package com.hivescm.tms.api.dto.es.waybill.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Description:
 * @Author: MaGuoDong
 * @Date: Created in 2018/5/24 15:07
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class WaybillListRespDTO implements Serializable {

    private static final long serialVersionUID = 1806102033624441864L;

    private  @ApiModelProperty("运单号") String code ;

    /**
     * 创建时间
     */
    private @Mapping
    @ApiModelProperty("创建时间") Long createTime;

    /**
     * 发货人省名称
     */
    private @Mapping  @ApiModelProperty("发货人省名称")String invoiceProvName;

    /**
     * 发货人城市名称
     */
    private @Mapping  @ApiModelProperty("发货人城市名称")String invoiceCityName;

    /**
     * 收货人省名称
     */
    private @Mapping  @ApiModelProperty("收货人省名称")String receiptProvName;


    /**
     * 收货人城市名称
     */
    private @Mapping  @ApiModelProperty("收货人城市名称")String receiptCityName;


    /**
     * 收货人详细地址
     */
    private @Mapping  @ApiModelProperty("收货人详细地址")String receiptAddress;


}
