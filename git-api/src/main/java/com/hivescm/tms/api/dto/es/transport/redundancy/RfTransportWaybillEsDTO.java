package com.hivescm.tms.api.dto.es.transport.redundancy;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class RfTransportWaybillEsDTO  implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7361599498417274297L;
	/**
	 * 网点ID
	 */
	private   @Mapping @ApiModelProperty("网点ID") Integer branchId;
	/**
	 * 公司ID
	 */
	private  @Mapping @ApiModelProperty("公司ID") Long companyId;
	
    /**
     * 发车批次ID
     */
    private @Mapping @ApiModelProperty("发车批次ID") Long transportId;
	 /**
     * 运单ID
     */
    private @Mapping @ApiModelProperty("运单ID") Long waybillId;
    /**
     * 运单编码
     */
    private  @Mapping @ApiModelProperty("运单编码") String waybillCode;
    /**
     * 运单总件数"
     */
    private @Mapping  @ApiModelProperty("运单总件数") Integer totalNum;
    /**
     * 装车件数
     */
    private    @Mapping    @ApiModelProperty("装车件数")  Integer loadAmount;


    private    @Mapping    @ApiModelProperty("卸车件数")  Integer unLoadAmount;

    /**
     * 创建人
     */
    private  @Mapping @ApiModelProperty("创建人") Integer createUser;

    /**
     * 创建时间
     */
    private   @Mapping @ApiModelProperty("创建时间") Long createTime;

    /**
     * 修改人
     */
    private   @Mapping @ApiModelProperty("修改人")  Integer updateUser;

    /**
     * 修改时间
     */
    private  @Mapping @ApiModelProperty("修改时间") Long updateTime;
    
}
