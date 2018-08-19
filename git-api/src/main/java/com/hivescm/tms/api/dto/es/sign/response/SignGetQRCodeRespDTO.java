package com.hivescm.tms.api.dto.es.sign.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 签收获取二维码返回DTO
 * @author lhf
 * @date 2017年11月21日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class SignGetQRCodeRespDTO implements Serializable{
	 private static final long serialVersionUID = 1L;
	 /**
	  * tms平台订单号
	  */
	 private @Mapping @ApiModelProperty("tms平台订单号") String businessOrderNo;
	 /**
	  * 业务平台编号TMS:4000
	  */
	 private @Mapping @ApiModelProperty("业务平台编号TMS:4000") String businessCode;
	 /**
	  * 支付订单号（支付系统的id
	  */
	 private @Mapping @ApiModelProperty("支付订单号（支付系统的id") String orderNo;
	 /**
	  * 请求支付返回的二维码路径
	  */
	 private @Mapping @ApiModelProperty("请求支付返回的二维码路径") String qrcodeurl;
   
	



   
	
	
	
	
	

}
