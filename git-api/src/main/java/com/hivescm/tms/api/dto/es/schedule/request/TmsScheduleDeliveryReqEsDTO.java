package com.hivescm.tms.api.dto.es.schedule.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;
import com.hivescm.tms.api.dto.es.schedule.TmsScheduleBlockEsDTO;
import com.hivescm.tms.api.dto.es.schedule.TmsScheduleKeyWordEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 关键词请求类用于新增与修改
 *
 * @author qiushengkun
 * @company 蜂网供应链管理（上海）有限公司
 * @since 2017/8/29 13:56
 */
@Data
@ToString
public class TmsScheduleDeliveryReqEsDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    //仅用于修改司机派送线路
    @Logger
    @Mapping
    @ApiModelProperty("司机派送线路Id")
    private Long id;
    @Logger
    @Required
    @Mapping
    @ApiModelProperty("公司Id")
    private Long companyId;
    @Logger
    @Required
    @Mapping
    @ApiModelProperty("司机Id")
    private Integer driverId;
    @Required
    @Mapping
    @ApiModelProperty("司机姓名")
    private String driverName;
    @Required
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
    @ApiModelProperty("创建人姓名")
    private String createUserName;
    @Mapping
    @ApiModelProperty("修改人")
    private Integer updateUser;
    @Mapping
    @ApiModelProperty("修改人姓名")
    private String updateUserName;
    //仅用于修改关键词
    @Mapping
    @ApiModelProperty("关键词区域Id")
    private String keyWordAreaId;
    @Mapping
    @ApiModelProperty("电子围栏区域Id")
    private String blockAreaId;

    @ApiModelProperty("关键词列表")
    private List<TmsScheduleKeyWordEsDTO> keyWordList;

    @ApiModelProperty("电子围栏列表")
    private List<TmsScheduleBlockEsDTO> blockList;
    @Required
    @ApiModelProperty("区域列表")
    private List<TmsScheduleRegionReqDTO> regionList;

    public TmsScheduleDeliveryReqEsDTO() {
    }

    public TmsScheduleDeliveryReqEsDTO(Long companyId, Integer driverId, String driverName, String phoneNo, Integer belongCarry, String carryName, Integer createUser) {
        this.companyId = companyId;
        this.driverId = driverId;
        this.driverName = driverName;
        this.phoneNo = phoneNo;
        this.belongCarry = belongCarry;
        this.carryName = carryName;
        this.createUser = createUser;
    }
}
