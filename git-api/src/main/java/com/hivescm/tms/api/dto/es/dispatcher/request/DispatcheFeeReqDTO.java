package com.hivescm.tms.api.dto.es.dispatcher.request;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 派车单新增请求实体用于外部接口调用
 * TmsDispatcherEsDTO
 *
 * @author lutingting
 * @date 2017年11月10日
 * @company 蜂网供应链管理
 */
@Data
@ToString
public class DispatcheFeeReqDTO implements Serializable {

    private static final long serialVersionUID = -6933611350149830800L;

    @Required
    @ApiModelProperty("派车单ID")
    private Long dispatcherId;
    @Required
    @ApiModelProperty("公司ID")
    private Long companyId;
    /**
     * 操作用户ID
     */
    @ApiModelProperty(value = "操作用户ID", notes = "前端调用时不传")
    private Integer opUserId;

    /**
     * 操作用户名称
     */
    @ApiModelProperty(value = "操作用户名称", notes = "前端调用时不传")
    private String opUserName;

    /**
     *费项ID"
     */
    @ApiModelProperty("删除的费项ID")
    private List<Long> feeId;
    @ApiModelProperty("新增修改的费用")
    private List<FeeReqEsDTO> list;
    
    @Required
    @ApiModelProperty("派车单总费用")
    private BigDecimal cost;

}
