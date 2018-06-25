package com.hivescm.tms.api.dto.es.schedule;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 电子围栏详情表
 *
 * @author qiushengkun
 * @company 蜂网供应链管理（上海）有限公司
 * @since 2017/9/4 13:56
 */
@Data
@ToString
public class ScheduleDeliveryBlockEsDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Logger
    @Mapping
    @ApiModelProperty("主键")
    private Long id;
    @Logger
    @Mapping
    @ApiModelProperty("公司Id")
    private Long companyId;
    @Logger
    @Mapping
    @ApiModelProperty("司机Id")
    private Integer driverId;

    @Mapping
    @ApiModelProperty("司机姓名")
    private String driverName;

    @Mapping
    @ApiModelProperty("手机号")
    private String phoneNo;

    @Mapping
    @ApiModelProperty("电子围栏Id")
    private Long blockId;

    @Mapping
    @ApiModelProperty("经度坐标")
    private Double[] lngs;

    @Mapping
    @ApiModelProperty("纬度坐标")
    private Double[] lats;
    /**
     * 电子围栏类型 1：司机线路2：区块管理
     */
    @Mapping
    @ApiModelProperty("电子围栏类型")
    private Integer blockType;

    @Mapping
    @ApiModelProperty("省Id")
    private String provinceId;

    @Mapping
    @ApiModelProperty("市Id")
    private String cityId;

    @Mapping
    @ApiModelProperty("县Id")
    private String countyId;

    @Mapping
    @ApiModelProperty("镇Id")
    private String townId;

    @Mapping
    @ApiModelProperty("备注")
    private String remarks;

    @Mapping
    @ApiModelProperty("创建人")
    private Integer createUser;

    @Mapping
    @ApiModelProperty("创建时间")
    private Long createTime;

    @Mapping
    @ApiModelProperty("修改人")
    private Integer updateUser;

    @Mapping
    @ApiModelProperty("修改时间")
    private Long updateTime;

    @Mapping
    @ApiModelProperty("省名称")
    private String provinceName;

    @Mapping
    @ApiModelProperty("市名称")
    private String cityName;

    @Mapping
    @ApiModelProperty("县名称")
    private String countyName;

    @Mapping
    @ApiModelProperty("镇名称")
    private String townName;

    @Mapping
    @ApiModelProperty("派送线路编号")
    private String deliveryCode;

    @ApiModelProperty("区块名称")
    private String blockName;


    public Boolean isValid(){
        return null != this.provinceName && null != this.cityName && null != this.lngs && null != this.lats;
    }
}
