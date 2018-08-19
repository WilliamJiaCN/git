package com.hivescm.tms.common.address;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * GeoCoder返回地址信息
 * @author ke.huang
 * @date 2017年9月12日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class GeoCoder implements Serializable{
	private static final long serialVersionUID = 8588955989173151353L;
	private Location location;//经纬度
	private String formatted_address;
	private String business;
	private AddressComponents addressComponent;
	private String[] pois;
	private String[] poiRegions;
	private String sematic_description;
	private Integer cityCode;
	private String[] roads;
}
