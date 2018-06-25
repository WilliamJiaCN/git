package com.hivescm.tms.api.dto.es.schedule.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 线路请求类 用于新增与修改
 *
 * @author qiushengkun
 * @since 2017/8/26 17:34
 */
@Data
@ToString
public class TmsScheduleRegionReqEsDTO implements Serializable {
    private static final long serialVersionUID = 1L;
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
    private Integer belongCarry;
    @Mapping
    @ApiModelProperty("承运商名称")
    private String carryName;
    @Mapping
    @ApiModelProperty("备注")
    private String remarks;
    @Mapping
    @ApiModelProperty("创建人")
    private Integer createUser;
    @Mapping
    @ApiModelProperty("创建人")
    private String createUserName;
    //用于修改
    @Mapping
    @ApiModelProperty("创建时间")
    private Long createTime;
    //用于修改
    @Mapping
    @ApiModelProperty("修改人")
    private Integer updateUser;
    @Mapping
    @ApiModelProperty("修改人姓名")
    private String updateUserName;
    @ApiModelProperty("区域列表")
    private List<TmsScheduleRegionReqDTO> list = new ArrayList<>();

    public TmsScheduleRegionReqEsDTO() {
    }

    public TmsScheduleRegionReqEsDTO(Long companyId, Integer driverId, String driverName, String phoneNo
            , Integer belongCarry, String carryName, Integer createUser,String createUserName) {
        this.companyId = companyId;
        this.driverId = driverId;
        this.driverName = driverName;
        this.phoneNo = phoneNo;
        this.belongCarry = belongCarry;
        this.carryName = carryName;
        this.createUser = createUser;
        this.createUserName = createUserName;
    }
}
