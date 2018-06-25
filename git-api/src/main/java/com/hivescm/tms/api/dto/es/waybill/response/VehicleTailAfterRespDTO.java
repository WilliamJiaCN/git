package com.hivescm.tms.api.dto.es.waybill.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.tms.api.dto.es.waybill.VehicleTailAfterEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author qsk
 * @date 2017年7月18日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class VehicleTailAfterRespDTO implements Serializable {

    private static final long serialVersionUID = 7092569743878907211L;

    @ApiModelProperty("跟踪信息")
    private List<VehicleTailAfterEsDTO> list;
    @ApiModelProperty("运单号")
    private String waybillCode;
    @ApiModelProperty("订单编号")
    private String orderCode;
    @ApiModelProperty("始发地")
    private String invoiceCityName;
    @ApiModelProperty("目的地")
    private String destName;
    @ApiModelProperty("发货网点")
    private String invoiceOrgName;
    @ApiModelProperty("承运商名称")
    private String carrierName;
    @ApiModelProperty("司机ID")
    private Integer driverId;
    @ApiModelProperty("司机姓名")
    private String driverName;
    @ApiModelProperty("司机手机号")
    private String phoneNo;
    @ApiModelProperty("所用时间")
    private Long useTime;

    @Mapping
    @ApiModelProperty("仓库名称")
    private String warehouseName;
    @Mapping
    @ApiModelProperty("门店名称")
    private String storeName;
    @Mapping
    @ApiModelProperty("发货地址")
    private String invoiceAddress;
    @Mapping
    @ApiModelProperty("收货地址")
    private String receiptAddress;

    /**
     * 运单状态
     */
    @Mapping
    @ApiModelProperty("运单状态")
    private Integer waybillStatus;
    @Mapping
    @ApiModelProperty("所用时间")
    private String usedTime;
}
