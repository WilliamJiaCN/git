package com.hivescm.tms.api.dto.es.waybill.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 根据运单号查询简略版库存信息--在库库存
 * <p>Title: WaybillWarehouseInfoRespDTO</p>
 * <p>Description: </p>
 * <p>Email: syenging@gmail.com</p>
 * <p>Company:http://hivescm.com/ </p>
 *
 * @author Jie.Yang
 * @version 1.0
 * @create 2018-03-24-10:46 AM
 */
@Data
@ToString
public class WaybillWarehouseInfoRespDTO implements Serializable {

    private static final long serialVersionUID = 7092569743878907212L;

    /**
     * 运单号
     */
    @Mapping
    private  @ApiModelProperty("运单号") String code;

    /**
     * 件数
     */
    @Mapping
    private @ApiModelProperty("总件数") Integer totalNum;

    /**
     * 总体积
     */
    @Mapping
    private @ApiModelProperty("总体积")
    BigDecimal totalVolume;

    /**
     * 总重量
     */
    private @Mapping  @ApiModelProperty("总重量") BigDecimal totalWeight;

}
