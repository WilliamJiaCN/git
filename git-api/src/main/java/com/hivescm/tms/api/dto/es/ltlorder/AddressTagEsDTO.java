package com.hivescm.tms.api.dto.es.ltlorder;


import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class AddressTagEsDTO {

	@Mapping
	@ApiModelProperty("主键")
    private Long id;
	@Required
	@Mapping
	@ApiModelProperty("用户ID")
	private Long userId;
	@Required
	@Mapping
	@ApiModelProperty("标签名称")
    private String tagName;
	@Mapping
	@ApiModelProperty("创建时间")
    private Long createTime;
}
