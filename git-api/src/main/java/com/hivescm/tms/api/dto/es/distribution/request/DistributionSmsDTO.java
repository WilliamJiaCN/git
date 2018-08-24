package com.hivescm.tms.api.dto.es.distribution.request;


import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author  boqiang.deng
 * @company 蜂网供应链管理（上海）有限公司
 * @since   2017/8/11
*/
@Data
@ToString
public class DistributionSmsDTO {

	
	@Mapping
	@ApiModelProperty("运单id")
    private Long id;
	
	@Mapping
	@ApiModelProperty("运单号")
    private String waybillCode;
	
	@Mapping
	@ApiModelProperty("公司名称")
    private String companyName;
    
	@Mapping
    @ApiModelProperty("司机姓名(装货时必填)")
	private String driverName;
    
	@Mapping
    @ApiModelProperty("司机电话号码(装货时必填)")
    private String driverPhone;
    
	@Mapping
    @ApiModelProperty("收货人电话号码(装货时必填)")
    private String consignorTelNo;
	/**
	 * 收货人		
	 */
	@Mapping
	@ApiModelProperty("收货人")
	private String receiptUser;

	@Mapping
    @ApiModelProperty("预计几个小时到达")
    private Long arrivalTime;

	@Mapping
    @ApiModelProperty("到付运费")
    private String cod;
    
	@Mapping
	@ApiModelProperty("短信内容")
	private String content;
	
	@Mapping
	private  @ApiModelProperty("收货人手机号码")String receiptConsignorMobleNo;
}

