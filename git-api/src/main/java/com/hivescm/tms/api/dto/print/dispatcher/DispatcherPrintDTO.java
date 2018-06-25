package com.hivescm.tms.api.dto.print.dispatcher;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

import com.hivescm.framework.entitymapping.annotation.Mapping;

/**
 * 派车单
 *
 * @author 李洪春
 * @since 2017/8/18 7:58
 */
@Data
@ToString
public class DispatcherPrintDTO implements Serializable {

	private static final long serialVersionUID = 1244274286695080984L;

	@ApiModelProperty("主键")
	private Long id;

	@ApiModelProperty("公司id")
	private Long companyId;

	@ApiModelProperty("派车批次")
	private String batchCode;

	@ApiModelProperty("发车时间")
	private Long dispatcherTime;

	@ApiModelProperty("车辆ID")
	private Integer vehicleId;
	
	@ApiModelProperty("车牌号码")
	private String vehicleNo;
	
	@ApiModelProperty("司机姓名")
	private String driverName;
	
	@ApiModelProperty("手机号码")
	private String phoneNo;
	
	@ApiModelProperty("预计倒库时间")
	private Long estimatedStorageTime;
	
	@ApiModelProperty("备注")
	private String remark;
	
	@ApiModelProperty("派车成本")
	private BigDecimal cost;

	@ApiModelProperty("提货人ID")
	private Integer consigneeId;

	@ApiModelProperty("提货人姓名")
	private String consigneeName;
	
	@ApiModelProperty("派车网点ID")
	private Integer branchId;
	@ApiModelProperty("派车网点名称")
	private String branchName;
	
	@ApiModelProperty("车型ID")
	private Integer vehicleModels;

	@ApiModelProperty("车型名称")
	private String vehicleModelName;
	
	@ApiModelProperty("仓库ID")
	private Long warehouseId;
	
	@ApiModelProperty("仓库名称")
	private String warehouseName;
	
	@ApiModelProperty("应收货款")
	private BigDecimal totalBusFee;
	/**
	 * 装载率(体积)
	 */
	@Mapping
	@ApiModelProperty("装载率(体积)")
	private BigDecimal volumeLoading;

	/**
	 * 装载率(重量)
	 */
	@ApiModelProperty("装载率(重量)")
	@Mapping
	private BigDecimal weightLoading;
}
