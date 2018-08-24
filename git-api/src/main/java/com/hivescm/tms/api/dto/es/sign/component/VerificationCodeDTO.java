package com.hivescm.tms.api.dto.es.sign.component;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
/**
 * 
* <p>Title:VerificationCodeDTO </p>
* <p>Description:验证码信息 </p>
* <p>Company: 蜂网供应链（上海）有限公司</p> 
* @author 王小雪 
* @date 上午10:48:21
 */

@Data
@ToString
public class VerificationCodeDTO implements Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	//运单id
	@ApiModelProperty("运单id")
	private Long waybillId;
	//验证码
	@ApiModelProperty("验证码")
	private String code;
	//派车单id
	@ApiModelProperty("派车单id")
	private Long dispatcherId;

	
	
	

	
}
