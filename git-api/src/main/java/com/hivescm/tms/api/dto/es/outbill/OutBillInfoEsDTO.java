package com.hivescm.tms.api.dto.es.outbill;

import java.math.BigDecimal;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class OutBillInfoEsDTO {

	@Mapping
	@ApiModelProperty("主键ID")
	private Long id;
	
	@Mapping
	@ApiModelProperty("公司ID")
    private Long companyId;
	
	@Mapping
	@ApiModelProperty("外发批次号")
    private String outGoCode;
	
	@Mapping
	@ApiModelProperty("外发时间")
    private Long outGoTime;
	
	@Mapping
	@ApiModelProperty("外发网点")
    private Integer outBranchId;
	
	@Mapping
	@ApiModelProperty("外发网点名称")
    private String outBranchName;
	
	@Mapping
	@ApiModelProperty("是否入库")
    private Boolean inStorage;
	
	@Mapping
	@ApiModelProperty("入库网点")
    private Integer inBranchId;
	
	@Mapping
	@ApiModelProperty("入库网点名称")
    private String inBranchName;
	
	@Mapping
	@ApiModelProperty("分摊方式")
    private Integer feeShareType;
	
	@Mapping
	@ApiModelProperty("分摊方式名称")
    private String feeShareTypeName;
	
	@Mapping
	@ApiModelProperty("外发成本")
    private BigDecimal outGoCost;
	
	@Mapping
	@ApiModelProperty("应收")
    private BigDecimal accountsReceivable;
	
	@Mapping
	@ApiModelProperty("应付")
    private BigDecimal accountsCost;
	
	@Mapping
	@ApiModelProperty("总票数")
    private Integer count;
	
	@Mapping
	@ApiModelProperty("总件数")
    private Integer packageNum;
	
	@Mapping
	@ApiModelProperty("总体积")
    private BigDecimal volume;
	
	@Mapping
	@ApiModelProperty("总重量")
    private BigDecimal weight;
	
	@Mapping
	@ApiModelProperty("外发公司ID")
    private Integer outCompanyID;
	
	@Mapping
	@ApiModelProperty("外发公司")
	private String outCompanyNames;
	
	@Mapping
	@ApiModelProperty("外发状态")
    private Integer status;
	
	@Mapping
	@ApiModelProperty("外发状态名称")
    private String statusName;
	
	@Mapping
	@ApiModelProperty("经办人（系统操作员）")
    private Integer operator;
    
    @Mapping
	@ApiModelProperty("经办人（系统操作员）名称")
    private String operatorName;
	
	@Mapping
	@ApiModelProperty("外发确认人")
    private String confirmUserName;
	
	@Mapping
	@ApiModelProperty("外发确认时间")
    private Long confirmTime;
	
	@Mapping
	@ApiModelProperty("总运费")
    private BigDecimal totalFreightFee;
	
	@Mapping
	@ApiModelProperty("总产值")
    private BigDecimal totalProductFee;
	
	@Mapping
	@ApiModelProperty("总业务费")
    private BigDecimal totalBusinessFee;
	
	@Mapping
	@ApiModelProperty("总到付")
    private BigDecimal totalToPay;
	
	@Mapping
	@ApiModelProperty("总代收货款")
    private BigDecimal totalPay;
	
	@Mapping
	@ApiModelProperty("备注")
    private String remark;
	
	@Mapping
    private Integer createUser;
	@Mapping
    private String createUserName;
	@Mapping
    private Long createTime;
	@Mapping
    private String updateUserName;
	@Mapping
    private Integer updateUser;
	@Mapping
    private Long updateTime;
}
