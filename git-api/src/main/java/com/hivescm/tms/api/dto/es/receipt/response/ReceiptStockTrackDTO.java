package com.hivescm.tms.api.dto.es.receipt.response;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 回单轨迹response DTO
 * @author ke.huang
 * @date 2018年4月9日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class ReceiptStockTrackDTO implements Serializable{
	private static final long serialVersionUID = 2804646913891951127L;
	
	@ApiModelProperty("操作时间")
	private Long operTime;
	@ApiModelProperty("网点名称")
	private String orgName;
	@ApiModelProperty("状态")
	private String status;
	@ApiModelProperty("收件人")
	private String receiveUserName;
	@ApiModelProperty("手机号码(发放信息)")
	private String phone;
	@ApiModelProperty("地址(发放信息)")
	private String address;
	
	public ReceiptStockTrackDTO() {
		super();
	}

	//回单回收、接收节点
	public ReceiptStockTrackDTO(Long operTime, String orgName, String status) {
		super();
		this.operTime = operTime;
		this.orgName = orgName;
		this.status = status;
	}

	//回单寄出节点
	public ReceiptStockTrackDTO(Long operTime, String orgName, String status, String receiveUserName) {
		super();
		this.operTime = operTime;
		this.orgName = orgName;
		this.status = status;
		this.receiveUserName = receiveUserName;
	}

	//回单发放节点
	public ReceiptStockTrackDTO(Long operTime, String orgName, String status, String receiveUserName, String phone,
			String address) {
		super();
		this.operTime = operTime;
		this.orgName = orgName;
		this.status = status;
		this.receiveUserName = receiveUserName;
		this.phone = phone;
		this.address = address;
	}
	
	
	
	
	
	
}
