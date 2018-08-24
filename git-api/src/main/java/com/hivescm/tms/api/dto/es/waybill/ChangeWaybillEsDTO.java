package com.hivescm.tms.api.dto.es.waybill;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ToString
public class ChangeWaybillEsDTO  implements Serializable{
	  private @Mapping static final long serialVersionUID = 1L;
			/**
			 * 主键
			 */
		    private @Mapping @ApiModelProperty("主键") Long id;
		    /**
		     * 公司ID
		     */
		    private @Mapping @ApiModelProperty("公司ID") Long companyId;
		    
			/**
			 * 公司名称	
			 */
			private @Mapping  @ApiModelProperty("公司名称") String companyName ;
			
			
			
		    
		    /**
		     * 更改批次
		     */
		    @ApiModelProperty("更改批次")
		    private @Mapping String changeBatch;
		    /**
		     * 运单ID
		     */
		    @ApiModelProperty("运单ID")
		    private @Mapping Long waybillId;
		    
		    @ApiModelProperty("运单号")
		    private @Mapping String waybillCode;
		    
		    @ApiModelProperty("更改状态（1未确认 2已确认）")
		    private @Mapping Integer status;
		    
		    @ApiModelProperty("是否更改运费")
		    private @Mapping Boolean changeFee;
		    
		    @ApiModelProperty("运费差异")
		    private @Mapping BigDecimal feeDifference;
		    
		    @ApiModelProperty("代收货款差异")
		    private @Mapping BigDecimal deliveryDifference;
		    
		    @ApiModelProperty("业务费差异")
		    private @Mapping BigDecimal businessDifference;
		    
		    @ApiModelProperty("更改内容")
		    private @Mapping String changeContent;
		    
		    @ApiModelProperty("更改备注")
		    private @Mapping String changeRemark;
		    
		    @ApiModelProperty("改前付款方式")
		    private @Mapping Integer beforePayWay;
		    @ApiModelProperty("改前付款方式名称")
		    private @Mapping String beforePayWayName;
		    
		    @ApiModelProperty("改后付款方式")
		    private @Mapping Integer afterPayWay;
		    
		    @ApiModelProperty("改后付款方式名称")
		    private @Mapping String afterPayWayName;
		    
		    @ApiModelProperty("发货网点")
		    private @Mapping Long invoiceOrg;
		    
		    @ApiModelProperty("发货网点名称")
		    private @Mapping String invoiceOrgName;
		    
		    @ApiModelProperty("目的地")
		    private Long destId;
		    
		    @ApiModelProperty("目的地名称")
		    private @Mapping String destName;
		    
		    @ApiModelProperty("运输线路ID")
		    private @Mapping Long lineId;
		    
		    @ApiModelProperty("运输线路名称")
		    private @Mapping String lineName;
		    
		    @ApiModelProperty("目的网点")
		    private @Mapping Long destOrg;
		    
		    @ApiModelProperty("目的网点名称")
		    private @Mapping String destOrgName;
		    
		    @ApiModelProperty("申请人")
		    private @Mapping Long applyUser;
		    
		    @ApiModelProperty("申请人姓名")
		    private @Mapping String applyUserName;
		    
		    @ApiModelProperty("申请时间")
		    private @Mapping Long applyTime;
		    
		    
		    @ApiModelProperty("申请网点")
		    private @Mapping Long applyOrg;
		    
		    @ApiModelProperty("申请网点名称")
		    private @Mapping String applyOrgName;
		    
		    @ApiModelProperty("初始创建人")
		    private @Mapping Long oldCreateUser;
		    
		    @ApiModelProperty("初始创建人姓名")
		    private @Mapping String oldCreateUserName;
		    
		    @ApiModelProperty("初始创建时间")
		    private @Mapping Long oldCreateTime;
		    
		    @ApiModelProperty("确认人")
		    private @Mapping Long comfirmUser;
		    
		    @ApiModelProperty("确认人姓名")
		    private @Mapping String confirmUserName;
		    
		    @ApiModelProperty("修改时间")
		    private @Mapping Long confirmTime;
		    
		    @ApiModelProperty("更改状态（1未确认 2已确认）名称")
		    private @Mapping String statusName;
		    
		    
		    @ApiModelProperty("变更旧数据")
		    private @Mapping String  oldChangeData;
		    
		    @ApiModelProperty("变更新数据")
		    private @Mapping String  newChangeData;
		    
}
