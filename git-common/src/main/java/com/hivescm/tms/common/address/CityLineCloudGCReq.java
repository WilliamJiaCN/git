package com.hivescm.tms.common.address;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 地理编码请求
 *
 * @author LiuQiang
 * @company 蜂网供应链管理（上海）有限公司
 * @since 2017/8/10
 */
@Data
@ToString
public class CityLineCloudGCReq extends CloudGCReq implements Serializable {
	private static final long serialVersionUID = 5139954438539203946L;

	/**
	 * 运单编号
	 */
	@ApiModelProperty("运单编号")
	private String waybillCode;

	/**
	 * 解析后地址
	 */
	@ApiModelProperty("解析后地址")
	private CloudGCRes cloudGCRes;



}
