package com.hivescm.tms.api.dto.es.finance.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @Author sql
 * @Date 16:082018\5\12 0012
 */
@Data
@ToString
public class CapitalAccountResDTO {
//    @Mapping
//    @ApiModelProperty("所属部门")
//    private String pertainToDepartment;
//    @Mapping
//    @ApiModelProperty("所属用户")
//    private String pertainToUser;
    @Mapping
    @ApiModelProperty("资金账户")
    private String fundAccount;
//    @Mapping
//    @ApiModelProperty("开户银行")
//    private String bank;

}
