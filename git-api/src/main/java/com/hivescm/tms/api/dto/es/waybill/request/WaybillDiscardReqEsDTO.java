package com.hivescm.tms.api.dto.es.waybill.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 运单作废DTO
 *
 * @author ke.huang
 * @date 2017年7月19日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class WaybillDiscardReqEsDTO implements Serializable {
    private static final long serialVersionUID = -1121322159628856917L;
    /**
     * 运单ID
     */
    @Logger
    @Required
    @ApiModelProperty(value="运单ID",required=true)
    @Mapping({"WaybillEsDTO.id"})
    private Long waybillId;

    @ApiModelProperty("公司ID")
    @Mapping
    private Long companyId;
    
    @ApiModelProperty("库存网点id")
    @Mapping
    @Required
    private Integer orgId;
    /**
     * 作废类型
     */
    @ApiModelProperty(value="作废类型",required=true)
    @Mapping
    @Required
    private Integer discardType;
    /**
     * 作废原因
     */
    @ApiModelProperty(value="作废原因",required=true)
    @Mapping
    @Required
    private String discardReason;
    
    @ApiModelProperty("修改人")
    @Mapping
    private Integer updateUser;
    
    @ApiModelProperty("修改人姓名")
    @Mapping
    private String updateUserName;
    
    @ApiModelProperty("订单号")
    @Mapping()
    private String orderCode;

}
