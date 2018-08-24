package com.hivescm.tms.common.address;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * Geo接口结果对象
 * @author ke.huang
 * @date 2017年9月12日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class GeoCoderResult implements Serializable{
	private static final long serialVersionUID = 227197568507929783L;
	private GeoCoder result;
}
