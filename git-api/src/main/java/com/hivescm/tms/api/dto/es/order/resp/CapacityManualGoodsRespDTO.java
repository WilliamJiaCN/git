package com.hivescm.tms.api.dto.es.order.resp;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ToString
public class CapacityManualGoodsRespDTO implements Serializable{

	
	private static final long serialVersionUID = 1L;

	@Mapping
	@ApiModelProperty("货物id")
	private Long id;
	@Mapping
	@ApiModelProperty("商品类型")
    private String goodsType;
	@Mapping
	@ApiModelProperty("商品编码")
    private String goodsCode;
	@Mapping
	@ApiModelProperty("重量")
    private BigDecimal weight;
	@Mapping
	@ApiModelProperty("体积")
    private BigDecimal volume;
	@Mapping
	@ApiModelProperty("商品名称")
    private String goodsName;
	@Mapping
	@ApiModelProperty("数量(件数)")
    private Integer amount;
}
