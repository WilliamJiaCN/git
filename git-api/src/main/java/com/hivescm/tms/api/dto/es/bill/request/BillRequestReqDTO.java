package com.hivescm.tms.api.dto.es.bill.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.tms.api.enums.biz.bill.BillTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 单据申请信息请求DTO
 * @author ke.huang
 * @date 2017年10月11日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class BillRequestReqDTO implements Serializable{
	private static final long serialVersionUID = 698010396132973543L;
	@Mapping
	@ApiModelProperty("主键")
	private Long id;
	@Mapping
	@ApiModelProperty("公司ID")
    private Long companyId;
    @Mapping
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
	@ApiModelProperty("使用人ID")
    private Integer usedUserId;
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
	@ApiModelProperty("使用人姓名")
    private String usedUserName;
	@Mapping
	@ApiModelProperty("创建人姓名")
	private String createUserName;
	@Mapping
	@ApiModelProperty("创建人ID")
    private Integer createUser;
	@Mapping
	@ApiModelProperty("更新人ID")
    private Integer updateUser;
	@Mapping
	@ApiModelProperty("修改人姓名")
	private String updateUserName;

	@Mapping
	@ApiModelProperty("申请网点ID")
	private Integer receivedOrgId;
	@Mapping
	@ApiModelProperty("申请网点名称")
	private String receivedOrgName;
}
