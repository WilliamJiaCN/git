package com.hivescm.tms.common.address;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 定位数据，经纬度、行政区划
 * @author ke.huang
 * @date 2017年9月2日
 * @company 蜂网供应链管理（上海）有限公司
 */
@EqualsAndHashCode(callSuper=false)
@Data
@ToString
public class FullLocation extends Location implements Serializable{
	private static final long serialVersionUID = 6541563024916574620L;
	private String province;//省
	private String city;//市
	private String district;//区
	private String street;//街道
	private String level;//地址级别（类型）
	private String address; //原地址
}
