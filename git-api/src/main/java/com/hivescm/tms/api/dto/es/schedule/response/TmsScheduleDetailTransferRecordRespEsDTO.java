package com.hivescm.tms.api.dto.es.schedule.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.tms.api.dto.es.schedule.TmsScheduleBlockEsDTO;
import com.hivescm.tms.api.dto.es.schedule.TmsScheduleKeyWordEsDTO;
import com.hivescm.tms.api.dto.es.schedule.request.TmsScheduleRegionReqDTO;
import com.hivescm.tms.api.dto.es.schedule.request.TmsScheduleTargetDriverEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 区域转移详情响应类
 *
 * @author qiushengkun
 * @since 2017/8/26 17:34
 */
@Data
@ToString
public class TmsScheduleDetailTransferRecordRespEsDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Mapping
    @ApiModelProperty("公司Id")
    private Long companyId;
    //来源司机Id
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
    @ApiModelProperty("目标司机列表")
    List<TmsScheduleTargetDriverEsDTO> targetDriverList = new ArrayList<>();
    @ApiModelProperty("区块列表")
    private List<TmsScheduleBlockEsDTO> blockList = new ArrayList<>();
    @ApiModelProperty("关键词列表")
    private List<TmsScheduleKeyWordEsDTO> keyWordList = new ArrayList<>();
    @ApiModelProperty("区域列表")
    private List<TmsScheduleRegionReqDTO> regionList = new ArrayList<>();
}
