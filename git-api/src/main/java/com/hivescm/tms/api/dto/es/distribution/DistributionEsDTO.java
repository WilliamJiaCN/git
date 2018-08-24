package com.hivescm.tms.api.dto.es.distribution;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 城配调度表
 *
 * @author
 */
@Data
@ToString
public class DistributionEsDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	@Mapping
	@ApiModelProperty("主键")
	private Long id;

	@Mapping
	@ApiModelProperty("公司ID")
	private Long companyId;

	@Mapping
	@ApiModelProperty("公司名称")
	private String companyName;

	@Mapping
	@ApiModelProperty("派车单ID")
	private Long dispatcherId;
	/**
	 * 派车批次
	 */
	@Mapping
	@ApiModelProperty("派车批次")
	private String batchCode;

	@Mapping
	@ApiModelProperty("运单ID")
	private Long waybillId;

	@Mapping
	@ApiModelProperty("司机ID")
	private Integer driverId;
	
	@Mapping
	@ApiModelProperty("派车单成本")
	private String cost;

	@Mapping
	@ApiModelProperty("司机姓名")
	private String driverName;

	@Mapping
	@ApiModelProperty("车辆ID")
	private Integer vehicleId;

	@Mapping
	@ApiModelProperty("车辆号码")
	private String vehicleName;

	@Mapping
	@ApiModelProperty("车队ID")
	private Integer fleetId;
	
	@Mapping
	@ApiModelProperty("车队名称")
	private String fleetName;

	@Mapping
	@ApiModelProperty("单据类型")
	private Integer billType;
	
	@Mapping
	@ApiModelProperty("单据类型名称")
	private String billTypeName;

	@Mapping
	@ApiModelProperty("收货地址")
	private String receiptAddress;
	
	@Mapping
	@ApiModelProperty("发货地址")
	private String invoiceAddress;
	/**
	 * 派车单状态(1=已指派 2=已接单 3=已装货 4=未发车 5=已发车) 状态不为5时派车单未发车
	 */
	@Mapping
	@ApiModelProperty("派车单状态(1=已指派 2=已接单 3=已装货 4=未发车 5=已发车 6=已签收) 状态不为5时派车单未发车")
	private Integer status;
	/**
	 * 派车网点ID
	 */
	@Mapping
	@ApiModelProperty("派车网点ID")
	private Integer dotId;
	/**
	 * 派车网点名称
	 */
	@Mapping
	@ApiModelProperty("派车网点名称")
	private String dotName;
	/**
	 * 总体积
	 */
	@Mapping
	@ApiModelProperty("总体积")
	private String volume;
	/**
	 * 总重量
	 */
	@Mapping
	@ApiModelProperty("总重量")
	private String weight;
	/**
	 * 总单数
	 */
	@Mapping
	@ApiModelProperty("总单数")
	private Integer totalNum;
	
	/**
	 * 总单数
	 */
	@Mapping
	@ApiModelProperty("总件数")
	private Integer totalPackage;
	
	/**
	 * 是否整车	
	 */
	@Mapping
	@ApiModelProperty("是否整车") 
	private  Boolean iTruckLoad;
	
	@Mapping
	@ApiModelProperty("操作类型（1=指派司机、2=指派车队、3=地图指派 4=智能调度）")
	private Integer operateType;
	
	@Mapping
	@ApiModelProperty("指派时间")
    private Long billTime;
	
	@Mapping
	@ApiModelProperty("指派网点")
    private Integer billOrgId;
    
	@Mapping
	@ApiModelProperty("单据状态")
    private Integer billStatus;
    
	@Mapping
	@ApiModelProperty("撤回时间")
    private Long recallTime;
    
	@Mapping
	@ApiModelProperty("撤回人")
    private Integer recallUser;
    
	@ApiModelProperty("撤回类型")
    private Integer recallType;
	
	@Mapping
	@ApiModelProperty("签收时间")
	private Long signTime;
	
	@Mapping
	@ApiModelProperty("创建人")
	private Integer createUser;

	@Mapping
	@ApiModelProperty("创建人姓名")
	private String createUserName;

	@Mapping
	@ApiModelProperty("创建时间")
	private Long createTime;

	@Mapping
	@ApiModelProperty("修改人")
	private Integer updateUser;

	@Mapping
	@ApiModelProperty("修改人姓名")
	private String updateUserName;

	@Mapping
	@ApiModelProperty("修改时间")
	private Long updateTime;
	
	/***********************************新增字段*********************************/
	
	@Mapping
	@ApiModelProperty("执行任务id")
	private Integer taskId;
	
	@Mapping
	@ApiModelProperty("执行任务名称")
	private String taskName;
	
	@ApiModelProperty("车型ID")
    private Integer vehicleModels;
    
    @ApiModelProperty("车型名称")
    private String vehicleModelName;
    
    @ApiModelProperty("司机手机号码")
    private String phoneNo;
    
    @Mapping
    @ApiModelProperty("发车时间")
    private Long dispatcherTime;
    
    /**
     * 发布状态
     */
    @Mapping
    @ApiModelProperty("发布状态")
    private Boolean releaseStatus;

}
