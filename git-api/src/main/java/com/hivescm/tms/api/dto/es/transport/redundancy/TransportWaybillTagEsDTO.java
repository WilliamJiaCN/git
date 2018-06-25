package com.hivescm.tms.api.dto.es.transport.redundancy;

import java.io.Serializable;
import java.math.BigDecimal;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TransportWaybillTagEsDTO  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3909302632223176086L;
	/**
	 * ID
	 */
	private  @Mapping @ApiModelProperty("网点ID") Long id;
	/**
	 * 网点ID
	 */
	private  @Mapping @ApiModelProperty("网点ID") Integer branchId;
	/**
	 * 公司ID
	 */
	private  @Mapping @ApiModelProperty("公司ID") Long companyId;
	
	 /**
     * 标签号
     */
    private   @Mapping @ApiModelProperty("标签号") String tagNo;
    
    /**
     * 标签ID
     */
    private   @Mapping @ApiModelProperty("标签ID") Long tagId;
    
    /**
     * 发车批次ID
     */
    private  @Mapping @ApiModelProperty("发车批次ID") Long transportId;

    /**
     * 到货批次ID
     */
    private  @Mapping @ApiModelProperty("到货批次ID") Long arrivalId;
    
    /**
     * 运单ID
     */
    private  @Mapping @ApiModelProperty("运单ID") Long waybillId;
    
    /**
     * 运单编码
     */
    private   @Mapping @ApiModelProperty("运单编码") String waybillCode;
    
    /**
     * 运单总件数
     */
    private @Mapping  @ApiModelProperty("运单总件数") Integer totalNum;
    
    /**
     *  运单总重量
     */
    private @Mapping  @ApiModelProperty("总重量") BigDecimal weight;
    
    /**
     *  运单总体积
     */
    private @Mapping  @ApiModelProperty("总体积") BigDecimal volume;
    
    /**
     * 收货人城市名称
     */
    private @Mapping  @ApiModelProperty("收货人城市名称")String receiptCityName;
    /**
     * 标签件数
     */
    private   @Mapping @ApiModelProperty("标签货物件数")  Integer tagAmount;
    
    /**
     * 扫描时间
     */
    private   @Mapping @ApiModelProperty("扫描时间")  Long scanTime;
    
    /**
     * 装车 = 1，到货 = 2
     */
    private   @Mapping @ApiModelProperty("装车 = 1，到货 = 2")  Integer type;
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
    
    
    /**-------------------------------冗余字段--------------------------------------------*/
    /**
     * 创建人姓名
     */
    @ApiModelProperty("创建人姓名")
    private String createUserName;

    /**
     * 修改人姓名
     */
    @ApiModelProperty("修改人姓名")
    private String updateUserName;
}
