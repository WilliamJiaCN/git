package com.hivescm.tms.api.dto.es.ltlorder.resp;


import com.hivescm.framework.common.utils.StringUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Objects;

@Data
@ToString
public class OrderScanDto implements Comparable<OrderScanDto> {
	
	@ApiModelProperty("收发标识:1-发、2-收")
	private Integer flag;
	@ApiModelProperty("物流ID")
	private Integer companyId;
	@ApiModelProperty("物流公司简称")
	private String companyName;
	@ApiModelProperty("派送方式")
	private Integer distributionType;
	@ApiModelProperty("配送方式名称")
	private  String distributionTypeName;
	@ApiModelProperty("状态")
	private Integer status;
	@ApiModelProperty("状态名称")
	private  String statusName;
	@ApiModelProperty("发货人姓名")
    private String invoiceUserName;
	@ApiModelProperty("发货人手机号")
    private String invoiceUserMobile;
	@ApiModelProperty("发货地址")
    private String invoiceAddress;
	@ApiModelProperty("收货人姓名")
	private String receiptUserName;
	@ApiModelProperty("收货人手机号")
    private String receiptUserMobile;
	@ApiModelProperty("收货地址")
    private String receiptAddress;
	@ApiModelProperty("订单Id")
	private Long orderId;
	@ApiModelProperty("订单号")
	private String orderCode;
	@ApiModelProperty("运单Id")
	private Long waybillId;
	@ApiModelProperty("运单号")
	private String code;
	@ApiModelProperty("下单时间")
	private Long createTime;
	@ApiModelProperty("下单人")
	private Integer createUser;
	@ApiModelProperty("付款方式")
	private  Integer payWay;
	@ApiModelProperty("付款方式名称")
	private String payWayName;
	@ApiModelProperty("现付")
	private BigDecimal cashPay;
	@ApiModelProperty("月结")
	private BigDecimal monthlyPay;
	@ApiModelProperty("回单付")
	private BigDecimal receiptPay;
	@ApiModelProperty("到付")
	private BigDecimal cod;
	@ApiModelProperty("总运费")
	private BigDecimal totalFee;
	@ApiModelProperty("实际运费")
	private Double realFee;
	@ApiModelProperty("签收状态")
	private Integer signStatus;
	@ApiModelProperty("发货方式")
	private Integer deliveryType;
	
	
	@Override
	public int compareTo(OrderScanDto o) {
		if(equals(o)) {
			return 0;
		}else {
			return createTime-o.createTime<0?1:-1;
		}
	}
	
	@Override  
    public boolean equals(Object o) {  
        if (o == this) return true;  
        if (!(o instanceof OrderScanDto)) {  
            return false;  
        }  
        OrderScanDto order = (OrderScanDto) o;  
        if(StringUtils.isBlank(code))
    		return Objects.equals(orderCode, order.orderCode); 
    	else if(StringUtils.isBlank(orderCode))
    		return Objects.equals(code, order.code);
        return Objects.equals(orderCode, order.orderCode)&&Objects.equals(code, order.code);  
    }  
  
    @Override  
    public int hashCode() {
    	if(StringUtils.isBlank(code))
    		return Objects.hash(orderCode); 
    	else if(StringUtils.isBlank(orderCode))
    		return Objects.hash(code); 
        return Objects.hash(orderCode, code);  
    } 
}
