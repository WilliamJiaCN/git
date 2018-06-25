package com.hivescm.tms.api.dto.es.schedule;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 线路门店关联表
 *
 * @author qiushengkun
 * @company 蜂网供应链管理（上海）有限公司
 * @since 2017/8/29 13:56
 */
@Data
@ToString
public class TmsScheduleStoreEsDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    @Logger
    @Mapping
    @ApiModelProperty("公司Id")
    private Long companyId;
    @Logger
    @Mapping
    @ApiModelProperty("门店ID")
    private Integer id;
    @Logger
    @Mapping
    @ApiModelProperty("门店编号")
    private String storeCode;
    @Mapping
    @ApiModelProperty("门店名称")
    private String storeName;
    // true 已被关联 false 未被关联
    @Mapping
    @ApiModelProperty("是否关联")
    private Boolean used;
    @Mapping
    @ApiModelProperty("主要联系人")
    private String contacts;
    @Mapping
    @ApiModelProperty("主要联系人电话")
    private String contactsPhoneNo;
    @Mapping
    @ApiModelProperty("门店地址ID")
    private String coverAreaId;
    @Mapping
    @ApiModelProperty("门店地址")
    private String coverArea;

}
