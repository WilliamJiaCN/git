package com.hivescm.tms.api.dto.es.waybill.response;

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
public class ChangeWaybillFeeEsRespDTO implements Serializable{
	
	private static final long serialVersionUID = 1521331465954381957L;
	// 根据waybillId分组
	
	/**
	 * id
	 */
    private @Mapping({"id","waybillAttrFeeDO.id"})   @ApiModelProperty("主键") Long id;

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

}