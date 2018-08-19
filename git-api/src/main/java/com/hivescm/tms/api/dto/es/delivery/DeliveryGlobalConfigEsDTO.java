package com.hivescm.tms.api.dto.es.delivery;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 全局配置
 * @author ke.huang
 * @date 2017年10月20日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class DeliveryGlobalConfigEsDTO implements Serializable{
	private static final long serialVersionUID = 7107569694968243428L;
	@Mapping
	@ApiModelProperty("主键")
    private Long id;
	@Mapping
	@ApiModelProperty("市ID")
    private String cityId;
	@Mapping({"isTimelyAllocation"})
	@ApiModelProperty("是否及时分配运力[默认]")
    private Boolean itimelyAllocation;
	@Mapping({"isTransportAllocation"})
	@ApiModelProperty("是否按运输线路配送时间分配")
    private Boolean itransportAllocation;
	@Mapping({"isOrderNumberAllocation"})
	@ApiModelProperty("是否按订单数量分配")
    private Boolean iorderNumberAllocation;
	@Mapping({"isTimerRateAllocation"})
	@ApiModelProperty("是否按时间频率分配")
    private Boolean itimerRateAllocation;
	@Mapping({"isTimersAllocation"})
	@ApiModelProperty("是否按固定时间分配")
    private Boolean itimersAllocation;
	@Mapping
	@ApiModelProperty("订单数量")
    private Integer orderNumber;
	@Mapping({"isSetMaxTimerLimit"})
	@ApiModelProperty("是否设置最大时间限制")
    private Boolean isetMaxTimerLimit;
	@Mapping
	@ApiModelProperty("最大时间限制(小时)")
    private BigDecimal maxTimerLimit;
	@Mapping
	@ApiModelProperty("开始分配时间")
    private String startAllotTimer;
	@Mapping
	@ApiModelProperty("结束分配时间")
    private String endAllotTimer;
	@Mapping
	@ApiModelProperty("时间频率(分钟)")
    private Integer allotTimerRate;
	@Mapping
	@ApiModelProperty("分配时间")
    private String allotTimes;
	@Mapping
	@ApiModelProperty("接单时限(分钟)[默认30]")
    private Integer orderTakingTimer;
	@Mapping({"isAutoTaking"})
	@ApiModelProperty("超时是否自动接收[默认false]")
    private Boolean iautoTaking;
	@Mapping({"isRangeLimit"})
	@ApiModelProperty("是否限制提货距离[默认false]")
    private Boolean irangeLimit;
	@Mapping({"isCarrierCreditLimit"})
	@ApiModelProperty("是否限制承运商信用[默认false]")
    private Boolean icarrierCreditLimit;
	@Mapping
	@ApiModelProperty("提货范围(km)")
    private BigDecimal rangeLimit;
	@Mapping
	@ApiModelProperty("承运商信用0-100")
    private Integer carrierCredit;
	@Mapping({"isPriorityAllocation"})
	@ApiModelProperty("是否按优先级分配[默认]")
    private Boolean ipriorityAllocation;
	@Mapping({"isScaleAllocation"})
	@ApiModelProperty("是否按比例分配")
    private Boolean iscaleAllocation;
	@Mapping({"isCarrierSortAllocation"})
	@ApiModelProperty("是否按承运商顺序分配")
    private Boolean icarrierSortAllocation;
	@Mapping
	@ApiModelProperty("优先级1=价格，2=时效，3=信用，4=距离")
    private String priorityAllocation;
	@Mapping({"isAutoCapacity"})
	@ApiModelProperty("是否自动分配")
	private Boolean iautoCapacity;
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
	
	
	/**
	 * 冗余字段
	 */
	@Mapping
	@ApiModelProperty("市名称")
	private String cityName;
	@ApiModelProperty("创建人姓名")
	private String createUserName;
	@Mapping
	@ApiModelProperty("更新人姓名")
	private String updateUserName;
	
	

    
}