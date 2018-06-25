package com.hivescm.tms.api.dto.common;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class SmsRequestDTO implements Serializable {

	private static final long serialVersionUID = 1L;
    //模板号", 必填
	public Long templateCode;
    //用户id", 必填
	public Integer companyId;
	//收货方电话号码
	public String fromPhone;
	//发货方电话号码
	public String toPhone;
}
