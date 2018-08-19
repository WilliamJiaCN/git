package com.hivescm.tms.api.dto.es.order.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.tms.api.enums.capacity.order.OrderOperateTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 承运商运单分配接单请求（接单、拒接、超时未接）
 * @author ke.huang
 * @date 2017年10月31日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class OrderCapacityResultReqDTO implements Serializable{
	private static final long serialVersionUID = 2570791356156128882L;
	@Mapping({"OrderCapacityDO.carrierId"})
	@ApiModelProperty("承运商ID")
	private Long carrierId;
	@ApiModelProperty("请求类型")
	private OrderOperateTypeEnum orderOperateTypeEnum;
	@Mapping({"OrderCapacityDO.acceptTime","OrderCapacityEsDTO.acceptTime"})
	@ApiModelProperty("接单时间")
    private Long acceptTime;
	@Mapping({"OrderCapacityDO.acceptUser","OrderCapacityEsDTO.acceptUser"})
	@ApiModelProperty("接单人")
    private Integer acceptUser;
	@Mapping({"OrderCapacityDO.acceptUserName","OrderCapacityEsDTO.acceptUserName"})
	@ApiModelProperty("接单人姓名")
    private String acceptUserName;//冗余
	@Mapping({"OrderCapacityDO.retractUser","OrderCapacityEsDTO.retractUser"})
	@ApiModelProperty("撤回人")
    private Integer retractUser;
	@Mapping({"OrderCapacityDO.retractUserName","OrderCapacityEsDTO.retractUserName"})
	@ApiModelProperty("撤回人姓名")
    private String retractUserName;//冗余
	@Mapping({"OrderCapacityDO.refuseUser","OrderCapacityEsDTO.refuseUser"})
	@ApiModelProperty("拒接人")
    private Integer refuseUser;
	@Mapping({"OrderCapacityDO.refuseUserName","OrderCapacityEsDTO.refuseUserName"})
	@ApiModelProperty("拒接人姓名")
    private String refuseUserName;//冗余
	@Mapping({"OrderCapacityDO.invalidTime","OrderCapacityEsDTO.invalidTime"})
	@ApiModelProperty("接单失效时间")
    private Long invalidTime;
	@Mapping({"OrderCapacityDO.refuseTime","OrderCapacityEsDTO.refuseTime"})
	@ApiModelProperty("拒接时间")
    private Long refuseTime;
	@Mapping({"OrderCapacityDO.recallTime","OrderCapacityEsDTO.retractTime"})
	@ApiModelProperty("撤回时间")
    private Long recallTime;
	@Logger
	@Mapping({"OrderCapacityDO.orderId","OrderCapacityEsDTO.orderId"})
	@ApiModelProperty("订单ID")
	private Long orderId;

	
	
	@Mapping
	@ApiModelProperty("修改人")
    private Integer updateUser;
	@Mapping
	@ApiModelProperty("修改人姓名")
	private String updateUserName;//冗余
	@Mapping
	@ApiModelProperty("修改时间")
    private Long updateTime;
	
}
