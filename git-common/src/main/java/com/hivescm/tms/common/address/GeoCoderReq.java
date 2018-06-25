package com.hivescm.tms.common.address;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;
/**
 * GeoCoder 请求 
 * @author ke.huang
 * @date 2017年9月12日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class GeoCoderReq implements Serializable{
	private static final long serialVersionUID = -1797273029369677888L;
	private String accessUrl;
	private String ak;
	private String output;
	private String location;
}
