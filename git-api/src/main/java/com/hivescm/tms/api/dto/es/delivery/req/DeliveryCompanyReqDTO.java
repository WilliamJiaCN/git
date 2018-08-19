package com.hivescm.tms.api.dto.es.delivery.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 获取快递公司请求类
 * @author qsk
 * @date 2018年1月23日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class DeliveryCompanyReqDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    //模糊检索
    @ApiModelProperty("快递公司名称")
    private String companyName;


    //=======================仓储服务商设置查询条件
    @ApiModelProperty("仓储服务商Id")
    private Long providerId;

    @ApiModelProperty("仓库Id")
    private Long warehouseId;



    //=======================经销商服务设置查询条件
    @ApiModelProperty("经销商Id")
    private Long dealerId;


}
