package com.hivescm.tms.api.dto.es.finance.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description:
 * @Author: MaGuoDong
 * @Date: Created in 2018/5/3 21:54
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class ReceivableFinanceReceiptEsDTO implements Serializable {

    private static final long serialVersionUID = -4189037649792493115L;


    @Mapping
    @ApiModelProperty("应收ID")
    private Long id;

    @Mapping
    @ApiModelProperty("已收金额")
    private BigDecimal receiptedAmount;


    /**
     * 公司ID
     */
    @Mapping
    @ApiModelProperty("公司ID")
    private  Long companyId;
    
    
    @Mapping
    @ApiModelProperty("未收金额")
    private BigDecimal unreceiptAmount;

    @Mapping
    @ApiModelProperty("付款状态")
    private Integer deliveryStatus;

    @Mapping
    @ApiModelProperty("修改人ID")
    private Integer updateUserId;

    @Mapping
    @ApiModelProperty("修改时间")
    private Long updateTime;

}
