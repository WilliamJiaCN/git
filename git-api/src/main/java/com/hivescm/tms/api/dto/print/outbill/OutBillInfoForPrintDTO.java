package com.hivescm.tms.api.dto.print.outbill;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ToString
public class OutBillInfoForPrintDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Mapping
	@ApiModelProperty("主键ID")
	private Long id;
    @Mapping
    @ApiModelProperty("外发批次号")
    private String outGoCode;
    @Mapping
    @ApiModelProperty("外发时间")
    private Long outGoTime;
    @Mapping
	@ApiModelProperty("是否入库")
    private Boolean inStorage;
    @Mapping
	@ApiModelProperty("入库网点名称")
    private String inBranchName;
    
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
    @ApiModelProperty("外发公司")
    private String outCompanyNames;
    @Mapping
	@ApiModelProperty("外发公司联系人")
    private String outContactName;
    
    @Mapping
    @ApiModelProperty("外发网点名称")
    private String outBranchName;
    
    @Mapping
   	@ApiModelProperty("外发网点联系人")
    private String outBranchContactName;
    
    @Mapping
	@ApiModelProperty("经办人（系统操作员）名称")
    private String operatorName;
    
    @Mapping
	@ApiModelProperty("备注")
    private String remark;

    @Mapping
	@ApiModelProperty("中转运费")
    private BigDecimal transitFreight;
	
	@Mapping
	@ApiModelProperty("中转提货费")
    private BigDecimal transitTake;
	
	@Mapping
	@ApiModelProperty("中转送货费")
    private BigDecimal transitSend;
	
	@Mapping
	@ApiModelProperty("中转其他费用")
    private BigDecimal transitOther;
}
