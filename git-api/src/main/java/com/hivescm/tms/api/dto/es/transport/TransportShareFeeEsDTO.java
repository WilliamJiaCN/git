package com.hivescm.tms.api.dto.es.transport;

import java.io.Serializable;
import java.math.BigDecimal;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author
 */
@Data
@ToString
public class TransportShareFeeEsDTO implements Serializable {
    /**
     * 主键
     */
    @Mapping
    @ApiModelProperty("主键")
    private Long id;

    /**
     * 公司id
     */
    @Mapping
    @ApiModelProperty("公司id")
    private Long companyId;

    /**
     * 运输单ID
     */
    @Mapping
    @ApiModelProperty("运输单ID")
    private Long transportId;

    /**
     * 运单ID
     */
    @Mapping
    @ApiModelProperty("运单ID")
    private Long waybillId;

    /**
     * 运输明细表 主键
     */
    @Mapping
    @ApiModelProperty("运输明细表 主键")
    private Long transportWaybillId;

    /**
     * 费用明细ID
     */
    @Mapping
    @ApiModelProperty("费用明细ID")
    private Long costDetailId;

    /**
     * 费用
     */
    @Mapping
    @ApiModelProperty("费用")
    private BigDecimal fee;

    /**
     * 创建人
     */
    @Mapping
    @ApiModelProperty("创建人")
    private Integer createUser;

    /**
     * 创建时间
     */
    @Mapping
    @ApiModelProperty("创建时间")
    private Long createTime;

    /**
     * 修改人
     */
    @Mapping
    @ApiModelProperty("修改人")
    private Integer updateUser;

    /**
     * 修改时间
     */
    @Mapping
    @ApiModelProperty("修改时间")
    private Long updateTime;

    private static final long serialVersionUID = 1L;


}