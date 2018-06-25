package com.hivescm.tms.common.address;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 返回距离地址
 *
 * @author m5itao
 * @company 蜂网供应链管理（上海）有限公司
 * @since 2017/12/8
 */
@Data
@ToString
public class CityLineDistanceRes implements Serializable {
	private static final long serialVersionUID = 5139954438539203946L;

	@ApiModelProperty("总距离")
	private BigDecimal totalDistance;

	@ApiModelProperty("距离单位是米")
	private String unit = "米";

}
