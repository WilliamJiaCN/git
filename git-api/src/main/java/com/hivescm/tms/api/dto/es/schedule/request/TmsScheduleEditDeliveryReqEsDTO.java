package com.hivescm.tms.api.dto.es.schedule.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 用于修改司机派送线路信息
 */
@Data
@ToString
public class TmsScheduleEditDeliveryReqEsDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("覆盖区域Id列表")
    private List<String> coverAreaIdList;

    @ApiModelProperty("覆盖区域名臣列表")
    private List<String>  coverAreaList;

    @ApiModelProperty("覆盖类型Id列表")
    private List<String>  coverTypeIdList;

    @ApiModelProperty("覆盖类型列表")
    private List<String> coverTypeList;

    @ApiModelProperty("覆盖线路Id列表")
    private List<String> coverRouteIdList;

    @ApiModelProperty("覆盖线路列表")
    private List<String> coverRouteList;

    @ApiModelProperty("号码类型Id列表")
    private List<String> numberTypeIdList;

    @ApiModelProperty("号码类型列表")
    private List<String> numberTypeList;

    @ApiModelProperty("道路Id列表")
    private List<String> roadIdList;

    @ApiModelProperty("道路名称列表")
    private List<String> roadNameList;

    @ApiModelProperty("开始号码列表")
    private List<String> startNumList;

    @ApiModelProperty("结束号码列表")
    private List<String> endNumList;

    @ApiModelProperty("站点号列表")
    private List<String> siteNumList;

    @ApiModelProperty("关键词Id列表")
    private List<String> keyWordIdList;

    @ApiModelProperty("关键词列表")
    private List<String> keyWordList;

    @ApiModelProperty("电子围栏Id列表")
    private List<String> blockIdList;

    @ApiModelProperty("电子围栏名称列表")
    private List<String> blockNameList;

    @ApiModelProperty("站点号列表")
    private String remarks;

    @ApiModelProperty("更新人")
    private Integer updateUser;

    @ApiModelProperty("更新人姓名")
    private String updateUserName;

    @ApiModelProperty("更新时间")
    private Long updateTime;

    public TmsScheduleEditDeliveryReqEsDTO() {
    }

    public TmsScheduleEditDeliveryReqEsDTO(List<String> coverAreaIdList, List<String> coverAreaList, List<String> coverTypeIdList, List<String> coverTypeList, List<String> coverRouteIdList, List<String> coverRouteList, List<String> numberTypeIdList,
                                           List<String> numberTypeList, List<String> roadIdList, List<String> roadNameList, List<String> startNumList, List<String> endNumList, List<String> siteNumList, Integer updateUser
            , Long updateTime, String remarks, List<String> keyWordIdList, List<String> keyWordList, List<String> blockIdList, List<String> blockNameList, String updateUserName) {
        this.coverAreaIdList = coverAreaIdList;
        this.coverAreaList = coverAreaList;
        this.coverTypeIdList = coverTypeIdList;
        this.coverTypeList = coverTypeList;
        this.coverRouteIdList = coverRouteIdList;
        this.coverRouteList = coverRouteList;
        this.numberTypeIdList = numberTypeIdList;
        this.numberTypeList = numberTypeList;
        this.roadIdList = roadIdList;
        this.roadNameList = roadNameList;
        this.startNumList = startNumList;
        this.endNumList = endNumList;
        this.siteNumList = siteNumList;
        this.remarks=remarks;
        this.updateUser = updateUser;
        this.updateTime = updateTime;
        this.keyWordIdList=keyWordIdList;
        this.keyWordList=keyWordList;
        this.blockIdList=blockIdList;
        this.blockNameList=blockNameList;
        this.updateUserName = updateUserName;
    }
}
