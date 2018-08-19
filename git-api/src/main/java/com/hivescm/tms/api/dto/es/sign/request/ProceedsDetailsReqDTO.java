package com.hivescm.tms.api.dto.es.sign.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 * 
* <p>Title:ProceedsDetailsReqDTO </p>
* <p>Description:收款详情 </p>
* <p>Company: 蜂网供应链（上海）有限公司</p> 
* @author 王小雪 
* @date 下午5:46:37
 */
@Data
@ToString
public class ProceedsDetailsReqDTO implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	@Mapping() @Logger
	@ApiModelProperty("支付订单号")
	private String orderCode;
	@Mapping()
	@ApiModelProperty("支付方式(1 = WXINITIATIVE,2 = ZFBINITIATIVE)")
	private int paymentMode=1;
	
	@Mapping()
	@ApiModelProperty("订单金额")
	private String amountMoney;
	@Mapping()
	@ApiModelProperty("付款用户id")
	private String userId;
	
	@Mapping()
	@ApiModelProperty("业务平台编号") @Logger
	private String businessCode="4000";
	
	@Mapping()
	@ApiModelProperty("运费")
    private BigDecimal freight;
	
}
