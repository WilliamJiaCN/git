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
public class CityLineCloudForDistanceGCReq extends CloudGCReq implements Serializable {
	private static final long serialVersionUID = 5139954438539203946L;

	/**
	 * 地址序号
	 */
	@ApiModelProperty("地址序号")
	private int sortNumber;

	/**
	 * 解析后地址
	 */
	@ApiModelProperty("解析后地址")
	private CloudGCRes cloudGCRes;



}
