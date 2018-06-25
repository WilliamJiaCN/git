package com.hivescm.tms.api.dto.es.dispatcher.response;

import java.io.Serializable;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
/**
 * 此实体类作为与运单相关的包裹信息Dto 为运单详情展示包裹用
 * @author lhf
 */
@Data
@ToString
public class DispatcherPackageInfoRespDTO implements Serializable {

   private static final long serialVersionUID = 1L;
   /**
    * 包裹ID
    */
   private @Mapping @ApiModelProperty("包裹ID") Long id;
   /**
    * 公司ID
    */
   private @Mapping @ApiModelProperty("公司ID") Long companyId;

   /**
    * 包裹号
    */
   private @Mapping @ApiModelProperty("包裹号") String packageCode;

   /**
    * 订单编号
    */
   private @Mapping @ApiModelProperty("订单编号") String orderCode;
	 /**
     * 总箱数
     */
   private @Mapping @ApiModelProperty("总箱数") Integer boxNum;

}
