package com.hivescm.tms.api.dto.es.schedule.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.tms.api.dto.es.schedule.TmsScheduleBlockEsDTO;
import com.hivescm.tms.api.dto.es.schedule.TmsScheduleKeyWordEsDTO;
import com.hivescm.tms.api.dto.es.schedule.request.TmsScheduleRegionReqDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 关键词详情响应类
 *
 * @author qiushengkun
 * @company 蜂网供应链管理（上海）有限公司
 * @since 2017/8/29 13:56
 */
@Data
@ToString
public class TmsScheduleDetailDeliveryRespEsDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Mapping
    @ApiModelProperty("公司Id")
    private Long companyId;

    @Mapping
    @ApiModelProperty("派送线路Id")
    private Long id;
    @Mapping
    @ApiModelProperty("派送线路编号")
    private String deliveryCode;

    /**
     * 用于详情页面 停用启用
     * 启用状态 true 启用 false 停用
     */
    @Mapping
    @ApiModelProperty("启用状态")
    private Boolean used;
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

    @ApiModelProperty("关键词列表")
    private List<TmsScheduleKeyWordEsDTO> keyWordList = new ArrayList<>();

    @ApiModelProperty("区域列表")
    private List<TmsScheduleRegionReqDTO> regionList = new ArrayList<>();

    @ApiModelProperty("电子围栏列表")
    private List<TmsScheduleBlockEsDTO> blockList = new ArrayList<>();

}
