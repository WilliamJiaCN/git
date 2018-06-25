package com.hivescm.tms.api.dto.es.schedule;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 转移记录EsDTO
 *
 * @author qiushengkun
 * @company 蜂网供应链管理（上海）有限公司
 * @since 2017/8/29 13:56
 */
@Data
@ToString
public class TmsScheduleRecordEsDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Logger
    @Mapping
    @ApiModelProperty("记录Id")
    private Long id;
    @Logger
    @Mapping
    @ApiModelProperty("公司id")
    private Long companyId;
    @Logger
    @Mapping
    @ApiModelProperty("转移编号")
    private String recordCode;
    @Mapping
    @ApiModelProperty("操作类型1:转移2：全部转移3：复制4：全部复制")
    private Integer type;
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
    @ApiModelProperty("目标司机Ids")
    private String targetDriverIds;
    @Mapping
    @ApiModelProperty("目标司机s")
    private String targetDrivers;
    @Mapping
    @ApiModelProperty("目标司机手机号s")
    private String targetPhoneNos;
    @Mapping
    @ApiModelProperty("目标司机所属承运商")
    private String belongCarrys;
    @Mapping
    @ApiModelProperty("目标司机所属承运商名称")
    private String carryNames;
    @Mapping
    @ApiModelProperty("覆盖区域ids")
    private String coverAreaIds;
    @Mapping
    @ApiModelProperty("覆盖区域s")
    private String coverAreas;
    @Mapping
    @ApiModelProperty("覆盖类型Ids")
    private String coverTypeIds;
    @Mapping
    @ApiModelProperty("覆盖类型")
    private String coverTypes;
    @Mapping
    @ApiModelProperty("覆盖线路Ids")
    private String coverRouteIds;
    @Mapping
    @ApiModelProperty("覆盖线路s")
    private String coverRoutes;
    @Mapping
    @ApiModelProperty("关键词Ids")
    private String keyWordIds;
    @Mapping
    @ApiModelProperty("关键词s")
    private String keyWords;
    @Mapping
    @ApiModelProperty("电子围栏Ids")
    private String blockIds;
    @Mapping
    @ApiModelProperty("电子围栏名称")
    private String blockNames;
    //需要添加经纬度
    @Mapping
    @ApiModelProperty("号码类型Ids")
    private String numberTypeIds;
    @Mapping
    @ApiModelProperty("号码类型s")
    private String numberTypes;
    @Mapping
    @ApiModelProperty("道路Ids")
    private String roadIds;
    @Mapping
    @ApiModelProperty("道路名称s")
    private String roadNames;
    @Mapping
    @ApiModelProperty("开始号码s")
    private String startNums;
    @Mapping
    @ApiModelProperty("结束号码s")
    private String endNums;
    @Mapping
    @ApiModelProperty("站点号s")
    private String siteNums;
    @Mapping
    @ApiModelProperty("创建人")
    private Integer createUser;
    @Mapping
    @ApiModelProperty("创建人姓名")
    private String createUserName;
    @Mapping
    @ApiModelProperty("创建时间")
    private Long createTime;

    public TmsScheduleRecordEsDTO() {
    }

    public TmsScheduleRecordEsDTO(Long id, Long companyId, String recordCode, Integer type, Integer driverId, String driverName, String phoneNo, Integer belongCarry, String carryName, String targetDriverIds, String targetDrivers, String targetPhoneNos, String belongCarrys, String carryNames, String coverAreaIds, String coverAreas
            , String coverTypeIds, String coverTypes, String coverRouteIds, String coverRoutes, String keyWordIds, String keyWords, String blockIds, String blockNames, String numberTypeIds, String numberTypes, String roadIds, String roadNames, String startNums, String endNums, String siteNums, Integer createUser, Long createTime,String createUserName) {
        this.id = id;
        this.companyId = companyId;
        this.recordCode = recordCode;
        this.type = type;
        this.driverId = driverId;
        this.driverName = driverName;
        this.phoneNo = phoneNo;
        this.belongCarry = belongCarry;
        this.carryName = carryName;
        this.targetDriverIds = targetDriverIds;
        this.targetDrivers = targetDrivers;
        this.targetPhoneNos = targetPhoneNos;
        this.belongCarrys = belongCarrys;
        this.carryNames = carryNames;
        this.coverAreaIds = coverAreaIds;
        this.coverAreas = coverAreas;
        this.coverTypeIds = coverTypeIds;
        this.coverTypes = coverTypes;
        this.coverRouteIds = coverRouteIds;
        this.coverRoutes = coverRoutes;
        this.keyWordIds = keyWordIds;
        this.keyWords = keyWords;
        this.blockIds = blockIds;
        this.blockNames = blockNames;
        this.numberTypeIds = numberTypeIds;
        this.numberTypes = numberTypes;
        this.roadIds = roadIds;
        this.roadNames = roadNames;
        this.startNums = startNums;
        this.endNums = endNums;
        this.siteNums = siteNums;
        this.createUser = createUser;
        this.createTime = createTime;
        this.createUserName = createUserName;
    }
}
