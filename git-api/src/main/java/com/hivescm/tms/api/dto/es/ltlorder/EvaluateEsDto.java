package com.hivescm.tms.api.dto.es.ltlorder;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class EvaluateEsDto {

	@Mapping
	@ApiModelProperty("主键")
    private Long id;
	@Mapping
	@ApiModelProperty("订单号")
	private String orderCode;
	@Mapping
	@ApiModelProperty("货主ID")
	private Long goodsOwnerId;
	@Mapping
	@ApiModelProperty("服务星级")
    private Integer serviceLevel;
	@Mapping
	@ApiModelProperty("物流星级")
    private Integer logisticsLevel;
	@Mapping
	@ApiModelProperty("评价内容")
    private String evaContent;
	@Mapping
	@ApiModelProperty("创建人")
    private Integer createUser;
	@Mapping
	@ApiModelProperty("创建时间")
	private Long createTime;
	@Mapping
	@ApiModelProperty("修改人")
	private Integer updateUser;
	@Mapping
	@ApiModelProperty("修改时间")
	private Long updateTime;
}
