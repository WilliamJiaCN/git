package com.hivescm.tms.api.dto.es.bill;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.tms.api.enums.biz.bill.BillTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 单据申领表
 * @author ke.huang
 * @date 2017年9月25日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class BillRequestReceivedEsDTO implements Serializable{
	private static final long serialVersionUID = -9011261032093041649L;
	@Mapping
	@ApiModelProperty("主键")
	private Long id;
	@Mapping
	@ApiModelProperty("公司ID")
    private Long companyId;
	@Mapping
	@ApiModelProperty("单据库存ID")
	private Long billStockId;
	@Mapping
	@ApiModelProperty("申领批次")
    private String batchCode;
	@Mapping
	@ApiModelProperty("状态 1=未确认 2=已确认")
    private Integer status;
	@ApiModelProperty("申领单据类型 1=运单 2=发票 3=支票")
    private BillTypeEnum requestBillType;
	@Mapping
	@ApiModelProperty("申领份数")
    private Integer requestNum;
	@Mapping
	@ApiModelProperty("使用网点ID")
    private Integer requestOrgId;
	@Mapping
	@ApiModelProperty("申领备注")
    private String requestRemark;
	@Mapping
	@ApiModelProperty("申领人ID")
    private Integer requestUserId;
	@Mapping
	@ApiModelProperty("申领时间")
    private Long requestTime;
	@Mapping
	@ApiModelProperty("发放单据起始号")
    private String grantStartNum;
	@Mapping
	@ApiModelProperty("发放单据结束号")
    private String grantEndNum;
	@Mapping
	@ApiModelProperty("发放份数")
    private Integer grantNum;
	@Mapping
	@ApiModelProperty("发放备注")
    private String grantRemark;
	@Mapping
	@ApiModelProperty("发放时间")
    private Long grantTime;
	@Mapping
	@ApiModelProperty("发放人")
    private Integer grantUserId;
	@Mapping
	@ApiModelProperty("单价")
    private BigDecimal price;
	@Mapping	
	@ApiModelProperty("总价")
    private BigDecimal totalPrice;
	@Mapping
	@ApiModelProperty("使用人ID")
    private Integer usedUserId;
	@Mapping
	@ApiModelProperty("领取人ID")
    private Integer receivedUserId;
	@Mapping
	@ApiModelProperty("领取时间")
    private Long receivedTime;
	@Mapping
	@ApiModelProperty("经办人ID")
    private Integer handlerUserId;
	@Mapping
	@ApiModelProperty("未使用份数")
    private Integer notUsedNum;
	@Mapping
	@ApiModelProperty("已使用份数")
    private Integer usedNum;
	@Mapping
	@ApiModelProperty("已作废份数")
    private Integer discardNum;
	@Mapping
	@ApiModelProperty("已取消份数")
    private Integer cancelledNum;
	@Mapping
	@ApiModelProperty("创建人ID")
    private Integer createUser;
	@Mapping
	@ApiModelProperty("创建时间")
    private Long createTime;
	@Mapping
	@ApiModelProperty("更新人ID")
    private Integer updateUser;
	@Mapping
	@ApiModelProperty("更新时间")
    private Long updateTime;
	@Mapping
	@ApiModelProperty("申请网点ID")
	private Integer receivedOrgId;

	
	/********************************冗余字段*********************************/
	@Mapping
	@ApiModelProperty("公司名称")
	private String companyName;
	@Mapping
	@ApiModelProperty("使用网点名称")
    private String requestOrgName;
	@Mapping
	@ApiModelProperty("申领人姓名")
    private String requestUserName;
	@Mapping
	@ApiModelProperty("发放人姓名")
    private String grantUserName;
	@Mapping
	@ApiModelProperty("使用人姓名")
    private String usedUserName;
	@Mapping
	@ApiModelProperty("领取人姓名")
    private String receivedUserName;
	@Mapping
	@ApiModelProperty("经办人姓名")
    private String handlerUserName;
	@Mapping
	@ApiModelProperty("创建人姓名")
	private String createUserName;
	@Mapping
	@ApiModelProperty("修改人姓名")
	private String updateUserName;

	@Mapping
	@ApiModelProperty("申请网点名称")
	private String receivedOrgName;
	
	

}