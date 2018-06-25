package com.hivescm.tms.api.dto.es.finance.request;

import java.util.List;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FinanceReceiptStatusUpdateDTO {


	@ApiModelProperty("公司ID")
	private Long companyId;

    /**
     * 回单状态
     */
    @Mapping
    @ApiModelProperty("回单状态")
    private Integer backStatus;
    
    /**
     * 回单状态
     */
    @Mapping
    @ApiModelProperty("回单状态名称")
    private String backStatusName;

    /**
     * 回单发放时间
     */
    @Mapping
    @ApiModelProperty("回单发放时间")
    private Long backGrantTime;

    /**
     * 签收状态
     */
    @Mapping
    @ApiModelProperty("签收状态")
    private Integer signStatus;

	@ApiModelProperty("运单ID")
	private List<Long> ids;
    
    /**
     * 签收状态
     */
    @Mapping
    @ApiModelProperty("签收状态名称")
    private String signStatusName;

    /**
     * 签收时间
     */
    @Mapping
    @ApiModelProperty("签收时间")
    private Long signTime;
	
	
	@ApiModelProperty("支付方式")
	private Integer payWay;
}
