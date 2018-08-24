package com.hivescm.tms.api.dto.es.ltlorder;


import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class UsuallySendGoodsEsDto {

	@Mapping
	@ApiModelProperty("主键")
    private Long id;
	@Mapping
	@ApiModelProperty("货主ID")
	private Long goodsOwnerId;
	@Mapping
	@ApiModelProperty("商品名称")
    private String goodsName;
	@Mapping
	@ApiModelProperty("商品类型  用于筛选承运商 数据字典")
    private String goodsType;
	@Mapping
	@ApiModelProperty("重量")
    private Double weight;
	@Mapping
	@ApiModelProperty("体积")
    private Double volume;
	@Mapping
	@ApiModelProperty("数量(件数)")
	private Integer amount;
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
