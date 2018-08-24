package com.hivescm.tms.api.dto.es.bill;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.tms.api.enums.biz.bill.BillStatusEnum;
import com.hivescm.tms.api.enums.biz.bill.BillTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 单据详情
 * @author ke.huang
 * @date 2017年9月25日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class BillInfoEsDTO implements Serializable,Cloneable{
	private static final long serialVersionUID = 529652418789173762L;
	@Mapping
	@ApiModelProperty("主键")
	private Long id;
	@Mapping
	@ApiModelProperty("公司ID")
    private Long companyId;
	@Mapping
	@ApiModelProperty("单据入库ID")
    private Long billStockId;
	@Mapping
	@ApiModelProperty("单据申领ID")
    private Long requestGrantId;
	@Mapping
	@ApiModelProperty("单据号")
    private String billCode;
	@ApiModelProperty("单据类型")
    private BillTypeEnum billType;
	@Mapping
	@ApiModelProperty("单价")
    private BigDecimal price;
	@ApiModelProperty("单据状态1=未领用 2=未使用 3=已使用 4=已作废 5=已取消")
    private BillStatusEnum status;
	@Mapping
	@ApiModelProperty("取消领用人ID")
	private Integer canceledUserId;
	@Mapping
	@ApiModelProperty("取消领用时间")
	private Long canceledTime;
	@Mapping
	@ApiModelProperty("创建人ID")
    private Integer createUser;
	@Mapping
	@ApiModelProperty("创建时间")
    private Long createTime;
	@Mapping
	@ApiModelProperty("修改人ID")
    private Integer updateUser;
	@Mapping
	@ApiModelProperty("修改时间")
    private Long updateTime;
	
	/********************************冗余字段*********************************/
	@Mapping
	@ApiModelProperty("公司名称")
	private String companyName;
	@Mapping
	@ApiModelProperty("创建人姓名")
	private String createUserName;
	@Mapping
	@ApiModelProperty("修改人姓名")
	private String updateUserName;
	@Mapping
	@ApiModelProperty("单据申领批次号")
	private String billRequestReceivedBatchCode;
	@Mapping
	@ApiModelProperty("单据入库批次号")
	private String billStockBatchCode;
	@Mapping
	@ApiModelProperty("单据使用人ID")
	private Long usedUserId;
	@Mapping
	@ApiModelProperty("单据使用人姓名")
	private String usedUserName;
	@Mapping
	@ApiModelProperty("单据使用网点ID")
	private Integer requestOrgId;
	@Mapping
	@ApiModelProperty("单据使用网点名称")
	private String requestOrgName;
	@Mapping
	@ApiModelProperty("单据发放时间")
    private Long grantTime;
	@Mapping
	@ApiModelProperty("单据申领人ID")
    private Integer requestUserId;
	@Mapping
	@ApiModelProperty("单据申领人姓名")
    private String requestUserName;
	@Mapping
	@ApiModelProperty("单据申领时间")
    private Long requestTime;
	@Mapping
	@ApiModelProperty("领取人ID")
    private Integer receivedUserId;
	@Mapping
	@ApiModelProperty("经办人ID")
    private Integer handlerUserId;
	@Mapping
	@ApiModelProperty("领取人姓名")
    private String receivedUserName;
	@Mapping
	@ApiModelProperty("经办人姓名")
    private String handlerUserName;
	@Mapping
	@ApiModelProperty("取消领用人姓名")
	private String canceledUserName;
	//运单作废冗余
	@Mapping
	@ApiModelProperty("作废类型ID")
	private Integer discardTypeId;
	@Mapping
	@ApiModelProperty("作废类型名称")
	private String discardTypeName;
	@Mapping
	@ApiModelProperty("作废原因")
	private String discardReason;
	@Mapping
	@ApiModelProperty("作废操作时间")
	private Long discardTime;
	@Mapping
	@ApiModelProperty("作废操作人ID")
	private Integer discardUsreId;
	@Mapping
	@ApiModelProperty("作废操作人姓名")
	private String discardUserName;
	
	public BillInfoEsDTO clone(Long id,String billCode) throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		BillInfoEsDTO dto = (BillInfoEsDTO) super.clone();
		dto.setId(id);
		dto.setBillCode(billCode);
		return dto;
	}
	
}