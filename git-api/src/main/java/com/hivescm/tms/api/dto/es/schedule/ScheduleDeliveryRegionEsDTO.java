package com.hivescm.tms.api.dto.es.schedule;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 司机派送线路详情表(区域详情表)
 *
 * @author qiushengkun
 * @company 蜂网供应链管理（上海）有限公司
 * @since 2017/8/29 13:56
 */
@Data
@ToString
public class ScheduleDeliveryRegionEsDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Logger
    @Mapping
    @ApiModelProperty("id")
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
    @ApiModelProperty("所属承运商")
    private String belongCarry;

    @Mapping
    @ApiModelProperty("线路Id")
    private Long routeId;

    @Mapping
    @ApiModelProperty("线路编号")
    private String routeCode;

    @Mapping
    @ApiModelProperty("线路名称")
    private String routeName;
    /**
     * 线路状态 1：停用2：启用
     */
    @Mapping
    @ApiModelProperty("线路状态")
    private Boolean routeState;
    //冗余字段
    @Mapping
    @ApiModelProperty("道路名称")
    private String roadNames;
    //冗余字段
    @Mapping
    @ApiModelProperty("开始号码")
    private String startNums;
    //冗余字段
    @Mapping
    @ApiModelProperty("结束号码")
    private String endNums;
    //冗余字段
    @Mapping
    @ApiModelProperty("覆盖区域")
    private String coverAreas;
    //冗余字段
    @Mapping
    @ApiModelProperty("覆盖类型")
    private String coverTypes;
    //冗余字段
    @Mapping
    @ApiModelProperty("号码类型")
    private String numberTypes;
    //冗余字段
    @Mapping
    @ApiModelProperty("站点号")
    private String siteNums;

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
    @ApiModelProperty("派送线路编号")
    private String deliveryCode;

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


}
