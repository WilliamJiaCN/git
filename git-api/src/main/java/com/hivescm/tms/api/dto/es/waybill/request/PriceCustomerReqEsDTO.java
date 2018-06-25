package com.hivescm.tms.api.dto.es.waybill.request;

import java.io.Serializable;

import com.hivescm.framework.logger.api.annotation.Logger;

import io.swagger.annotations.ApiModelProperty;

/**
 * 客户价格请求DTO
 * @author ke.huang
 * @date 2017年7月28日
 * @company 蜂网供应链管理（上海）有限公司
 */
public class PriceCustomerReqEsDTO implements Serializable{
	private static final long serialVersionUID = -3591921040380167021L;
	private @ApiModelProperty("网点ID") @Logger int dotId;
	private @ApiModelProperty("客户ID") @Logger int userId;
	private @ApiModelProperty("重量") double weight;
	private @ApiModelProperty("体积") double volume;
	private @ApiModelProperty("件数") int number;
	public int getDotId() {
		return dotId;
	}
	public void setDotId(int dotId) {
		this.dotId = dotId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
		return "PriceCustomerRequestDTO [dotId=" + dotId + ", userId=" + userId + ", weight=" + weight + ", volume="
				+ volume + ", number=" + number + "]";
	}
}
