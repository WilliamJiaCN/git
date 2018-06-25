package com.hivescm.tms.api.dto.es.sign.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * @author Micha31
 * @Company 蜂网供应链管理（上海）有限公司
 * @date 2018/4/4
 */
@Data
@ToString
public class SignWaybillInfoRespDTO {
    @ApiModelProperty("运单信息")
    private SimpleWaybill4SignRespDTO waybillEsDTO;

   //private List<DispatcherDetailEsDTO> detailEsDTOList;

    @ApiModelProperty("签收运单页签")
    private List<SignWaybillDetailsRespDTO> signWaybillDetails;

    //@ApiModelProperty("费用信息")
    //private Map waybillFeeJson; //暂时存储所有费用明细

    @ApiModelProperty("费用信息")
    private SignWaybillFeeRespDTO signWaybillFee;

}
