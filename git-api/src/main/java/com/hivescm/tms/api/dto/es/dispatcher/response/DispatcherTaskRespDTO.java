package com.hivescm.tms.api.dto.es.dispatcher.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author  lhf
 * @company 蜂网供应链管理（上海）有限公司
 * @since   2017/11/9
*/
@Data
@ToString
public class DispatcherTaskRespDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 收货人
	 */
	@Mapping
	@ApiModelProperty("收货人")
	private String receiptUser;
	/**
	 * 收货人详细地址
	 */
	private @Mapping @ApiModelProperty("收货人详细地址") String receiptAddress;
	
	/**
     * 发货人详细地址
     */
	private@Mapping @ApiModelProperty("发货人详细地址") String invoiceAddress;
    
	/**
     * 公司id
     */
    private @Mapping @ApiModelProperty("公司id") Long companyId;
    private @Mapping @ApiModelProperty("消退类型") Integer returnType;
    
   
    /**
     * 派车批次
     */
    private @Mapping @ApiModelProperty("派车批次") String batchCode;
    
    /**
     * 派车单ID
     */
    private @Mapping @ApiModelProperty("派车单ID") Long dispatcherId;
    
    /**
	 * 总体积(派车单总体积)
	 */
	private @Mapping @ApiModelProperty("装车体积(派车单总体积)") BigDecimal packageVolume;
	
	/**
	 * 装车重量(派车单总重量)
	 */
	private @Mapping @ApiModelProperty("装车重量(派车单总重量)") BigDecimal packageWeight;
	
	/**
	 * 总单数
	 */
	private @Mapping @ApiModelProperty("总单数") Integer totalNum;
	
	/**
	 * 单据类型
	 */
	private @Mapping @ApiModelProperty("单据类型 1=指派单 2=平台单 3=智能调度单") Integer billType;
	
	/**
     * 运单ID
     */
    private @Mapping @ApiModelProperty("运单ID") Long waybillId;
   
    /**
     * 订单运输的类型，包括：1销售订单、2销退单；自动接收（非必填）
     */
    private @Mapping @ApiModelProperty("订单类型") Integer orderType;
    
    /**
     * 是否为销退单:ture是  false否
     */
    private @Mapping @ApiModelProperty("订单类型") Boolean isXTOrder;
	
    /**
     * 运单号
     */
	@Mapping("code")
	@ApiModelProperty("运单号，已作废")
	private String waybillCode;

	/**
	 * 运单号
	 */
	@Mapping 
	@ApiModelProperty("运单号")
	private String code;
	
	/**
	 * 运单号
	 */
	@Mapping
	@ApiModelProperty("订单号")
	private String orderCode;

	/**
	 * 运单状态
	 */
	
	private @Mapping @ApiModelProperty("运单状态") Integer waybillStatus;
		
	/**
	 * 查询我的批次时候 此id为派车单id
	 */
	
	private @Mapping @ApiModelProperty("主键") Long id;
	
	/**
	 * 派车类型( 1=提货、2=送货、3=提送)
	 */
	private @Mapping @ApiModelProperty("派车类型( 1=提货、2=送货、3=提送)") Integer dispatcherType;

	/**
	 * 派车单状态(OT_DEPARTING(4, 未发车)对应前端 发车,DEPARTED(5, 已发车)对应前端 装货, COMMITED(9, 已交接)和SEND(10, 已配送)对应前端 签收") 
	 */
	@Mapping @ApiModelProperty("派车单状态(OT_DEPARTING(4, 未发车)对应前端 发车,DEPARTED(5, 已发车)对应前端 装货, COMMITED(9, 已交接)和SEND(10, 已配送)对应前端 签收")
	private Integer status;
	
	/**
	 * 装载率(体积)
	 */
	private @Mapping @ApiModelProperty("装载率(体积)") BigDecimal volumeLoading;

	/**
	 * 装载率(重量)
	 */
	private @Mapping @ApiModelProperty("装载率(重量)") BigDecimal weightLoading;

	/**
	 * 发布状态
	 */
	private @Mapping @ApiModelProperty("发布状态") Boolean releaseStatus;

	/**
	 * 发布时间
	 */
	private @Mapping @ApiModelProperty("发布时间") Long releaseTime;

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
	 * 来源类型
	 */
	private @Mapping @ApiModelProperty("来源类型") Integer sourceType;
	/**
	 * 派车成本
	 */
	private @Mapping @ApiModelProperty("派车成本") BigDecimal cost;
    /**
     * 运单Id集合
     */
    private @Mapping @ApiModelProperty("运单Id") List<Long> waybillIds;
	
    /**
     * 是否全部装车标识
     */
	private @Mapping @ApiModelProperty("是否全部装车标识") Boolean allLoadingFlag;
	
	private @Mapping @ApiModelProperty("已装车票数") Integer loadWaybillNum;
	
	@Mapping
	@ApiModelProperty("是否装车")
	private Boolean loaded;
	
    @Mapping
    @ApiModelProperty("销退单类型名称")
    private String returnTypeName;
    
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
