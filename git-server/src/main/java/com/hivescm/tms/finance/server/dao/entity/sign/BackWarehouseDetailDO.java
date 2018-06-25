package com.hivescm.tms.finance.server.dao.entity.sign;

import java.io.Serializable;
import java.math.BigDecimal;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BackWarehouseDetailDO implements Serializable{
    
	private static final long serialVersionUID = 1L;

	@Mapping
	@ApiModelProperty("主键")
	private Long id;

	@Mapping
	@ApiModelProperty("公司id")
    private Long companyId;

	@Mapping
	@ApiModelProperty("反库主表id")
    private Long backWarehouseId;

	@Mapping
	@ApiModelProperty("运单id")
    private Long waybillId;

	@Mapping
	@ApiModelProperty("运单code")
	private String waybillCode;

	@Mapping
	@ApiModelProperty("反库货物名称")
    private String goodsName;

	@Mapping
	@ApiModelProperty("包装")
    private String packages;

	@Mapping
	@ApiModelProperty("件数")
    private Integer packageNum;

	@Mapping
	@ApiModelProperty("重量")
    private BigDecimal weight;

	@Mapping
	@ApiModelProperty("体积")
    private BigDecimal volume;

	@Mapping
	@ApiModelProperty("创建人")
    private Integer createUser;

	@Mapping
	@ApiModelProperty("创建时间")
    private Long createTime;

	@Mapping
	@ApiModelProperty("修改时间")
    private Long updateTime;

	@Mapping
	@ApiModelProperty("修改人")
    private Integer updateUser;
}