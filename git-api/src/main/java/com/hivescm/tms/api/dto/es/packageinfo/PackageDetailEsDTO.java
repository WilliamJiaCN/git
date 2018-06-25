package com.hivescm.tms.api.dto.es.packageinfo;

import java.io.Serializable;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PackageDetailEsDTO implements Serializable {

	 /**
	 * 
	 */
	private static final long serialVersionUID = -1009753381436997774L;

	/**
     * 主键
     */
	@Mapping
    private Long id;

    /**
     * 公司ID
     */
	@Mapping @Logger
    private Long companyId;
	
	 /**
     * 运单ID
     */
	@Mapping
    private Long waybillId;

    /**
     * SKUID
     */
	@Mapping @Logger
    private String skuid;

    /**
     * 商品名称
     */
	@Mapping
    private String goodsName;

    /**
     * 包裹id
     */
	@Mapping @Logger
    private Long packageId;

    /**
     * 数量
     */
	@Mapping
    private Integer amount;

    /**
     * 单位
     */
	@Mapping
    private String unit;

    /**
     * 主单位数量
     */
	@Mapping
    private Integer mainUnitNum;

    /**
     * 主单位
     */
	@Mapping
    private String mainUnit;

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
	
	/*****************************冗余主表信息********************************/
	
	/**
     * 存储规格
     */
	@Mapping
    private String storageFormat;
	
	/**
     * 包裹号
     */
	@Mapping @Logger
    private String packageCode;
}
