package com.hivescm.tms.api.dto.es.dispatcher.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.tms.api.dto.es.sign.UnitLevelDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class DispatcherPackageDetailsRespDTO implements Serializable {

   private static final long serialVersionUID = 1L;

    /**
     * 运单货物明细id
     */
    private @Mapping @ApiModelProperty("运单货物明细id") Long id;
   /**
    * 商品名称
    */
   private @Mapping @ApiModelProperty("商品名称") String goodsName;

   /**
    * 包裹id
    */
   private @Mapping @ApiModelProperty("包裹id") Long packageId;

   /**
    * 数量
    */
   private @Mapping @ApiModelProperty("数量") Integer amount;

   /**
    * 单位
    */
   private @Mapping @ApiModelProperty("单位") String unit;

   /**
    * 主单位数量
    */
   private @Mapping @ApiModelProperty("主单位数量") Integer mainUnitNum;

   /**
    * 主单位
    */
   private @Mapping @ApiModelProperty("主单位") String mainUnit;

   /**
    * skuid
    */
   private @Mapping @ApiModelProperty("skuid") String skuid;

    /**
     * 最大取货量
     */
   private @Mapping @ApiModelProperty("最大取货量") Integer maxDelivery;
   
   /**
    * 实际取货量
    */
   private @Mapping @ApiModelProperty("实际取货量") Integer deliveryNum;


   /**
    * 三级单位
    */
   private @Mapping @ApiModelProperty("三级单位") List<UnitLevelDTO> unitLevelDTOS;

}
