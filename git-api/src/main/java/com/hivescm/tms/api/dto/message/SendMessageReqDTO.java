package com.hivescm.tms.api.dto.message;
/**
 * @author  boqiang.deng
 * @company 蜂网供应链管理（上海）有限公司
 * @since   2017/8/11
*/

import com.hivescm.tms.api.enums.biz.messsage.SmsSendTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class SendMessageReqDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	/**
	 * 运单id
	 */
	private Long waybillId;
	
	/**
	 *是否给发货方发短信
	 */
	private Boolean isSendinvoice;
	
	/**
	 *是否给收货方发短信
	 */
	private Boolean isSendReceipt;
	/**
	 *是否给收货方发短信
	 */
	@ApiModelProperty("只有需要发送验证码时传")
	private Integer signCode;
	
	/**
	 *是否给收货方发短信
	 */
	@ApiModelProperty("重发时为手动发送，其他为自动发送")
	private SmsSendTypeEnum sendType;

}

