package com.hivescm.tms.api.dto.es.finance.response;

import java.io.Serializable;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 账户查询
 * @author 杨彭伟
 * @date 2018-01-06 20:26
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PaymentAccountRespTDO implements Serializable {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    @Mapping
    @ApiModelProperty("账户id")
    private Long accountId;
    @Mapping
    @ApiModelProperty("账户名称")
    private String accountName;
}
