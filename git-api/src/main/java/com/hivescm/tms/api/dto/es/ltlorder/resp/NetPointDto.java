package com.hivescm.tms.api.dto.es.ltlorder.resp;

import java.io.Serializable;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class NetPointDto implements Serializable,Comparable<NetPointDto>{
	
	private static final long serialVersionUID = 2676963289432019719L;
	
	@ApiModelProperty("物流公司ID")
	private Integer orgId;
	@ApiModelProperty("物流公司logo")
	private String logoUrl;
	@ApiModelProperty("物流公司简称")
	private String orgShortName;
	@ApiModelProperty("物流公司全称")
	private String orgName;
	@ApiModelProperty("网点Id")
	private Integer netId;
	@ApiModelProperty("网点名称")
	private String netName;
	@ApiModelProperty("网点地址")
	private String address;
	@ApiModelProperty("营业时间")
	private Long shopHour;
	@ApiModelProperty("电话")
	private String phone;
	@ApiModelProperty("经度")
	private Double longitude;
	@ApiModelProperty("纬度")
	private Double latitude;
	@ApiModelProperty("距离")
	private Double distince;
	
	
	@Override
	public int compareTo(NetPointDto o) {
		if(distince.equals(o.distince)) {
			return 0;
		}
		return distince-o.distince>0?1:-1;
	}
	

}
