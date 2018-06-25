package com.hivescm.tms.api.dto.es.order;

import java.io.Serializable;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderCapacityEsDTO implements Serializable{
	private static final long serialVersionUID = 5007129980328431303L;
	/**
	 * 订单分配信息
	 */
	@Mapping({"OrderCapacityDO.id","OrderEsDTO.orderCapacityId"})
	@ApiModelProperty("订单分配主键")
	private Long id;
	@Mapping({"OrderCapacityDO.capacityUser","OrderEsDTO.capacityUser"})
	@ApiModelProperty("分配人ID")
    private Integer capacityUser;
	@Mapping({"OrderCapacityDO.capacityUserName","OrderEsDTO.capacityUserName"})
	@ApiModelProperty("分配人姓名")
    private String capacityUserName;//冗余
	@Mapping({"OrderCapacityDO.status","OrderEsDTO.capacityStatus"})
	@ApiModelProperty("订单分配信息状态")
    private Integer capacityStatus;
	@Mapping({"OrderCapacityDO.acceptTime","OrderEsDTO.acceptTime"})
	@ApiModelProperty("接单时间")
    private Long acceptTime;
	@Mapping({"OrderCapacityDO.acceptUser","OrderEsDTO.acceptUser"})
	@ApiModelProperty("接单人")
    private Integer acceptUser;
	@Mapping({"OrderCapacityDO.acceptUserName","OrderEsDTO.acceptUserName"})
	@ApiModelProperty("接单人姓名")
    private String acceptUserName;//冗余
	@Mapping({"OrderCapacityDO.retractUser","OrderEsDTO.retractUser"})
	@ApiModelProperty("撤回人")
    private Integer retractUser;
	@Mapping({"OrderCapacityDO.retractUserName","OrderEsDTO.retractUserName"})
	@ApiModelProperty("撤回人姓名")
    private String retractUserName;//冗余
	@Mapping({"OrderCapacityDO.refuseUser","OrderEsDTO.refuseUser"})
	@ApiModelProperty("拒接人")
    private Integer refuseUser;
	@Mapping({"OrderCapacityDO.refuseUserName","OrderEsDTO.refuseUserName"})
	@ApiModelProperty("拒接人姓名")
    private String refuseUserName;//冗余
	@Mapping({"OrderCapacityDO.invalidTime","OrderEsDTO.invalidTime"})
	@ApiModelProperty("接单失效时间")
    private Long invalidTime;
	@Mapping({"OrderCapacityDO.isAutoAccept","OrderEsDTO.isAutoAccept"})
	@ApiModelProperty("超时是否自动接收")
    private Boolean iautoAccept;
	@Mapping({"OrderCapacityDO.carrierId","OrderEsDTO.carrierId"})
	@ApiModelProperty("承运商ID")
    private Integer carrierId;
	@Mapping({"OrderCapacityDO.carrierName","OrderEsDTO.carrierName"})
	@ApiModelProperty("承运商名称")
    private String carrierName;//冗余
	@Mapping({"OrderCapacityDO.refuseTime","OrderEsDTO.refuseTime"})
	@ApiModelProperty("拒接时间")
    private Long refuseTime;
	@Mapping({"OrderCapacityDO.recallTime","OrderEsDTO.retractTime"})
	@ApiModelProperty("撤回时间")
    private Long recallTime;
	
	
	//冗余主表信息，用于反查列表，分配时记得冗余过来，否则列表查询不能筛选条件
	@ApiModelProperty("运力分配时间")
	private Long capacityTime;//冗余主表 分页列表条件
	@ApiModelProperty("下单时间")
	private Long orderCreateTime;//冗余主表 分页列表条件
	@ApiModelProperty("接收时间")
	private Long platformAcceptTime;//冗余主表 分页列表条件
	@ApiModelProperty("运输线路名称") 
	private String lineName;// 冗余主表 分页列表条件
	@ApiModelProperty("订单号")
	private String orderCode;//冗余主表 分页列表条件
	@ApiModelProperty("订单ID")
	private Long orderId;
	@ApiModelProperty("线路ID")//冗余主表 接单统计 订单绑定线路使用（一期不做先保留）
	private Long lineId;
	@ApiModelProperty("服务ID")//冗余主表 接单累加运力使用（一期不做先保留）
	private Long serviceId;
	
	/**
	 * 公共属性
	 */
	@Mapping
	@ApiModelProperty("仓库ID")
    private Long warehouseId;
	@Mapping
	@ApiModelProperty("仓库名称")
	private String warehouseName;//冗余
	@Mapping
	@ApiModelProperty("创建人")
    private Integer createUser;
	@Mapping
	@ApiModelProperty("创建人姓名")
	private String createUserName;//冗余
	@Mapping
	@ApiModelProperty("创建时间")
    private Long createTime;
	@Mapping
	@ApiModelProperty("修改人")
    private Integer updateUser;
	@Mapping
	@ApiModelProperty("修改人姓名")
	private String updateUserName;//冗余
	@Mapping
	@ApiModelProperty("修改时间")
    private Long updateTime;
	
	/****************************额外冗余****************************/
	@Mapping
	@ApiModelProperty("网点ID") 
	private Integer branchId;
	@Mapping
	@ApiModelProperty("网点名称") 
	private String branchName;// 冗余线路 
	
	@Mapping
	@ApiModelProperty("订单配送类型 1,物流 2,快递 3,邮寄")
	private Integer orderDeliveryType;
	
	@Mapping
	@ApiModelProperty("订单配送类型 1,物流 2,快递 3,邮寄")
	private String orderDeliveryTypeName;
}
