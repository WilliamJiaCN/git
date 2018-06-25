package com.hivescm.tms.common.address;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 根据顺序号排序，计算距离
 *
 * @author m5itao
 * @company 蜂网供应链管理（上海）有限公司
 * @since 2017/12/8
 */
@Data
@ToString
public class CityLineDistanceReq implements Serializable {
	private static final long serialVersionUID = 5139954438539203946L;

	/**
	 * 仓库地址
	 */
	@ApiModelProperty("仓库地址")
	private String wareHouseAddress;

	/**
	 * 仓库所在城市名称
	 */
	@ApiModelProperty("仓库所在城市名称")
	private String city;

	/**
	 * 仓库解析后地址
	 */
	@ApiModelProperty("仓库解析后地址")
	private CloudGCRes wareHouseCloudGCRes;

	/**
	 * 城市
	 */
	@ApiModelProperty("地址列表及排序号")
	private List<CityLineCloudForDistanceGCReq> CityLineCloudForDistanceGCReqList;



}
