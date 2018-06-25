package com.hivescm.tms.finance.server.component.pcsign.impl;

import com.hivescm.framework.common.exception.ExceptionFactory;
import com.hivescm.framework.entitymapping.utils.EntityUtils;
import com.hivescm.tms.api.dto.es.dispatcher.DispatcherDetailEsDTO;
import com.hivescm.tms.api.dto.es.dispatcher.DispatcherGoodsEsDTO;
import com.hivescm.tms.api.dto.es.sign.SignDetailsEsDTO;
import com.hivescm.tms.api.dto.es.sign.SignEsDTO;
import com.hivescm.tms.api.dto.es.sign.SignFeeEsDTO;
import com.hivescm.tms.api.dto.es.sign.component.TmsSignEsDTO;
import com.hivescm.tms.api.enums.biz.sign.SignStatusEnum;
import com.hivescm.tms.constants.ExceptionCodeConstants;
import com.hivescm.tms.finance.server.component.pcsign.MockSignService;
import com.hivescm.tms.finance.server.feign.dispatcher.DispatcherService;
import com.hivescm.tms.finance.server.service.sign.EsSignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 模拟 签收操作
 * @author Micha31
 * @Company 蜂网供应链管理（上海）有限公司
 * @date 2018/4/18
 */
@Service
public class MockSignServiceImpl implements MockSignService {

    @Autowired
    private EsSignService esSignService;
    @Autowired
    private DispatcherService dispatcherService;


    public TmsSignEsDTO mockBuildSignReq(long detailId, int signType) throws Exception {

        DispatcherDetailEsDTO detailEsDTO = dispatcherService.queryDispatcherDetailById(detailId);

        List<DispatcherGoodsEsDTO> dispatcherGoodsEsDTOList = detailEsDTO.getDispatcherGoodsEsDTOList();


        int signNum, refuseNum, createNum = detailEsDTO.getPackageNum();


        if (SignStatusEnum.SIGNED.getType() == signType) {

            signNum = createNum;
            refuseNum = 0;

        } else if (SignStatusEnum.PARTIAL_SIGN.getType() == signType) {

            refuseNum = 1;
            signNum = createNum - refuseNum;
            if (refuseNum == 0) {
                throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_ADD_SIGN, "一件货物无法部分签收");
            }

        } else {
            signNum = 0;
            refuseNum = createNum;

        }

        return buildSignReq(signType, signNum, refuseNum, createNum,detailEsDTO,dispatcherGoodsEsDTOList);


    }

    private TmsSignEsDTO buildSignReq(int signType,int signNum, int refuseNum, int createNum, DispatcherDetailEsDTO detailEsDTO, List<DispatcherGoodsEsDTO> dispatcherGoodsEsDTOList) throws Exception {


        TmsSignEsDTO tmsSignEsDTO = new TmsSignEsDTO();


        SignEsDTO signEsDTO = new SignEsDTO();
        EntityUtils.copyProperties(detailEsDTO,signEsDTO);
        {//签收主表
            signEsDTO.setDispatcherDetailId(detailEsDTO.getId());
            signEsDTO.setSignNumber(signNum);
            signEsDTO.setRefuseNumber(refuseNum);
            signEsDTO.setCreateNumber(createNum);
            signEsDTO.setCode(detailEsDTO.getOrderCode());
            signEsDTO.setWaybillCode(detailEsDTO.getOrderCode());
            signEsDTO.setSignOrgId(detailEsDTO.getDestOrgId());
            signEsDTO.setSignOrgName(detailEsDTO.getDestOrgName());
            signEsDTO.setIdeliveryFailure(false);
            signEsDTO.setSignType(signType);

            signEsDTO.setHandler(detailEsDTO.getUpdateUser());
            signEsDTO.setHandlerName(detailEsDTO.getUpdateUserName());
            signEsDTO.setSignTime(System.currentTimeMillis());


            tmsSignEsDTO.setSignEsDTO(signEsDTO);
        }



        {//签收明细表
            //全签 全拒
            List<SignDetailsEsDTO> signDetailsEsDTOS = new ArrayList<>();
            for (DispatcherGoodsEsDTO dispatcherGoodsEsDTO : dispatcherGoodsEsDTOList) {

                SignDetailsEsDTO signDetail = EntityUtils.copyProperties(dispatcherGoodsEsDTO, SignDetailsEsDTO.class);
                Integer packageNum = dispatcherGoodsEsDTO.getPackageNum();
                if (signType==SignStatusEnum.REFUSE_SIGN.getType()) {


                    signDetail.setCreateNumber(packageNum);
                    signDetail.setSignNumber(0);
                    signDetail.setRefuseNumber(packageNum);
                    signDetail.setUnsignedNumber(0);


                }else {
                    signDetail.setCreateNumber(packageNum);
                    signDetail.setSignNumber(packageNum);
                    signDetail.setRefuseNumber(0);
                    signDetail.setUnsignedNumber(0);
                }
                signDetailsEsDTOS.add(signDetail);


            }

            //部分签收 取第一个明细
            if (signType==SignStatusEnum.PARTIAL_SIGN.getType()){

                SignDetailsEsDTO signDetailsEsDTO = signDetailsEsDTOS.get(0);
                signDetailsEsDTO.setSignNumber(signDetailsEsDTO.getCreateNumber()-1);
                signDetailsEsDTO.setRefuseNumber(1);



            }

            tmsSignEsDTO.setSignDetailsEsDTO(signDetailsEsDTOS);
        }

        {//签收费用表

            SignFeeEsDTO feeEsDTO = EntityUtils.copyProperties(detailEsDTO, SignFeeEsDTO.class);

            tmsSignEsDTO.setSignFeeEsDTO(feeEsDTO);


        }



        return tmsSignEsDTO;
    }


}
