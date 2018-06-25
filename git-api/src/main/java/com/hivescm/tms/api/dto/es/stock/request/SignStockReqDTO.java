package com.hivescm.tms.api.dto.es.stock.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * @author Micha31
 * @Company 蜂网供应链管理（上海）有限公司
 * @date 2018/3/31
 */
@Data
@ToString
public class SignStockReqDTO {

    @Mapping
    @ApiModelProperty("签收id")
    private Long signId;

    @Mapping
    @ApiModelProperty("公司id")
    private Long companyId;

    @Mapping
    @ApiModelProperty("运单id")
    private Long waybillId;

    @Mapping
    @ApiModelProperty("派车单id")
    private Long dispatcherId;
    @Mapping
    @ApiModelProperty("签收网点id")
    private Integer signOrgId;

    @Mapping
    @ApiModelProperty("派车单明细id")
    private Long dispatcherDetailId;

    @Mapping
    @ApiModelProperty("签收批次号")
    private String signBatchNumber;

    @Mapping
    @ApiModelProperty("订单号")
    private String orderCode;
    @Mapping
    @ApiModelProperty("签收时间")
    private Long signTime;

    @Mapping
    @ApiModelProperty("签收类型")
    private Integer signType;

    @Mapping
    @ApiModelProperty("签收件数")
    private Integer signNumber;

    @Mapping
    @ApiModelProperty("签收重量")
    private BigDecimal signWeight;

    @Mapping
    @ApiModelProperty("签收体积")
    private BigDecimal signVolume;

    @Mapping
    @ApiModelProperty("拒签件数")
    private Integer refuseNumber;

    @ApiModelProperty("未签件数")
    private Integer unsignedNumber;

    @Mapping
    @ApiModelProperty("开单件数")
    private Integer createNumber;

    @Mapping
    @ApiModelProperty("签收状态")
    private Integer signStatus;

    @Mapping
    @ApiModelProperty("派送类型(1 = 自提签收,2 = 送货签收)")
    private Integer deliveryType;

}
