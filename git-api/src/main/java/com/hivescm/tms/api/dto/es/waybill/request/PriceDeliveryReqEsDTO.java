package com.hivescm.tms.api.dto.es.waybill.request;

import java.io.Serializable;

import com.hivescm.framework.logger.api.annotation.Logger;

import io.swagger.annotations.ApiModelProperty;

/**
 * 送货费价格请求DTO
 * @author ke.huang
 * @date 2017年7月28日
 * @company 蜂网供应链管理（上海）有限公司
 */
public class PriceDeliveryReqEsDTO implements Serializable{
	private static final long serialVersionUID = 8539513269381158916L;
	private @ApiModelProperty("分公司ID") @Logger int dotId;
	private @ApiModelProperty("省、市、区/县、乡镇") @Logger String address;
	private @ApiModelProperty("重量") double weight;
	private @ApiModelProperty("体积") double volume;
	private @ApiModelProperty("件数") int number;
	
	public int getDotId() {
		return dotId;
	}
	public void setDotId(int dotId) {
		this.dotId = dotId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getVolume() {
		return volume;
	}
	public void setVolume(double volume) {
		this.volume = volume;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	@Override
	public String toString() {
		return "PriceDeliveryRequestDTO [dotId=" + dotId + ", address=" + address + ", weight=" + weight + ", volume="
				+ volume + ", number=" + number + "]";
	}
}
