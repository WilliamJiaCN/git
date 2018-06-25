package com.hivescm.tms.api.dto.es.fare;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.Data;
import lombok.ToString;

/**
 * 车费更改实体
 *
 * @author qsk
 * @date 2018年5月22日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class FareChangeEsDTO {
    @Mapping
    @ApiModelProperty("主键")
    private Long id;
    @Mapping
    @ApiModelProperty("更改批次号")
    private String changeCode;
    @Mapping
    @ApiModelProperty("公司Id")
    private @Required
    @Logger
    Long companyId;
    @Mapping
    @ApiModelProperty("公司名称")
    private @Required
    @Logger
    String companyName;
    @Mapping
    @ApiModelProperty("单据批次号")
    private @Required
    @Logger
    String billCode;
    @Mapping
    @ApiModelProperty("单据id")
    private @Required
    @Logger
    Long billId;
    @Mapping
    @ApiModelProperty("单据类型（2派车单3配载单 7到货确认单）")
    private @Required
    @Logger
    Integer billType;
    @Mapping
    @ApiModelProperty("状态 1未确认2已同意3已否决")
    private Integer status;
    @Mapping
    @ApiModelProperty("车费总额变更差异")
    private @Required
    @Logger
    String totalChange;
    @Mapping
    @ApiModelProperty("起始网点id")
    private Long startNetworkId;
    @Mapping
    @ApiModelProperty("到达网点id")
    private Long endNetworkId;
    @Mapping
    @ApiModelProperty("运输线路id")
    private Long lineId;
    @Mapping
    @ApiModelProperty("申请人")
    private Integer createUser;
    @Mapping
    @ApiModelProperty("申请时间")
    private Long createTime;
    @Mapping
    @ApiModelProperty("修改人")
    private Integer updateUser;
    @Mapping
    @ApiModelProperty("修改时间")
    private Long updateTime;
    @Mapping
    @ApiModelProperty("确认人")
    private Integer confirmUser;
    @Mapping
    @ApiModelProperty("确认时间")
    private Long confirmTime;
    //-----------------------Es--------------------------------
    @Mapping
    @ApiModelProperty("车费总额是否变更")
    private Boolean iChange;
    @Mapping
    @ApiModelProperty("更改内容")
    private String content;
    @Mapping
    @ApiModelProperty("更改备注")
    private String remarks;
    @Mapping
    @ApiModelProperty("操作说明")
    private String description;
    @Mapping
    @ApiModelProperty("起始网点名称")
    private String startNetworkName;
    @Mapping
    @ApiModelProperty("到达网点名称")
    private String endNetworkName;
    @Mapping
    @ApiModelProperty("运输线路名称")
    private String lineName;
    @Mapping
    @ApiModelProperty("申请人姓名")
    private String createUserName;
    @Mapping
    @ApiModelProperty("修改人姓名")
    private String updateUserName;
    @Mapping
    @ApiModelProperty("确认人姓名")
    private String confirmUserName;
    @Mapping
    @ApiModelProperty("申请网点id")
    private Integer createOrgId;
    @Mapping
    @ApiModelProperty("申请网点名称")
    private String createOrgName;
}