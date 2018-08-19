package com.hivescm.tms.api.dto.es.finance.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by 919610864 on 2018/5/1.
 */
@Data
@ToString
public class FinanceManageReceiptReqDTO implements Serializable{

    private static final long serialVersionUID = 1351934962802842207L;

    @Mapping
    @ApiModelProperty("网点ID")
    private Integer branchId;

    @Mapping
    @ApiModelProperty("公司ID")
    private Long companyId;

    @ApiModelProperty("单据类型 1.运单  8.代收货款 9.他其收入")
    private Integer codeType;

    @ApiModelProperty(value = "结算方式 4、现付 5、欠付 6、月结 7、回单付 8、到付 9、货款扣 10、代收货款 11、代收货款手续费")
    private Integer payWay;

    @ApiModelProperty(value = "来源单号")
    private String orderSourceId;
    
    @ApiModelProperty(value = "运单ID")
    private Long waybillId;



}
