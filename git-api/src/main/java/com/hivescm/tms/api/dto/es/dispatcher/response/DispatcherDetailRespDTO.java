package com.hivescm.tms.api.dto.es.dispatcher.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author boqiang.deng
 * @company 蜂网供应链管理（上海）有限公司
 * @since 2017/8/11
 */
@Data
@ToString
public class DispatcherDetailRespDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Mapping
	@ApiModelProperty("收货地址")
	private String receiptAddress;
	/**
	 * 派车单明细状态
	 */
	@Mapping
	@ApiModelProperty("派车单明细状态")
	private Integer status;

	@Mapping
	@ApiModelProperty("销退单类型")
	private Integer returnType;
	/**
	 * 创建时间跟发布时间相等
	 */
	@Mapping
	@ApiModelProperty("创建时间")
	private Long createTime;

	@Mapping
	@ApiModelProperty("派车单成本")
	private String cost;
	/**
	 * 线路顺序
	 */
	@Mapping
	@ApiModelProperty("线路顺序")
	private Integer lineSort;
	  @Logger
	@Mapping
	@ApiModelProperty("派车单ID")
	private Long dispatcherId;

	/**
	 * 公司id
	 */
	@Mapping
	@ApiModelProperty("公司id")
	private Long companyId;

	/**
	 * 派车批次
	 */
	@Mapping
	@ApiModelProperty("派车批次")
	private String batchCode;
	/**
	 * 运单ID
	 */
	@Mapping
	@ApiModelProperty("运单ID")
	private Long waybillId;
	/**
	 * 总体积(派车单总体积)
	 */
	@Mapping
	@ApiModelProperty("装车体积(派车单总体积)")
	private BigDecimal packageVolume;

	/**
	 * 装车重量(派车单总重量)
	 */
	@Mapping
	@ApiModelProperty("装车重量(派车单总重量)")
	private BigDecimal packageWeight;

	/**
	 * 总单数
	 */
	@Mapping
	@ApiModelProperty("总单数")
	private Integer totalNum;
	/**
	 * 单据类型
	 */
	@Mapping
	@ApiModelProperty("单据类型 1=指派单 2=平台单 3=智能调度单")
	private Integer billType;

	/**
	 * 总件数
	 */
	@Mapping
	@ApiModelProperty("总件数")
	private Integer totalPackage;

	@Mapping
	@ApiModelProperty("指派网点")
	private Integer billOrgId;

	/**
	 * 运单状态
	 */
	@Mapping
	@ApiModelProperty("运单状态")
	private Integer waybillStatus;

	/**
	 * 发布状态
	 */
	@Mapping
	@ApiModelProperty("发布状态")
	private Boolean releaseStatus;

	/**
	 * 发布时间
	 */
	@Mapping
	@ApiModelProperty("发布时间")
	private Long releaseTime;
	/**
	 * 来源类型
	 */
	private @Mapping @ApiModelProperty("来源类型") Integer sourceType;

	/**
	 * 发货人详细地址
	 */
	private @Mapping @ApiModelProperty("发货人详细地址") String invoiceAddress;
	/**
	 * 运单号
	 */
	@Mapping("code")
	@ApiModelProperty("运单号，已作废")
	private String waybillCode;

	/**
	 * 运单号
	 */
	@ApiModelProperty("运单号")
	private String code;

	/**
	 * 此id为派车单明细id
	 */

	private @Mapping @ApiModelProperty("主键") Long id;

	/**
	 * 派车类型( 1=提货、2=送货、3=提送)
	 */
	@Mapping
	@ApiModelProperty("派车类型( 1=提货、2=送货、3=提送)")
	private Integer dispatcherType;
	/**
	 * 装载率(体积)
	 */

	private @Mapping @ApiModelProperty("装载率(体积)") BigDecimal volumeLoading;

	/**
	 * 装载率(重量)
	 */

	private @Mapping @ApiModelProperty("装载率(重量)") BigDecimal weightLoading;
	/**
	 * 总票数
	 */

	private @Mapping @ApiModelProperty("总票数") Integer totalWaybillNum;

	/**
	 * 总件数
	 */

	private @Mapping @ApiModelProperty("总件数") Integer totalGoodsNum;

	/**
	 * 总体积
	 */
	private @Mapping @ApiModelProperty("总体积") BigDecimal totalVolume;

	/**
	 * 总重量
	 */
	private @Mapping @ApiModelProperty("总重量") BigDecimal totalWeight;

	/**
	 * 是否装车
	 */
	@Mapping
	@ApiModelProperty("是否装车")
	private Boolean loaded;

	@Mapping
	@ApiModelProperty("订单类型")
	private Integer orderType;

	@Mapping
	@ApiModelProperty("订单号")
	private String orderCode;

	@Mapping
	@ApiModelProperty("订单来源名称")
	private String orderSourceName;
	@Mapping
	@ApiModelProperty("订单来源")
	private Integer orderSource;


	/**
	 * 指定配送时间为
	 */
	@Mapping
	@ApiModelProperty("指定日期")
	private String appointDate;
	@Mapping
	@ApiModelProperty("指定开始时间")
	private String appointStartTime;
	@Mapping
	@ApiModelProperty("指定结束时间")
	private String appointEndTime;

	/**
	 * 指定配送时间是上面三个拼接的
	 */
	@Mapping
	@ApiModelProperty("指定配送时间")
	private String appointDispatchTime;

	//2018-02-26(确认版) 需求
	/**
	 *门店：取运单的门店字段，若门店的值为空，则显示收货人的名字
	 */
	@Mapping
	@ApiModelProperty("门店名称")
	private String storeName;


	/**
	 * 是否加急
	 */
	@Mapping
	@ApiModelProperty("是否加急")
	private Boolean iemergency;

	@Mapping
	@ApiModelProperty("改配状态(1.改配审核中2.改配审核通过3.改配不通过)")
	private Integer changeSendStatus;
	@Mapping
	@ApiModelProperty("改配单类型")
	private Integer changeDispatcherType;

}
