package com.hivescm.tms.api.dto.es.accusedgoods.response;

import java.io.Serializable;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author  boqiang.deng
 * @date    2018年5月18日
 * @company 蜂网供应链管理（上海）有限公司
 * 
 */
@Data
@ToString
public class AccuseGoodsResp implements Serializable{

	private static final long serialVersionUID = 1L;
	private @Mapping @ApiModelProperty ("控货批次") String batchCode;

	private @Mapping @ApiModelProperty("状态") Integer status;
    
     private @Mapping @ApiModelProperty("状态名称") String statusName;
     
     private @Mapping @ApiModelProperty("操作类型") Integer operationType;

     private @Mapping @ApiModelProperty("操作类型名称") String operationTypeName;
     
     private @Mapping @ApiModelProperty("申请时间") Long applicantTime;
     
     private @Mapping @ApiModelProperty("确认人名称") String confirmName;
     
     private @Mapping @ApiModelProperty("申请人名称") String applicantName;
     
     private @Mapping @ApiModelProperty("确认时间") Long confirmTime;
     
     private @Mapping @ApiModelProperty("申请网点名称") String applicantOrgName;
     
     private @Mapping @ApiModelProperty("确认网点名称") String confirmOrgName;
     
     private @Mapping @ApiModelProperty("运单id") Long waybillId;
	

}
