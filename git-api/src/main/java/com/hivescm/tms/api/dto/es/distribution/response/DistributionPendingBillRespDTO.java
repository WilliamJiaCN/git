package com.hivescm.tms.api.dto.es.distribution.response;

import java.io.Serializable;
import java.util.Date;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author  boqiang.deng
 * @company 蜂网供应链管理（上海）有限公司
 * @since   2017/8/11
*/
@Data
@ToString
public class DistributionPendingBillRespDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	

	/**
	 * 运单状态	
	 */
	private @Mapping  @ApiModelProperty("运单状态")Integer status;
	/**
	 * 运单号	
	 */
	private @Mapping  @ApiModelProperty("运单号") String waybillCode;
	
	/**
	 * 运单id
	 */
	private @Mapping @ApiModelProperty("运单id") Long id;
	/**
	 * 客户单号	
	 */
	private @Mapping  @ApiModelProperty("客户单号")String customerOrderCode;
	/**
	 * 发货单位		
	 */
	private @Mapping  @ApiModelProperty("发货单位")String invoiceCompany;
	/**
	 * 发货人电话		
	 */
	private @Mapping  @ApiModelProperty("发货人电话")String invoiceTelNo;
	/**
	 * 发货人手机号码	
	 */
	private @Mapping  @ApiModelProperty("发货人手机号码")String invoiceMobleNo;
	/**
	 * 发货人		
	 */
	private @Mapping  @ApiModelProperty("发货人	")String invoiceUser;
	/**
	 * 发货人详细地址	 	
	 */
	private @Mapping  @ApiModelProperty("发货人详细地址")String invoiceAddress;
	/**
	 * 收货人		
	 */
	private @Mapping  @ApiModelProperty("收货人")String receiptUser;
	/**
	 * 收货单位		
	 */
	private @Mapping  @ApiModelProperty("收货单位")String receiptCompany;
	/**
	 * 收货人电话		
	 */
	private @Mapping  @ApiModelProperty("收货人电话")String receiptConsignorTelNo;
	/**
	 * 收货人手机号码		
	 */
	private @Mapping  @ApiModelProperty("收货人手机号码")String receiptConsignorMobleNo;
	/**
	 * 收货人详细地址		
	 */
	private @Mapping  @ApiModelProperty("收货人详细地址")String receiptAddress;
	 /**
     * 总件数
     */
    private @Mapping  @ApiModelProperty("总件数") Integer totalNum;
    /**
     * 总体积
     */
    private @Mapping  @ApiModelProperty("总体积") String volume;
    /**
     * 总重量
     */
    private @Mapping  @ApiModelProperty("总重量") String weight;
    /**
	 * 回单要求	
	 */
	@Mapping
	private @ApiModelProperty("回单要求") String backType;
	/**
	 * 回单份数	
	 */
	private @Mapping  @ApiModelProperty("回单份数") Integer backNum;
	/**
	 * 回单编号	
	 */
	private @Mapping  @ApiModelProperty("回单编号")String backCode;
	/**
	 * 付款方式
	 */
	private @Mapping @ApiModelProperty("付款方式") Integer payWay;
	/**
	 * 总运费	
	 */
	private @Mapping  @ApiModelProperty("总运费")String totalFee;
	/**
	 * 配送方式	
	 */
	private @Mapping  @ApiModelProperty("配送方式")Integer distributionType;
	/**
	 * 备注	
	 */
	private @Mapping  @ApiModelProperty("运单备注")String remark;
	/**
	 * 预计到达日期	
	 */
	private @Mapping  @ApiModelProperty("预计到达日期") Date arrivalTime;
	/**
	 * 录单日期
	 */
	private @Mapping  @ApiModelProperty("录单日期") Long orderDate;
	/**
     * 预约提货时间
     */
    private @Mapping  @ApiModelProperty("预约提货时间") Long deliveryPickTime;
    /**
     * 预约送货时间
     */
    private @Mapping  @ApiModelProperty("预约送货时间") Long deliverySendTime;
    /**
	 * 目的地名称	
	 */
	private @Mapping  @ApiModelProperty("目的地名称")String destName;
	/**
	 * 商品名称 ,“/”间隔
	 */
	@Mapping
	private @ApiModelProperty("商品名称") String goodsName;
	
	/************************************************************** 后加需要做另外查询  ***********************************************************/
	@Mapping
	private @ApiModelProperty("声明价值") String declaredValue;
	
	@Mapping
	private @ApiModelProperty("包装") String packages;
	
	@Mapping
	private @ApiModelProperty("执行任务") String taskName;
	
	@Mapping
	private @ApiModelProperty("送货费") String deliveryCharges;
	
	@Mapping
	private @ApiModelProperty("代收货款") String collectPayment;
	
	@Mapping
	@ApiModelProperty("接单时间")
	private Long acceptDate;
	
	@Mapping
	@ApiModelProperty("入库时长")
	private Long storageDuration;
	
	@Mapping
	@ApiModelProperty("入库时间")
	private Long storageTime;
	
	
}

