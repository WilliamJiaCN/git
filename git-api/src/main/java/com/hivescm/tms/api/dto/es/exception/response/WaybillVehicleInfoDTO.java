package com.hivescm.tms.api.dto.es.exception.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 责任车辆信息
 *
 * @author 李洪春
 * @since 2017/9/30 15:41
 */
@ToString
@Data
public class WaybillVehicleInfoDTO implements Serializable {
    private static final long serialVersionUID = 6582509522295185867L;

    @ApiModelProperty("批次号码")
    private String batchCode;

    @ApiModelProperty("车辆ID")
    private Integer vehicleId;
    @ApiModelProperty("车牌号码")
    private String vehicleNo;

    @ApiModelProperty("司机ID")
    private Integer driverId;
    @ApiModelProperty("司机姓名")
    private String driverName;

    @ApiModelProperty("承运商ID")
    private Integer carrierId;
    @ApiModelProperty("承运商名称")
    private String carrierName;


}
