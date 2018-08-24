package com.hivescm.tms.api.dto.es.sign.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
/**
 * 查询支付结果返回DTO
 * @author lhf
 * @date 2017年11月21日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class SignPaymentQueryStatusRespDto implements Serializable{
	private static final long serialVersionUID = -8817987997192777103L;
	
	 /**
	  * 支付订单状态1处理中2成功3失败4未知
	  */
	 private @Mapping @ApiModelProperty("支付订单状态1处理中2成功3失败4未知") int orderStatus;
	 /**
	  * tms平台订单号
	  */
	 private @Mapping @ApiModelProperty("tms平台订单号") String businessOrderNo;
	 /**
	  * 支付订单号（支付系统的id
	  */
	 private @Mapping @ApiModelProperty("支付订单号（支付系统的id") String orderNo;
	 /**
	  * 是否需要重新获取二维码ture是false不是
	  */
	 private @Mapping @ApiModelProperty("是否需要重新获取二维码ture是false不是") Boolean isGetQRCode;
	 /**
	  * 重新生成二维码数据
	  */
	 private @Mapping @ApiModelProperty("重新生成二维码数据") SignGetQRCodeRespDTO signGetQRCodeRespDTO;
}
