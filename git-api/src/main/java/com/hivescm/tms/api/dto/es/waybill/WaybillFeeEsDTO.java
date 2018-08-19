package com.hivescm.tms.api.dto.es.waybill;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 运单运费关联表 ES DTO
 * @author zhenming.du
 * @date 2017年7月17日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class WaybillFeeEsDTO implements Serializable{
	
	
	
	private static final long serialVersionUID = 1521331465954381957L;
	// 根据waybillId分组
	public Long groupByWaybillId(){
		return this.waybillId;
	}
	
	/**
	 * id
	 */
    private @Mapping({"id","waybillAttrFeeDO.id"})   @ApiModelProperty("主键") Long id;
    /**
     * 运单id
     */
    private @Mapping({"waybillId","waybillAttrFeeDO.waybillId"})   @ApiModelProperty("运单id") Long waybillId;
    /**
     * 公司id
     */
    private @Mapping({"companyId","waybillAttrFeeDO.companyId"})   @ApiModelProperty("公司id") Integer companyId;
    /**
     * 公司名称
     */
    private @Mapping @ApiModelProperty("公司名称") String companyName;
    /**
     * 属性id
     */
    private @Mapping({"attrId","waybillAttrFeeDO.attrId"})   @ApiModelProperty("属性id") Integer attrId;
    /**
     * 属性名称
     */
    private @Mapping({"attrName","waybillAttrFeeDO.attrName"})   @ApiModelProperty("属性名称") String attrName;
    /**
     * 费用
     */
    private @Mapping({"fee","waybillAttrFeeDO.fee"})   @ApiModelProperty("费用") BigDecimal fee;
    /**
     * 创建人
     */
    private @Mapping({"createUser","waybillAttrFeeDO.createUser"})   @ApiModelProperty("创建人") Integer createUser;
    /**
     * 创建人姓名
     */
    private @Mapping @ApiModelProperty("创建人姓名") String createUserName;
    /**
     * 创建时间
     */
    private @Mapping   @ApiModelProperty("创建时间") Long createTime;
    /**
     * 修改人
     */
    private @Mapping({"updateUser","waybillAttrFeeDO.updateUser"})   @ApiModelProperty("修改人") Integer updateUser;
    /**
     * 修改人姓名
     */
    private @Mapping @ApiModelProperty("修改人姓名") String updateUserName;
    /**
     * 修改时间
     */
    private @Mapping  @ApiModelProperty("修改时间") Long updateTime;

}