package com.hivescm.tms.finance.server.service.sign;

import com.hivescm.common.domain.PagedList;
import com.hivescm.escenter.common.conditions.OrderCondition;
import com.hivescm.escenter.common.conditions.PageCondition;
import com.hivescm.escenter.common.conditions.SearchCondition;
import com.hivescm.tms.api.dto.es.sign.SignDetailsEsDTO;
import com.hivescm.tms.api.dto.es.sign.SignEsDTO;
import com.hivescm.tms.api.dto.es.sign.SignFeeEsDTO;
import com.hivescm.tms.api.dto.es.sign.SignRefuseEsDTO;
import com.hivescm.tms.api.dto.es.sign.request.PCSignReq;
import com.hivescm.tms.api.dto.es.sign.request.SearchSignCodeReqDTO;
import com.hivescm.tms.api.dto.es.sign.response.SignQueryResponseDTO;
import com.hivescm.tms.api.dto.es.sign.response.SignRefuseRespDTO;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 送货签收
 * @author 杨彭伟
 * @date 2018-01-29 16:06
 */
public interface EsSignService {
    /**
     * 查询签收单
     *
     * @param waybillId
     * @return
     */
    SignEsDTO querySignEsDTO(Long waybillId);

    /**
     * 查询签收主表信息
     * @param id
     * @return
     */
    SignEsDTO querySignEsDTOById(Long id);

    Boolean insertSignEsDTO(SignEsDTO signEsDTO);

    Boolean insertSignDetailsEsDTO(List<SignDetailsEsDTO> signDetailsEsDTOList);

    Boolean insertSignFeeEsDTO(SignFeeEsDTO signFeeEsDTO);

    void deleteSignEsDTOById(Long id);

    void deleteSignDetailsEsDTOById(Long signId);

    void deleteSignFeeEsDTOById(Long signId);

    Boolean updateRefuseSign(SignRefuseEsDTO signRefuseEsDTO);


    SignFeeEsDTO querySignFeeEsDTO(Long signId);

    Boolean updateSignEsDTO(SignEsDTO signEsDTO);
    /**
     * 统计签收数量
     * @param waybillId
     * @return
     */
    Integer countSignNumber(Long waybillId);

    /**
     * 根据条件查询
     * @param pcSignReq
     * @return
     */
    List<SignEsDTO> querySignEsByConditions(PCSignReq pcSignReq);
    List<SignEsDTO> querySignEsByConditions(List<SearchCondition> scs);
    List<SignEsDTO> querySignEsByConditions(List<SearchCondition> scs , List<OrderCondition> orderConditions, PageCondition pageCondition);

    List<SignEsDTO> querySignEsByConditions(List<SearchCondition> scs , List<OrderCondition> orderConditions);
    /**
     * 查询 拒收信息
     * @param pcSignReq
     * @return
     */
    List<SignRefuseEsDTO> queryRefuseSignEsByConditions(PCSignReq pcSignReq);
    List<SignRefuseEsDTO> queryRefuseSignEsByConditions(List<SearchCondition> scs , List<OrderCondition> orderConditions, PageCondition pageCondition);

    List<SignRefuseRespDTO> queryRefuseRespByConditions(List<SearchCondition> scs , List<OrderCondition> orderConditions, PageCondition pageCondition);


    /**
         * 根据运单id查询签收单
         * @param waybillId
         * @return
         */
    List<SignEsDTO> querySignEsByWaybillId(Long waybillId);
    /**
     * 根据运单code查询签收单
     * @param waybillCode
     * @return
     */
    List<SignEsDTO> querySignEsByWaybillCode(String waybillCode);
    /**
     * 通过签收单id删除数据
     * @param signIds
     * @return
     */
    Boolean deleteBySignIds(List<Long> signIds);
    /**
     * 通过签收单id删除数据
     * @param signIds
     * @return
     */
    Boolean deleteBySignId(Long signId);
    /**
     * 根据运单id查询签收单
     * @param waybillIds
     * @return
     */
    List<SignEsDTO> querySignEsByWaybillIds(List<Long> waybillIds);
    /**
     * 自提取消专用，其他慎用
     * @param ids
     * @return
     */
    List<SignEsDTO> querySignEsByIds(List<Long> ids);
    /**
     * 批量保存签收es
     * @param signEsDTOs
     * @return
     */
    Boolean insertBatchSignEs(List<SignEsDTO> signEsDTOs);

    SignEsDTO querySignEsDTOByDispatcherDetailId(Long dispatcherDetailId);
    SignEsDTO querySignEsDTOByDispatcherDetailIdNoCancel(Long dispatcherDetailId);

    List<SignDetailsEsDTO> querySignDetailBySignId(Long signId);

    List<SignDetailsEsDTO> querySignDetailByDispatcherDetailId(Long dispatcherDetailId);

    List<SignEsDTO> querySignInfoByWaybillId(Long waybillId);
    /**
     * 批量更新
     * @param signEsDTO
     * @return
     */
    Boolean updateSignEsBatchById(List<SignEsDTO> signEsDTO);

    Set<String> queryDistinctResult(String filedName, List<SearchCondition> scs , List<OrderCondition> orderConditions, PageCondition pageCondition) ;
    Set<String> queryDistinctResult(String filedName,PCSignReq pcSignReq);

    List<SignEsDTO> querySignEsByWaybillCodes(Collection<String> codes);
    
    /**
     * 根据签收批次号查询签收信息
     * @param signBatchNumber
     * @return
     */
    List<SignEsDTO> querySignBySignBatchNumber(List<String> signBatchNumber);
    /**
     * 更新签收信息
     * @param sign
     * @return
     */
    Boolean updateSignById(List<SignEsDTO> sign);
    
    /**
     * |列表根据条件统计数量
     * @param pcSignReq
     * @return
     */
    Integer countSignBills(PCSignReq pcSignReq);



    Integer countRefuseSignBills(List<SearchCondition> scs );
    
    /**
     * 根据签收批次号查询签收信息
     * @param signBatchNumber
     * @return
     */
    SignEsDTO querySignBySignBatchNumber(String signBatchNumber,Long companyId);
    
    /**
     * 根据运单号查询签收批次
     * @param signBatchNumber
     * @return
     */
    List<String> querySignNumber(SearchSignCodeReqDTO req);

    /**
     * 拒收单模糊查询接口
     * @param cascadeName
     * @param like
     * @param searchConditions
     * @param orderConditions
     * @param pageCondition
     * @return
     */
    PagedList<SignRefuseRespDTO> queryRefuseLikeRespByConditions(String cascadeName, String like, List<SearchCondition> searchConditions, List<OrderCondition> orderConditions, PageCondition pageCondition);
    /**
     * 签收单模糊查询接口
     * @param cascadeName
     * @param like
     * @param searchConditions
     * @param orderConditions
     * @param pageCondition
     * @return
     */
    PagedList<SignEsDTO> querySignLikeRespByConditions(String cascadeName, String like, List<SearchCondition> searchConditions, List<OrderCondition> orderConditions, PageCondition pageCondition);
}
