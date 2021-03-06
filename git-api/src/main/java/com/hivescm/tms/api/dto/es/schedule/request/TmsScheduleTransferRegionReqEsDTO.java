package com.hivescm.tms.api.dto.es.schedule.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 转移复制请求类
 *
 * @author qiushengkun
 * @since 2017/8/26 17:34
 */
@Data
@ToString
public class TmsScheduleTransferRegionReqEsDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Logger
    @Mapping
    @ApiModelProperty("公司Id")
    private Long companyId;
    @Mapping
    @ApiModelProperty("操作类型")
    private Integer type;
    //来源司机ID
    @Logger
    @Mapping
    @ApiModelProperty("司机Id")
    private Integer driverId;
    //来源司机姓名
    @Mapping
    @ApiModelProperty("司机姓名")
    private String driverName;
    //来源司机手机号
    @Mapping
    @ApiModelProperty("手机号")
    private String phoneNo;
    //来源司机所属承运商
    @Mapping
    @ApiModelProperty("所属承运商")
    private Integer belongCarry;
    //来源司机所属承运商名称
    @Mapping
    @ApiModelProperty("承运商名称")
    private String carryName;
    @Mapping
    @ApiModelProperty("创建人")
    private Integer createUser;
    @Mapping
    @ApiModelProperty("创建人姓名")
    private String createUserName;

    @ApiModelProperty("目标司机列表")
    List<TmsScheduleTargetDriverEsDTO> targetDriverList;
    @ApiModelProperty("区域列表")
    private List<TmsScheduleRegionReqDTO> list;
}
