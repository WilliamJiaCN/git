package com.hivescm.tms.api.dto.es.distribution.request;

import java.io.Serializable;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.tms.api.enums.biz.dispatcher.DistributionBillTypeEnum;
import com.hivescm.tms.api.enums.biz.dispatcher.DistributionStatusEnum;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 城配调度单状态请求DTO
 * @author lutingting
 * @date 2017年8月9日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class DistributionRequestDTO implements Serializable{
	private static final long serialVersionUID = -2801125059760228371L;
	
    @ApiModelProperty("id")
    private @Mapping Long id;
	 /**
     * 公司id
     */
    @ApiModelProperty("公司id")
    private @Mapping Long companyId;

   
   

  
    /**
     * 接单状态
     */
    @ApiModelProperty("接单状态 1=未接单 2=已忽略 3=已接单 4=已失效")
    private DistributionStatusEnum taskStatus;

  

   
    
    @ApiModelProperty("单据类型")
    private DistributionBillTypeEnum billType;
    
   
   

   

	


	

	

}
