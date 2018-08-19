package com.hivescm.tms.api.dto.es.distribution;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.tms.api.enums.biz.dispatcher.DistributionVehicleStatusEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 车辆状态表
 *
 * @author
 */
@Data
@ToString
public class DistributionVehicleStatusEsDTO implements Serializable {
    /**
     * 主键
     */
    private @Mapping @ApiModelProperty("主键") Long id;
    /**
     * 公司id
     */
    private @Mapping @ApiModelProperty("公司id") Long companyId;
    /**
     * 公司名称
     */
    private @Mapping  @ApiModelProperty("公司名称") String companyName;
    /**
     * 车辆ID
     */
    private @Mapping @ApiModelProperty("车辆ID") Integer vehicleId;
    /**
     * 车牌号码
     */
    private @Mapping @ApiModelProperty("车牌号码") String vehicleNum;
    /**
     * 可载体积
     */
    private @Mapping @ApiModelProperty("可载体积") BigDecimal availableVolume;
    /**
     * 可载重量
     */
    private @Mapping @ApiModelProperty("可载重量") BigDecimal availableWeight;
    
    /**
     * 已载体积
     */
    private @Mapping @ApiModelProperty("已载体积") BigDecimal loadedVolume;
    /**
     * 已载重量
     */
    private @Mapping @ApiModelProperty("已载重量") BigDecimal loadedWeight;
    
    /**
     * 核载体积（M³）
     */
    private @Mapping @ApiModelProperty("核载体积（M³）") BigDecimal checkVolume;
    
    /**
     * 核载质量（KG）
     */
    private @Mapping @ApiModelProperty("核载质量（KG）") BigDecimal checkQuality;
    /**
     * 车辆载重状态(1=空载 2=半载 3=满载)
     */
    @ApiModelProperty("车辆载重状态(NO_LOAD=空载 HALF_LOAD=半载 FULL_LOAD=满载)")
    private @Mapping DistributionVehicleStatusEnum status;
    
    @ApiModelProperty("车辆类型ID")
    private @Mapping Integer vehicleType;
    
    @ApiModelProperty("车辆类型")
    private @Mapping String vehicleTypeName;
    /**
     * 创建人
     */
    private @Mapping @ApiModelProperty("创建人") Integer createUser;
    
    /**
     * 创建人姓名
     */
    private @Mapping @ApiModelProperty("创建人姓名") String createUserName;
    /**
     * 创建时间
     */
    @ApiModelProperty("创建时间")
    private @Mapping Long createTime;
    /**
     * 修改人
     */
    @ApiModelProperty("修改人")
    private @Mapping Integer updateUser;
    
    /**
     * 修改人姓名
     */
    private @Mapping @ApiModelProperty("修改人姓名") String updateUserName;
    /**
     * 修改时间
     */
    private @Mapping @ApiModelProperty("修改时间") Long updateTime;
    private static final long serialVersionUID = 1L;
}