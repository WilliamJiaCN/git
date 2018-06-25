package com.hivescm.tms.api.dto.es.outbill.sign;

import java.math.BigDecimal;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class OutbillSignGoodsDTO {

	@Mapping({"OutBillGoodsEsDTO.id","OutBillGoodsDO.id"})
	@ApiModelProperty("外发单商品ID")
	private Long ougbillGoodsId;
	
	@Mapping
	@ApiModelProperty("公司ID")
    private Long companyId;
	
	@Mapping
    @ApiModelProperty("运单商品ID")
    private Long goodsId;
    
    @Mapping
    @ApiModelProperty("实收件数")
    private Integer signPackageNum;
    
    @Mapping
    @ApiModelProperty("拒收件数")
    private Integer refusePackageNum;

    @Mapping
    @ApiModelProperty("实收体积")
    private BigDecimal signVolume;

    @Mapping
    @ApiModelProperty("实收重量")
    private BigDecimal signWeight;
    
    @Mapping
	@ApiModelProperty("拒签原因")
    private String refuseSignReasonName;
    
    @Mapping
   	@ApiModelProperty("拒签原因")
    private Integer refuseSignReason;
    
    @Mapping
	@ApiModelProperty("签收备注")
    private String signRemark;
    
}
