package com.hivescm.tms.api.dto.es.global;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 经销商选取承运商规则
 * @author ke.huang
 * @date 2017年10月23日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class GlobalDealerCarrierConfigEsDTO implements Serializable{
	private static final long serialVersionUID = 3999534885957430174L;
	@Mapping
	@ApiModelProperty("主键")
	private Long id;
	@Mapping
	@ApiModelProperty("市ID")
    private String cityId;
	@Mapping
	@ApiModelProperty("经销商ID")
    private Long dealerId;
	@Mapping({"isAnyCarrier"})
	@ApiModelProperty("是否允许任意承运商承运订单")
    private Boolean ianyCarrier;
	@Mapping({"isAppointCarrier"})
	@ApiModelProperty("是否指定承运商承运订单")
    private Boolean iappointCarrier;
	@Mapping({"isPriorityAllocation"})
	@ApiModelProperty("是否按优先级分配")
    private Boolean ipriorityAllocation;
	@ApiModelProperty("是否按比例分配")
	@Mapping({"isScaleAllocation"})
    private Boolean iscaleAllocation;
	@ApiModelProperty("是否按顺序分配")
	@Mapping({"isSortAllocation"})
    private Boolean isortAllocation;
	@Mapping({"isDistributionOther"})
	@ApiModelProperty("无指定时策略")
    private Boolean idistributionOther;
	@ApiModelProperty("优先级配置")
    private String priorityRule;
	@Required
	@Mapping({"isSelf"})
	@ApiModelProperty("经销商是否自己配送")
	private Boolean iself;
	@ApiModelProperty("创建人")
    private Integer createUser;
	@ApiModelProperty("创建时间")
    private Long createTime;
	@ApiModelProperty("修改人")
    private Integer updateUser;
	@ApiModelProperty("修改时间")
    private Long updateTime;
	
	/****************************冗余字段**********************/
	@ApiModelProperty("市名称")
	private String cityName;
	@ApiModelProperty("经销商名称")
	private String dealerName;
	@ApiModelProperty("创建人姓名")
	private String createUserName;
	@ApiModelProperty("修改人姓名")
	private String updateUserName;

}