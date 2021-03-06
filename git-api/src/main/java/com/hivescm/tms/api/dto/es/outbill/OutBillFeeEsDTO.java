package com.hivescm.tms.api.dto.es.outbill;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
@Data
@ToString
public class OutBillFeeEsDTO {
	@Mapping
	@ApiModelProperty("主键ID")
    private Long id;

	@Mapping
    @ApiModelProperty("公司id 分库分表参数")
    private Long companyId;

	@Mapping
    @ApiModelProperty("外发单id")
    private Long outbillId;

	@Mapping
    @ApiModelProperty("费用类型")
    private Integer feeType;

	@Mapping
    @ApiModelProperty("费用类型名称")
    private String feeTypeName;

	@Mapping
    @ApiModelProperty("费用")
    private BigDecimal amount;

	@Mapping
    @ApiModelProperty("付款对象")
    private Long payObjectId;
	
	@Mapping
    @ApiModelProperty("付款对象名称")
    private String payObjectName;

	@Mapping
    @ApiModelProperty("收款对象")
    private Long receiveObjectId;
	
	@Mapping
    @ApiModelProperty("收款对象名称")
    private String receiveObjectName;

	@Mapping
    @ApiModelProperty("油卡号码")
    private String oilcardNum;

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