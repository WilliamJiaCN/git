package com.hivescm.tms.api.dto.es.transport.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 单票添加返回信息
 * 
 * @author 鲁婷婷
 * @since 2018/3/30
 */
@Data
@ToString
public class BranchDTO implements Serializable {
	private static final long serialVersionUID = -532151097077810783L;

	/**
	 * 公司Id
	 */
	@ApiModelProperty(value = "网点Id", notes = "前端调用时不传")
	private Long orgId;

	/**
	 * 操作用户ID
	 */
	@ApiModelProperty(value = "网点名称", notes = "前端调用时不传")
	private String orgName;


}
