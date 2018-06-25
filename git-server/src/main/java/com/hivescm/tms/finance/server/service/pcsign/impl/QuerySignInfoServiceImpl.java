package com.hivescm.tms.finance.server.service.pcsign.impl;

import com.hivescm.common.domain.PagedList;
import com.hivescm.escenter.common.conditions.OrderCondition;
import com.hivescm.escenter.common.conditions.PageCondition;
import com.hivescm.escenter.common.conditions.SearchCondition;
import com.hivescm.escenter.common.enums.SortEnum;
import com.hivescm.framework.common.exception.ExceptionFactory;
import com.hivescm.framework.elasticsearch.utils.OrderConditionUtils;
import com.hivescm.framework.elasticsearch.utils.PageConditionUtils;
import com.hivescm.framework.entitymapping.utils.EntityUtils;
import com.hivescm.tms.api.dto.es.base.request.EsLikeReqDTO;
import com.hivescm.tms.api.dto.es.dispatcher.DispatcherDetailEsDTO;
import com.hivescm.tms.api.dto.es.dispatcher.request.DispatcherDetailForSignReqDTO;
import com.hivescm.tms.api.dto.es.sign.DeliveryFailureEsDTO;
import com.hivescm.tms.api.dto.es.sign.SignDetailsEsDTO;
import com.hivescm.tms.api.dto.es.sign.SignEsDTO;
import com.hivescm.tms.api.dto.es.sign.request.AppQueryReqDTO;
import com.hivescm.tms.api.dto.es.sign.request.PCRefuseSignQueryReq;
import com.hivescm.tms.api.dto.es.sign.request.PCSignReq;
import com.hivescm.tms.api.dto.es.sign.request.SignQueryReqDTO;
import com.hivescm.tms.api.dto.es.sign.response.*;
import com.hivescm.tms.api.dto.es.waybill.WaybillEsDTO;
import com.hivescm.tms.api.dto.es.waybill.WaybillFeeEsDTO;
import com.hivescm.tms.api.enums.biz.dispatcher.DispatcherDetailStatusEnum;
import com.hivescm.tms.api.enums.biz.sign.SignStatusEnum;
import com.hivescm.tms.api.enums.biz.waybill.WaybillFeeTypeEnum;
import com.hivescm.tms.constants.EsLikeMatchConstants;
import com.hivescm.tms.constants.ExceptionCodeConstants;
import com.hivescm.tms.finance.server.dao.mapper.sign.SignMapper;
import com.hivescm.tms.finance.server.dao.query.SignWaybillDBQueryDTO;
import com.hivescm.tms.finance.server.feign.dispatcher.DispatcherService;
import com.hivescm.tms.finance.server.feign.waybill.WaybillService;
import com.hivescm.tms.finance.server.service.pcsign.QuerySignInfoService;
import com.hivescm.tms.finance.server.service.sign.EsDeliveryFailureService;
import com.hivescm.tms.finance.server.service.sign.EsSignDetailsService;
import com.hivescm.tms.finance.server.service.sign.EsSignService;
import com.hivescm.tms.utils.StringUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Micha31
 * @Company 蜂网供应链管理（上海）有限公司
 * @date 2018/4/4
 */
@Service
public class QuerySignInfoServiceImpl implements QuerySignInfoService {

    @Autowired
    private DispatcherService dispatcherService;
    @Autowired
    private WaybillService waybillService;

    @Autowired
    private EsSignService esSignService;

    @Autowired
    private EsSignDetailsService esSignDetailsService;

    @Autowired
    private EsDeliveryFailureService esDeliveryFailureService;

    @Autowired
    private SignMapper signMapper;

    /**
     * 通过运单号获取运单签收信息
     *
     * @param waybillCode
     * @return
     */
    @Override
    public SignWaybillInfoRespDTO getWaybillSignInfo(String waybillCode, Long companyId) {

        SignWaybillInfoRespDTO resp = new SignWaybillInfoRespDTO();

        WaybillEsDTO waybill = waybillService.getWaybillByCode(waybillCode, companyId.intValue());

        //属性copy
        SimpleWaybill4SignRespDTO waybill4SignRespDTO = EntityUtils.copyProperties(waybill, SimpleWaybill4SignRespDTO.class);
        waybill4SignRespDTO.setUnSignNumber(waybill.getTotalNum()
                - (waybill.getTotalSignNumber() == null ? 0 : waybill.getTotalSignNumber())
                - (waybill.getTotalRefuseSignNumber() == null ? 0 : waybill.getTotalRefuseSignNumber()));
        resp.setWaybillEsDTO(waybill4SignRespDTO);

        //获取签收列表
        List<SignEsDTO> signEsDTOS = esSignService.querySignEsByWaybillCode(waybillCode);
        if (signEsDTOS.size() == 0) {
            return resp;
        }
        List<SignWaybillDetailsRespDTO> signWaybillDetails = new ArrayList<>();
        resp.setSignWaybillDetails(signWaybillDetails);
        //过滤掉-取消签收的状态
        signEsDTOS.stream().filter(e -> e.getSignStatus() != SignStatusEnum.CANCEL_SIGN.getType()).forEach(e -> {
            SignWaybillDetailsRespDTO detailsRespDTO = new SignWaybillDetailsRespDTO();
            //签收主表
            detailsRespDTO.setSignEsDTO(e);
            //获取签收详情
            List<SignDetailsEsDTO> signDetailsEsDTOS = esSignService.querySignDetailBySignId(e.getId());
            detailsRespDTO.setSignDetailsEsDTO(signDetailsEsDTOS);
            signWaybillDetails.add(detailsRespDTO);
        });

        //设置运单费用
        resp.setSignWaybillFee(getWaybillFeeObject(waybill));

        return resp;
    }

    @Override
    public PagedList<SignEsDTO> getSignInfoPage(PCSignReq req) {
        Integer count = esSignService.countSignBills(req);

        List<SignEsDTO> signs = esSignService.querySignEsByConditions(req);

        PagedList<SignEsDTO> pagedList = PagedList.createInstance(req.getCurrentPage(), req.getPageSize(), count, signs);


        return pagedList;
    }

    /**
     * 通过派车单明细查询签收页面展示信息
     * <p>
     * 如果签收前无签收主表信息
     *
     * @param dispatcherDetailId
     * @return
     */
    @Override
    public SignRespDTO getDeliverySignInfoByDispatcherDetailId(Long dispatcherDetailId) {

        DispatcherDetailEsDTO dispatcherDetail = dispatcherService.queryDispatcherDetailById(dispatcherDetailId);

        if (dispatcherDetail == null) {
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_SEARCH_SIGN, "派车信息不存在");

        }

        WaybillEsDTO waybillEsDTO = waybillService.queryWaybillEsDTO(dispatcherDetail.getWaybillId());

        if (waybillEsDTO == null) {
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_SEARCH_SIGN, "运单信息不存在");

        }

        SignRespDTO signRespDTO = new SignRespDTO();

        SignEsDTO signEsDTO = esSignService.querySignEsDTOByDispatcherDetailId(dispatcherDetailId);
        if (signEsDTO != null) {
            EntityUtils.copyProperties(signEsDTO, signRespDTO);
            List<SignDetailsEsDTO> signDetailsEsDTOS = esSignService.querySignDetailBySignId(signEsDTO.getId());
            signRespDTO.setSignDetails(signDetailsEsDTOS);
        } else {
            //签收单不存在
        }

        signRespDTO.setDispatcherDetailEsDTO(dispatcherDetail);
        SimpleWaybill4SignRespDTO waybillDTO = EntityUtils.copyProperties(waybillEsDTO, SimpleWaybill4SignRespDTO.class);
        waybillDTO.setUnSignNumber(waybillDTO.getTotalNum()
                - (waybillDTO.getTotalSignNumber() == null ? 0 : waybillDTO.getTotalSignNumber())
                - (waybillDTO.getTotalRefuseSignNumber() == null ? 0 : waybillDTO.getTotalRefuseSignNumber()));

        signRespDTO.setWaybill(waybillDTO);
        signRespDTO.setSignWaybillFeeRespDTO(getWaybillFeeObject(waybillEsDTO));

        //派送失败 赋值
        if (dispatcherDetail.getDetailStatus().intValue() == DispatcherDetailStatusEnum.COMPLETE.getType()
                && dispatcherDetail.getSignType().intValue() == SignStatusEnum.NO_SIGN.getType()) {

            signRespDTO.setDeliveryFailure(true);
        }

        return signRespDTO;


    }

    /**
     * 获取运单费用
     * 2018-06-07
     *
     * @param waybillEsDTO
     * @return
     */
    private SignWaybillFeeRespDTO getWaybillFeeObject(WaybillEsDTO waybillEsDTO) {

        SignWaybillFeeRespDTO feeRespDTO = new SignWaybillFeeRespDTO();
        if (waybillEsDTO != null) {
            //通过运单id获取费用列表
            List<WaybillFeeEsDTO> waybillFeeEsDTOList = waybillService.getWaybillFeeByWaybillId(waybillEsDTO.getId());
            if (waybillFeeEsDTOList != null && waybillFeeEsDTOList.size() > 0) {
                waybillFeeEsDTOList.stream().forEach(item -> {
                    if (item.getAttrId() == WaybillFeeTypeEnum.TO_PAY_DELIVERY_FEE.getType()) {
                        //到付送货费
                        feeRespDTO.setToPayDeliveryFee(item.getFee());
                    }
                    if (item.getAttrId() == WaybillFeeTypeEnum.COLLECT_PAYMENT.getType()) {
                        //代收货款
                        feeRespDTO.setOrderGoodsPayment(item.getFee());
                    }
                });
            }
            //为空默认初始值为0
            if (feeRespDTO.getOrderGoodsPayment() == null) {
                feeRespDTO.setOrderGoodsPayment(BigDecimal.ZERO);
            }
            if (feeRespDTO.getToPayDeliveryFee() == null) {
                feeRespDTO.setToPayDeliveryFee(BigDecimal.ZERO);
            }
            //二次派送费
            feeRespDTO.setSecondDeliveryFee(BigDecimal.ZERO);
            //其他费项
            feeRespDTO.setOtherFeeStation(BigDecimal.ZERO);
            //到付
            feeRespDTO.setCod(waybillEsDTO.getCod());
        }
        return feeRespDTO;
    }

    /**
     * 获取运单费用 json
     * 2018-06-07
     *
     * @param
     * @return
     */
   /*  private JSONObject getFeeJsonObject(WaybillEsDTO waybillEsDTO) {
        //通过运单id获取费用列表
        List<WaybillFeeEsDTO> waybillFeeEsDTOList = waybillService.getWaybillFeeByWaybillId(waybillEsDTO.getId());
        JSONObject jsonObject = new JSONObject();
        if (waybillFeeEsDTOList != null) {
            Map<Integer, WaybillFeeEsDTO> waybillFees = waybillFeeEsDTOList.stream()
                    .collect(Collectors.toMap(WaybillFeeEsDTO::getAttrId, a -> a));
            //费用
            Set<Map.Entry<Integer, WaybillFeeEsDTO>> entries = waybillFees.entrySet();
            entries.forEach(entry -> {
                WaybillFeeTypeEnum feeTypeEnum = WaybillFeeTypeEnum.getType(entry.getKey());

                if (feeTypeEnum != null) {
                    jsonObject.put(feeTypeEnum.toString().toLowerCase(), entry.getValue().getFee());
                }
            });
            //费用 json 前端算费用
            //到付
            jsonObject.put("cod", waybillEsDTO.getCod() == null ? 0 : waybillEsDTO.getCod());
            //代收货款
            jsonObject.put("orderGoodsPayment", waybillEsDTO.getOrderGoodsPayment() == null ? 0 : waybillEsDTO.getOrderGoodsPayment());
            //其他费项
            jsonObject.put("otherFee", waybillEsDTO.getOtherFee());
            //代收货款手续费
            jsonObject.put("deliveryCharge", waybillEsDTO.getDeliveryCharge());
        }

        //到付送货费  二次派送费
        return jsonObject;
    }*/
    @Override
    public PagedList<SignRefuseRespDTO> getRefuseSignInfoPage(PCRefuseSignQueryReq req) {
        PagedList<SignRefuseRespDTO> pagedList;
        List<SignRefuseRespDTO> refuseEsDTOS;

        //如果没有模糊查询信息
        if (StringUtil.isBlank(req.getLike())) {
            Integer count = esSignService.countRefuseSignBills(req.toSearchCondition());

            if (count.intValue() == 0) {

                return PagedList.createInstance(req.getCurrentPage(), req.getPageSize(), 0, Collections.EMPTY_LIST);
            }

            //查询 拒签主表信息
            refuseEsDTOS = esSignService.queryRefuseRespByConditions(req.toSearchCondition(), req.toOrderConditions(), req.toPageCondition());

            //分页
            pagedList = PagedList.createInstance(req.getCurrentPage(), req.getPageSize(), count, refuseEsDTOS);


        }
        //如果有模糊查询信息
        else {
            pagedList = esSignService.queryRefuseLikeRespByConditions(EsLikeMatchConstants.REFUSE_SIGN, req.getLike(), req.toSearchCondition(), req.toOrderConditions(), req.toPageCondition());

            if (pagedList.getItems().isEmpty()) {
                return pagedList;
            }

            refuseEsDTOS = pagedList.getItems();
        }

        //签收主表id 1:1
        Map<Long, List<SignRefuseRespDTO>> signIdMap = refuseEsDTOS.stream().filter(e -> e.getSignId() != null).collect(Collectors.groupingBy(SignRefuseRespDTO::getSignId));

        Set<Long> signIds = signIdMap.keySet();

        if (signIds.size() == 0) {
            return PagedList.createInstance(req.getCurrentPage(), req.getPageSize(), 0, Collections.EMPTY_LIST);
        }

        //查询出签收货物明细
        List<SignDetailsEsDTO> detailsEsDTOS = esSignDetailsService.queryBySignIds(signIds);


        //拒签货物   K: signId  V: 拒签货物s fixme 数据问题暂时不过滤
        Map<Long, List<SignDetailsEsDTO>> refuseDetailsMap = detailsEsDTOS.stream()
//                .filter(e -> e.getRefuseNumber()!=null&& e.getRefuseNumber().intValue() != 0)
                .collect(Collectors.groupingBy(SignDetailsEsDTO::getSignId));

        refuseEsDTOS.forEach(e -> {
            List<SignDetailsEsDTO> signDetailsEsDTOS = refuseDetailsMap.get(e.getSignId());
            e.setRefuseDetail(signDetailsEsDTOS);

        });

        return pagedList;
    }

//    public List<SignRefuseEsDTO> getRefuseSignDetail(Long refuseId) {
//
//
//        esSignService.querySignEsDTO()
//    }

    @Override
    public List<SignDetailsEsDTO> getSignDetail(Long signId) {

        List<SignDetailsEsDTO> result = esSignService.querySignDetailBySignId(signId);

        return result;
    }

//    @Override
//    public List<SignDetailsEsDTO> getDispatcherGoodsDetail(Long dispatcherDetailId) {
//
//        List<DispatcherGoodsEsDTO> goods = dispatcherService.getDispatcherGoodsByDetailId(dispatcherDetailId);
//
//        return result;
//    }

    @Override
    public List<SignDetailsEsDTO> getSignDetailByDispatcherDetailId(Long dispatcherDetailId) {

        List<SignDetailsEsDTO> result = esSignService.querySignDetailByDispatcherDetailId(dispatcherDetailId);

        return result;
    }

    /**
     * 送货列表
     * <p>
     * 未签收页签
     * 查派车单明细表
     * 全部签收 部分签收 全部拒签 页签
     * 查询签收主表  fixme 把能冗余的都冗余了,遍历->关联查询效率太低
     * 派送失败页签
     * 查询派送失败记录  fixme 把能冗余的都冗余了,遍历->关联查询效率太低
     *
     * @param signQueryReqDTO
     * @return
     * @throws Exception
     */
    @Override
    public SignQueryPageResponseDTO queryDispatcherDetailList(SignQueryReqDTO signQueryReqDTO) throws Exception {
        if (signQueryReqDTO.getDeliveryFailure() != null && signQueryReqDTO.getDeliveryFailure()) {
            //派送失败，fixme-zouhx 待优化,派送失败存放到签收主表，不往派送失败表存储数据
            return queryDispatcherFail(signQueryReqDTO);
        } else if (signQueryReqDTO.getSignStatus() != null && signQueryReqDTO.getSignStatus().getType() == SignStatusEnum.NO_SIGN.getType()) {
            //未签收
            return queryNoSignOrFail(signQueryReqDTO);
        } else {
            //全部签收、部分签收、全部拒签
            return querySignbill(signQueryReqDTO);
        }

    }

    /**
     * 查询派送失败页签
     *
     * @param signQueryReqDTO
     * @return
     */
    private SignQueryPageResponseDTO queryDispatcherFail(SignQueryReqDTO signQueryReqDTO) {

        SignQueryPageResponseDTO result = new SignQueryPageResponseDTO(); //返回对象

        try {
            List<SearchCondition> searchConditions = signQueryReqDTO.buildDispatcherFailCondition(); //构建查询条件
            List<OrderCondition> orderConditions = OrderConditionUtils.start().addCondition("createTime", SortEnum.DESC).end(); //构建排序
            PageCondition pageCondition = signQueryReqDTO.buildPageCondition(); //构建分页

            //查询派送失败列表
            PagedList<DeliveryFailureEsDTO> failureEsDTOPagedList = esDeliveryFailureService.queryPage(EsLikeMatchConstants.DELIVERY_SIGN, signQueryReqDTO.getLike(), searchConditions, pageCondition, orderConditions);
            List<DeliveryFailureEsDTO> items = failureEsDTOPagedList.getItems();
            result.setTotalNum(failureEsDTOPagedList.getTotalCount());

            if (items.isEmpty()) {
                result.setList(Collections.EMPTY_LIST);
            } else {
                List<SignQueryResponseDTO> list = new ArrayList<>(items.size());
                items.forEach(e -> {
                    SignQueryResponseDTO signQueryResponseDTO = new SignQueryResponseDTO();
                    signQueryResponseDTO.acceptFail(e);
                    // 派车单明细
                    Long dispatcherDetailId = e.getDispatcherDetailId();
                    if (dispatcherDetailId != null) {
                        DispatcherDetailEsDTO detailEsDTO = dispatcherService.queryDispatcherDetailById(dispatcherDetailId);
                        if (detailEsDTO != null) {
                            signQueryResponseDTO.acceptDispatcherDetail(detailEsDTO);
                            //派车单商品详情
                            //signQueryResponseDTO.dispatcherGoodsEsDTOList
                            if (detailEsDTO.getDispatcherGoodsEsDTOList() != null) {
                                signQueryResponseDTO.setDispatcherGoodsEsDTOList(detailEsDTO.getDispatcherGoodsEsDTOList());
                            }
                        }
                    }
                    //运单
                    Long waybillId = e.getWaybillId();
                    //运单费用
                    SignWaybillFeeRespDTO feeObject = new SignWaybillFeeRespDTO();
                    if (waybillId != null) {
                        WaybillEsDTO waybill = waybillService.queryWaybillEsDTO(waybillId);
                        if (waybill != null) {
                            signQueryResponseDTO.acceptWaybill(waybill);
                            feeObject = getWaybillFeeObject(waybill);
                        }
                    }
                    signQueryResponseDTO.setSignWaybillFee(feeObject);
                    list.add(signQueryResponseDTO);
                });
                result.setList(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }

    /**
     * 获取签收列表
     * <p>
     * 2018-06-07
     *
     * @param signQueryReqDTO
     * @return
     */
    private SignQueryPageResponseDTO querySignbill(SignQueryReqDTO signQueryReqDTO) {
        //在签收主表查询
        List<SearchCondition> searchConditions = signQueryReqDTO.buildSignSearchCondition();
        List<OrderCondition> orderConditions = signQueryReqDTO.buildOrderCondition();
        PageCondition pageCondition = signQueryReqDTO.buildPageCondition();

        PagedList<SignEsDTO> signEsDTOPagedList = esSignService.querySignLikeRespByConditions(EsLikeMatchConstants.DELIVERY_SIGN, signQueryReqDTO.getLike(), searchConditions, orderConditions, pageCondition);
        List<SignEsDTO> items = signEsDTOPagedList.getItems();
        if (items.isEmpty()) {
            SignQueryPageResponseDTO result = new SignQueryPageResponseDTO();
            result.setTotalNum(signEsDTOPagedList.getTotalCount());
            result.setList(Collections.EMPTY_LIST);
            return result;
        }

        List<SignQueryResponseDTO> list = new ArrayList<>(items.size());
        items.forEach(e -> {
            SignQueryResponseDTO signQueryResponseDTO = new SignQueryResponseDTO();
            //签收单
            signQueryResponseDTO.acceptSign(e);
            //签收明细
            List<SignDetailsEsDTO> signDetailsEsDTOS = esSignService.querySignDetailBySignId(e.getId());
            signQueryResponseDTO.setSignDetailsEsDTOList(signDetailsEsDTOS);
            //派车单明细
            Long dispatcherDetailId = e.getDispatcherDetailId();
            if (dispatcherDetailId != null) {
                DispatcherDetailEsDTO detailEsDTO = dispatcherService.queryDispatcherDetailById(dispatcherDetailId);
                signQueryResponseDTO.acceptDispatcherDetail(detailEsDTO);
            }
            //运单费用
            SignWaybillFeeRespDTO feeObject = new SignWaybillFeeRespDTO();
            //运单
            Long waybillId = e.getWaybillId();
            if (waybillId != null) {
                WaybillEsDTO waybill = waybillService.queryWaybillEsDTO(waybillId);
                if (waybill != null) {
                    signQueryResponseDTO.acceptWaybill(waybill);
                    feeObject = getWaybillFeeObject(waybill);
                }
            }
            signQueryResponseDTO.setSignWaybillFee(feeObject);

            //signQueryResponseDTO.setDeliveryFailure(false);
            /*signQueryResponseDTO.setWaitSignNumber(signQueryResponseDTO.getPackageNum() == null ? 0 : signQueryResponseDTO.getPackageNum()
                    - (signQueryResponseDTO.getSignNumber() == null ? 0 : signQueryResponseDTO.getPackageNum())
                    - (signQueryResponseDTO.getRefuseNumber() == null ? 0 : signQueryResponseDTO.getRefuseNumber()));*/
            list.add(signQueryResponseDTO);
        });

//        List<SignQueryResponseDTO> responseDTOs=copyToResponseDTO(items);
//
//
//        查询运单
//        searchWaybillForDispatcher(responseDTOs);
//        searchDispatcherFor(responseDTOs);


//      esSignService.
        SignQueryPageResponseDTO result = new SignQueryPageResponseDTO();
        result.setList(list);
        result.setTotalNum(signEsDTOPagedList.getTotalCount());
        return result;
    }


    /**
     * 获取未签收列表
     * fixme 用 queryDispatcherFail() 替换派送失败
     * 查 派车单明细
     *
     * @param signQueryReqDTO
     * @return
     * @throws Exception
     */
    private SignQueryPageResponseDTO queryNoSignOrFail(SignQueryReqDTO signQueryReqDTO) throws Exception {

        List<SignQueryResponseDTO> list;
        Integer signListTotalNum;
        SignQueryPageResponseDTO result = new SignQueryPageResponseDTO();

        //查询总数
        signListTotalNum = getSignListTotalNum(signQueryReqDTO);
        if (signListTotalNum == 0) {
            result.setList(Collections.EMPTY_LIST);
            result.setTotalNum(0);
            return result;
        }
        //构建rpc请求参数
        DispatcherDetailForSignReqDTO signReqDTO = getDispatcherDetailForSignReqDTO(signQueryReqDTO);
        //根据条件查询列表
        list = getSignQueryResponseDTOList(signReqDTO);

        if (list.size() == 0) {
            result.setList(Collections.EMPTY_LIST);
            result.setTotalNum(signListTotalNum);
            return result;
        }
        list.forEach(e -> {
            //运单费用
            SignWaybillFeeRespDTO feeObject = new SignWaybillFeeRespDTO();
            //运单
            Long waybillId = e.getWaybillId();
            if (waybillId != null) {
                WaybillEsDTO waybill = waybillService.queryWaybillEsDTO(waybillId);
                if (waybill != null) {
                    e.acceptWaybill(waybill);
                    feeObject = getWaybillFeeObject(waybill);
                }
            }
            e.setSignWaybillFee(feeObject);
            e.setDeliveryFailure(false);
            //待签件数用于等于送货件数
            e.setWaitSignNumber(e.getPackageNum() == null ? 0 : e.getPackageNum());
            // -(e.getSignNumber()==null?0:e.getPackageNum())
            //-(e.getRefuseNumber()==null?0:e.getRefuseNumber()));
        });
        result.setList(list);
        result.setTotalNum(signListTotalNum);
        return result;
    }


    /**
     * 构建派车单详情查询条件
     *
     * @param signQueryReqDTO
     * @return
     */
    private DispatcherDetailForSignReqDTO getDispatcherDetailForSignReqDTO(SignQueryReqDTO signQueryReqDTO) {
        List<SearchCondition> searchConditions = signQueryReqDTO.buildDispatcherDetailSearchCondition();
        List<OrderCondition> orderConditions = signQueryReqDTO.buildOrderCondition();
        PageCondition pageCondition = signQueryReqDTO.buildPageCondition();
        DispatcherDetailForSignReqDTO signReqDTO = new DispatcherDetailForSignReqDTO();
        signReqDTO.setSearchConditions(searchConditions);
        signReqDTO.setOrderConditions(orderConditions);
        signReqDTO.setPageCondition(pageCondition);
        return signReqDTO;
    }

    /**
     * 未签收-查询派车单明细
     *
     * @param signQueryReqDTO
     * @return
     */
    private List<SignQueryResponseDTO> getSignQueryResponseDTOList(DispatcherDetailForSignReqDTO signQueryReqDTO)
            throws Exception {
        List<DispatcherDetailEsDTO> detailEsDTOS = dispatcherService.getDispatcherDetailEsList(signQueryReqDTO);
        List<SignQueryResponseDTO> list = new ArrayList<SignQueryResponseDTO>();
        if (CollectionUtils.isNotEmpty(detailEsDTOS)) {
            for (DispatcherDetailEsDTO dispatcherDetailEsDTO : detailEsDTOS) {
                SignQueryResponseDTO signQueryResponseDTO = new SignQueryResponseDTO();
                BeanUtils.copyProperties(dispatcherDetailEsDTO, signQueryResponseDTO);

                if (dispatcherDetailEsDTO.getDispatcherGoodsEsDTOList() != null) {
                    signQueryResponseDTO.setDispatcherGoodsEsDTOList(dispatcherDetailEsDTO.getDispatcherGoodsEsDTOList());
                }
                list.add(signQueryResponseDTO);
            }
        }
        return list;
    }

    /**
     * 列表查询总条数
     *
     * @param signQueryReqDTO 查询条件
     * @return
     */
    private Integer getSignListTotalNum(SignQueryReqDTO signQueryReqDTO) {
        List<SearchCondition> searchConditions = signQueryReqDTO.buildDispatcherDetailSearchCondition();
        DispatcherDetailForSignReqDTO signReqDTO = new DispatcherDetailForSignReqDTO();
        signReqDTO.setSearchConditions(searchConditions);
        return dispatcherService.getDeliveryDispatcherDetailTotalNum(signReqDTO);
    }

    /**
     * 虽然是以运单维度进行展示的,但是并不能从运单上获取足够多的信息,也不好查询
     * 所以:
     * 1 先查签收
     * 2 再对运单进行 归并
     * 3 Map-Reduce
     *
     * @param reqDTO
     * @return
     */
    @Override
    public PagedList<SignWaybillElementDTO> getSignWaybills(PCSignReq reqDTO) throws Exception {

        //获得 运单编号 集合
//        Set<String> codes = esSignService.queryDistinctResult("code", reqDTO);
        SignWaybillDBQueryDTO dbQueryDTO = buildDBQueryDTO(reqDTO);
        //如果 填了 运单号
        if (StringUtil.isNotBlank(reqDTO.getWaybillCode())) {
            //先查运单 看是否存在
            WaybillEsDTO waybill = waybillService.getWaybillByCode(reqDTO.getWaybillCode(), reqDTO.getCompanyId().intValue());
            if (waybill == null) {
                return PagedList.createInstance(reqDTO.getCurrentPage(), reqDTO.getPageSize(), 0, Collections.EMPTY_LIST);
            }
            dbQueryDTO.setWaybillId(waybill.getId());
        }

        Long total = signMapper.countDistinctWaybillNum(dbQueryDTO);

        if (total == 0) {
            return PagedList.createInstance(reqDTO.getCurrentPage(), reqDTO.getPageSize(), 0, Collections.EMPTY_LIST);
        }
        List<Long> waybillIds = signMapper.queryDistinctWaybillId(dbQueryDTO);

        if (waybillIds.size() == 0) {

            return PagedList.createInstance(reqDTO.getCurrentPage(), reqDTO.getPageSize(), total.intValue(), Collections.EMPTY_LIST);

        }
        //通过 运单号 批量查签收单列表
        List<SignEsDTO> signEsDTOS = esSignService.querySignEsByWaybillIds(waybillIds);

        //将 派车单明细 以 运单code 为依据进行归并
        // code: 运单号 -.-!
        Map<String, List<SignEsDTO>> map = signEsDTOS.stream().filter(e -> e.getCode() != null).collect(Collectors.groupingBy(SignEsDTO::getCode));


        Set<String> codes = map.keySet();

        // 批量查运单  然后 组装数据
        List<WaybillEsDTO> waybillEsDTOS = waybillService.getWaybillEsDTOListByCodes(codes, reqDTO.getCompanyId());

        //fixme 做一次批量拷贝比较耗时,如果效率低下,运单模块可以在 orm 的时候直接返回RespDTO
        List<SignWaybillElementDTO> respDTOS
                = EntityUtils.doBatchClone(waybillEsDTOS, SignWaybillElementDTO.class);


        respDTOS.parallelStream().peek(e -> {
            List<SignEsDTO> signs = map.get(e.getCode());

            e.setSignEsDTOS(signs);
        }).peek(e -> {

            SignEsDTO signEsDTO = e.getSignEsDTOS().get(0);


            String signBatchNumber = signEsDTO.getSignBatchNumber();
            String signPeople = signEsDTO.getSignPeople();

            String phoneNumber = signEsDTO.getPhoneNumber();
            String signingInstructions = signEsDTO.getSigningInstructions();
            String idCard = signEsDTO.getIdCard();

            String batchCode = signEsDTO.getBatchCode();
            String driverName = signEsDTO.getDriverName();
            String vehicleNo = signEsDTO.getVehicleNo();
            String phoneNo = signEsDTO.getPhoneNo();
            String signStatusName = signEsDTO.getSignStatusName();

            BigDecimal orderGoodsPayment = signEsDTO.getOrderGoodsPayment();
            Long dispatcherTime = signEsDTO.getDispatcherTime();


            e.setOrderGoodsPayment(orderGoodsPayment);
            e.setDispatcherTime(dispatcherTime);


            e.setSignBatchNumber(signBatchNumber);
            e.setSignPeople(signPeople);
            e.setPhoneNumber(phoneNumber);
            e.setSigningInstructions(signingInstructions);
            e.setIdCard(idCard);

            e.setBatchCode(batchCode);
            e.setDriverName(driverName);
            e.setVehicleNo(vehicleNo);
            e.setPhoneNo(phoneNo);
            e.setSignStatusName(signStatusName);


            e.setUnSignNumber(e.getTotalNum()
                    - (e.getTotalRefuseSignNumber() == null ? 0 : e.getTotalRefuseSignNumber())
                    - (e.getTotalSignNumber() == null ? 0 : e.getTotalSignNumber())
            );
        })

                //过滤 掉未拆的的
                .filter(e -> e.getSignEsDTOS().size() > 1)

                .peek(e -> {
                    String dot = "/";
                    String endFix = "等";

                    //获取第二个元素
                    SignEsDTO signEsDTO = e.getSignEsDTOS().get(1);

                    String signBatchNumber = signEsDTO.getSignBatchNumber();
                    String signPeople = signEsDTO.getSignPeople();

                    String phoneNumber = signEsDTO.getPhoneNumber();
                    String signingInstructions = signEsDTO.getSigningInstructions() == null ? "" : signEsDTO.getSigningInstructions();
                    String idCard = signEsDTO.getIdCard();

                    String batchCode = signEsDTO.getBatchCode();
                    String driverName = signEsDTO.getDriverName();
                    String vehicleNo = signEsDTO.getVehicleNo();
                    String phoneNo = signEsDTO.getPhoneNo();
                    String signStatusName = signEsDTO.getSignStatusName();


                    e.setSignBatchNumber(e.getSignBatchNumber() + dot + signBatchNumber + endFix);
                    e.setSignPeople(e.getSignPeople() + dot + signPeople + endFix);
                    e.setPhoneNumber(e.getPhoneNumber() + dot + phoneNumber + endFix);
                    e.setSigningInstructions(e.getSigningInstructions() + dot + signingInstructions + endFix);


                    e.setBatchCode(e.getBackCode() + dot + batchCode + endFix);
                    e.setDriverName(e.getDriverName() + dot + driverName + endFix);
                    e.setVehicleNo(e.getVehicleNo() + dot + vehicleNo + endFix);
                    e.setPhoneNo(e.getPhoneNo() + dot + phoneNo + endFix);
                    e.setSignStatusName(e.getStatusName() + dot + signStatusName + endFix);


                    if (StringUtil.isNotBlank(e.getIdCard())) {
                        e.setIdCard(e.getIdCard() + dot + idCard + endFix);
                    } else {
                        e.setIdCard(idCard);
                    }


                }).forEach(e -> {
            e.setDividedBill(true);
        });


//        return result;
        return PagedList.createInstance(reqDTO.getCurrentPage(), reqDTO.getPageSize(), total.intValue(), respDTOS);

//        return respDTOS;
    }


    @Override
    public PagedList<DispatcherDetailEsDTO> getDispatcherDetailLike(AppQueryReqDTO reqDTO) {


        PageCondition pageCondition = PageConditionUtils.create(reqDTO.getPageSize(), reqDTO.getCurrentPage());


        List<SearchCondition> scs = reqDTO.toDeliverySearchCondition();
        List<OrderCondition> order = OrderConditionUtils.start().addCondition("deliverySendTime", SortEnum.DESC).end();

        EsLikeReqDTO esLikeReqDTO = new EsLikeReqDTO();
        esLikeReqDTO.setScs(scs);
        esLikeReqDTO.setCascadeName(EsLikeMatchConstants.DELIVERY_SIGN);
        esLikeReqDTO.setLikeString(reqDTO.getParam());
        esLikeReqDTO.setPageCondition(pageCondition);
        esLikeReqDTO.setOrders(order);
        PagedList<DispatcherDetailEsDTO> result = dispatcherService.queryDispatcherDetailLike(esLikeReqDTO);

        return result;
    }


    private SignWaybillDBQueryDTO buildDBQueryDTO(PCSignReq reqDTO) {

        SignWaybillDBQueryDTO queryDTO = new SignWaybillDBQueryDTO();
        queryDTO.setCompanyId(reqDTO.getCompanyId());
        //短路
        if (StringUtil.isNotBlank(reqDTO.getWaybillCode())) {
            queryDTO.setCurrIndex(0);
            queryDTO.setPageSize(10);
            return queryDTO;
        }
        //短路
        if (StringUtil.isNotBlank(reqDTO.getSignBatchNumber())) {
            queryDTO.setSignBatchNumber(reqDTO.getSignBatchNumber());
            queryDTO.setCurrIndex(0);
            queryDTO.setPageSize(10);
            return queryDTO;
        }
        queryDTO.setPageSize(reqDTO.getPageSize());
        queryDTO.setCurrIndex(reqDTO.getPageSize() * (reqDTO.getCurrentPage() - 1));

        queryDTO.setSignBatchNumber(reqDTO.getSignBatchNumber());
        queryDTO.setStartTime(reqDTO.getStartTime());
        queryDTO.setEndTime(reqDTO.getEndTime());

        if (reqDTO.getSignStatus() != null) {
            queryDTO.setSignType(reqDTO.getSignStatus().getType());

        }
        return queryDTO;

    }

}
