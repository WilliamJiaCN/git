package com.hivescm.tms.api.dto.es.packageinfo;

import java.io.Serializable;
import java.util.List;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PackageInfoEsDTO implements Serializable{

	 /**
	 * 
	 */
	private static final long serialVersionUID = -659598764696068348L;

	/**
     * 主键
     */
	@Mapping
    private Long id;

    /**
     * 公司ID
     */
	@Mapping
    private Long companyId;
	
	/**
     * 包裹ID
     */
	@Mapping @Required
    private String packageIdInfo;

    /**
     * 包裹号
     */
	@Mapping @Required @Logger
    private String packageCode;
    
    /**
     * 派车单ID
     */
	@Mapping
    private Long distributionId;

    /**
     * 运单ID
     */
	@Mapping
    private Long waybillId;
	
	/**
     * 运单号
     */
	@Mapping @Required @Logger
    private String waybillCode;

    /**
     * 订单编号
     */
	@Mapping @Logger
    private String orderCode;

    /**
     * 总箱数
     */
	@Mapping
    private Integer boxNum;

    /**
     * 存储规格
     */
	@Mapping
    private String storageFormat;

    /**
     * 创建人
     */
	@Mapping
    private Integer createUser;

    /**
     * 创建时间
     */
	@Mapping
    private Long createTime;

    /**
     * 修改人
     */
	@Mapping
    private Integer updateUser;

    /**
     * 修改时间
     */
	@Mapping
    private Long updateTime;
    
    private List<PackageDetailEsDTO> packageDetailEsDTO;
}
