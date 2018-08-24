/**
 * 
 */
package com.hivescm.tms.api.dto.es.sign.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author  boqiang.deng
 * @date    2018年5月5日
 * @company 蜂网供应链管理（上海）有限公司
 * 
 */
@Data
@ToString
public class SelfSignRespDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	@ApiModelProperty("运单信息")
	private SimpleWaybill4SignRespDTO waybill;

	@Mapping
	@ApiModelProperty("签收主表id")
	private Long id;
	@Mapping
	@ApiModelProperty("签收批次号")
	private String signBatchNumber;	
	@Mapping
	@ApiModelProperty("签收人")
	private String signPeople;
	@Mapping
	@ApiModelProperty("签收人手机号")
	private String phoneNumber;
	@Mapping
	@ApiModelProperty("签收人身份证号	")
	private String idCard;
	@Mapping
	@ApiModelProperty("签收时间")
	private Long signTime;
	@Mapping
	@ApiModelProperty("签收类型 ")
	private Integer signType;
	@Mapping
	@ApiModelProperty("签收说明")
	private String 	signingInstructions;
	@Mapping
	@ApiModelProperty("签收照片	")
	private String signPic;
	@Mapping
	@ApiModelProperty("签收数量	")
	private Integer signNumber;
	@Mapping
	@ApiModelProperty("拒签数量	")
	private Integer refuseNumber;
	@Mapping
	@ApiModelProperty("签收重量")
	private BigDecimal signWeight;
	@Mapping
	@ApiModelProperty("签收体积")
	private BigDecimal signVolume;
	@Mapping
	@ApiModelProperty("未签件数	")
	private Integer unsignedNumber;
	@Mapping
	@ApiModelProperty("拒签原因 ")
	private String	refuseResion;
	@Mapping
	@ApiModelProperty("经办人id ")
	private Integer handler;
	@Mapping
	@ApiModelProperty("经办人名称 ")
	private String handlerName;
	@Mapping
	@ApiModelProperty("收银状态	")
	private Boolean iscashierConfirm;
	@Mapping
	@ApiModelProperty("签收状态")
	private Integer signStatus;

	@Mapping
	@ApiModelProperty("派送失败原因")
	private String deliveryFailureReason;

	@Mapping
	@ApiModelProperty("是否派送失败")
	private Boolean isDeliveryFailure;
	@Mapping
	@ApiModelProperty("派车单id")
	private Long dispatcherId;
	@Mapping
	@ApiModelProperty("运单id")
	private Long waybillId;
	@Mapping
	@ApiModelProperty("费用主键")
    private Long Fid;
	
	@ApiModelProperty("提货件数")
	private Integer deliveryNumber;
	 
	@Mapping
	@ApiModelProperty("累计拒签件数")
	private Integer cumulativeRefuseNumber;
	
	@Mapping 
	@ApiModelProperty("累计签收件数")
	private Integer cumulativeSignNumber;
	
	@Mapping
	@ApiModelProperty("待签收重量")
	private BigDecimal staySignWeight;
	
	@Mapping
	@ApiModelProperty("待签收体积")
	private BigDecimal staySignVolume;
	
	@Mapping
	@ApiModelProperty("待签收件数")
	private Integer staySignNumber;
	
	@Mapping
	@ApiModelProperty("货物名称")
	private String goodsName;
	
	@Mapping
	@ApiModelProperty("签收状态名称")
	private String signStatusName;
	
	@Mapping
	@ApiModelProperty("签收类型名称")
	private String signTypeName;

	@Mapping({"isCashierConfirm","SignDO.isCashierConfirm"})
	@ApiModelProperty("收银确认")
	private Boolean icashierConfirm;

	@Mapping
	@ApiModelProperty("正常签收状态名称")
	private String normalSignTypeName;
	/*****************************费用信息***************************************/
	@Mapping
	@ApiModelProperty("应收合计")
	private BigDecimal totalReceivable;
	
	@Mapping
	@ApiModelProperty("代收货款")
    private BigDecimal collectPayment;
	
	@Mapping
	@ApiModelProperty("到付送货费")
	private BigDecimal deliveryFee;
	
	@ApiModelProperty("到站其他费用")
	private BigDecimal otherFeeStation;
	
	@ApiModelProperty("实际费用")
	private BigDecimal actualCost;
	
	@Mapping
	@ApiModelProperty("到付运费")
    private BigDecimal toPay;
	
	@ApiModelProperty("声明价值")
	private BigDecimal declarationsValue;
	
}
