package com.hivescm.tms.api.dto.es.receipt;

import com.hivescm.framework.common.exception.ExceptionFactory;
import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.tms.api.enums.bill.receipt.ReceiptExceptionStatusEnum;
import com.hivescm.tms.api.enums.bill.receipt.ReceiptRequirmentTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 回单发放接收批次详情
 * @author ke.huang
 * @date 2018年3月23日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class ReceiptTransmitReceiveDetailEsDTO implements Serializable,Cloneable{
	private static final long serialVersionUID = 3436854392653286675L;
	@Logger @Mapping
	@ApiModelProperty("主键")
	private Long id;
	@Logger @Mapping
	@ApiModelProperty("公司ID")
	private Long companyId;
	@Logger @Mapping
	@ApiModelProperty("寄出接收批次id")
    private Long transmitReceiveId;
	@Mapping
	@ApiModelProperty("运单号")
    private String waybillCode;
	@Mapping
	@ApiModelProperty("运单ID")
    private Long waybillId;
	@Mapping
	@ApiModelProperty("接收备注")
    private String receiptRemark;
	@Mapping
	@ApiModelProperty("快递分摊费用")
    private BigDecimal expressFee;
	@Mapping
	@ApiModelProperty("寄出备注")
    private String transmitRemark;
	@Mapping
	@ApiModelProperty("外发公司")
	private String externalCompanyName;
	@Mapping
    @ApiModelProperty("创建人")
	private Integer createUser;
	@Mapping
	@ApiModelProperty("创建时间")
    private Long createTime;
	@Mapping
	@ApiModelProperty("修改人")
    private Integer updateUser;
	@Mapping
	@ApiModelProperty("修改时间")
    private Long updateTime;
	@Mapping("isDelete")
	@ApiModelProperty("是否删除")
    private Boolean idelete;
	@Mapping
	@ApiModelProperty("回单状态")
	private String status;
	
	/**冗余名称*/
	@Mapping
	@ApiModelProperty("创建人名称") 
	private String createUserName;
	@Mapping
	@ApiModelProperty("修改人名称") 
	private String updateUserName;
	
	/**冗余回单库存*/
	@ApiModelProperty("库存ID")
	@Mapping
	private Long stockId;
	@ApiModelProperty("回单要求")
    private ReceiptRequirmentTypeEnum requirement;
	@Mapping
	@ApiModelProperty("回单份数")
    private Integer num;
	@Logger @Mapping
	@ApiModelProperty("回单编号")
    private String code;
	@ApiModelProperty("货物异常状态")
    private ReceiptExceptionStatusEnum exceptionStatus;
	@Mapping @ApiModelProperty("接收时间")
    private Long receiveTime;
	@Mapping @ApiModelProperty("寄出时间")
    private Long transmitTime;
	@Mapping @ApiModelProperty("寄出网点ID")
	private Long transmitOrgId;
	@Mapping @ApiModelProperty("寄出网点名称")
	private String transmitOrgName;
	@Mapping @ApiModelProperty("接收网点名称")
	private String receiveOrgName;
	@Mapping @ApiModelProperty("接收网点")
    private Long receiveOrgId;
	@Mapping @ApiModelProperty("寄出批次")
    private String transmitBatchCode;
	@Mapping @ApiModelProperty("快递号/车牌号")
    private String expressVehicleNum;
	
	/**冗余运单*/
	/**
	 * 发货网点ID	
	 */
	private @Mapping  @ApiModelProperty("发货网点ID	") Integer invoiceOrgId;
	/**
	 * 到达网点id	
	 */
	private @Mapping  @ApiModelProperty("到达网点id")Integer destOrgId;
	/**
	 * 发货网点名称	
	 */
	private @Mapping  @ApiModelProperty("发货网点名称	")String invoiceOrgName;
	/**
	 * 目的地名称	
	 */
	private @Mapping  @ApiModelProperty("目的地名称")String destName;
	/**
	 * 运输线路名称	
	 */
	private @Mapping  @ApiModelProperty("运输线路名称	")String lineName;
	/**
	 * 到达网点名称	
	 */
	private @Mapping  @ApiModelProperty("到达网点名称	")String destOrgName;
	/**
	 * 发货方名称		
	 */
	private @Mapping  @ApiModelProperty("发货方名称")String invoiceCompany;
	/**
	 * 发货人		
	 */
	private @Mapping  @ApiModelProperty("发货人")String invoiceUser;
	/**
	 * 发货人电话		
	 */
	private @Mapping  @ApiModelProperty("发货人电话")String invoiceTelNo;
	/**
	 * 发货人手机号码	
	 */
	private @Mapping  @ApiModelProperty("发货人手机号码")String invoiceMobleNo;
	/**
	 * 发货人详细地址	 	
	 */
	private @Mapping  @ApiModelProperty("发货人详细地址")String invoiceAddress;
	/**
	 * 收货方名称	
	 */
	private @Mapping  @ApiModelProperty("收货方名称")String receiptCompany;
	/**
	 * 收货人		
	 */
	private @Mapping  @ApiModelProperty("收货人")String receiptUser;
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
	 * 商品名称 ,“/”间隔
	 */
	private @Mapping @ApiModelProperty("商品名称") String goodsName;
	/**
	 * 包装名称 ,“/”间隔
	 */
    private @Mapping  @ApiModelProperty("包装名称") String packingName;
    /**
    * 总体积
    */
    private @Mapping  @ApiModelProperty("总体积") BigDecimal volume;
    /**
    * 总重量
    */
    private @Mapping  @ApiModelProperty("总重量") BigDecimal weight;
    /**
    * 总件数
    */
    private @Mapping  @ApiModelProperty("总件数") Integer totalNum;
    /**
	 * 派送方式
	 */
    private @Mapping @ApiModelProperty("配送方式") Integer distributionType;
    /**
	 * 付款方式名称
	 */
	private @Mapping @ApiModelProperty("付款方式名称") String payWayName;
	/**
	 * 总运费	
	 */
	private @Mapping  @ApiModelProperty("总运费")BigDecimal totalFee;
	
	
	@Override
	public ReceiptTransmitReceiveDetailEsDTO clone() {
		try {
			return (ReceiptTransmitReceiveDetailEsDTO)super.clone();
		} catch (CloneNotSupportedException e) {
			throw ExceptionFactory.ex(e);
		}
	}
}