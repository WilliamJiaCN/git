package com.hivescm.tms.api.dto.es.bill.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 单据领用请求DTO
 *
 * @author ke.huang
 * @date 2017年10月13日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class BillInfoWaybillCollarReqDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty("公司ID")
    private Long companyId;
    @ApiModelProperty("单据Ids")
    private List<Long> billInfoIds;
    @ApiModelProperty("单据Ids")
    private List<String> billCodes;
    @ApiModelProperty("操作人ID")
    private Integer operateUserId;
    @ApiModelProperty("操作人姓名")
    private String operateUserName;

    public Boolean isValid() {
        return null != this.companyId && null != this.billInfoIds && this.billInfoIds.size() != 0;
    }

    public Boolean isUsed() {
        return null != this.companyId && null != this.billCodes && this.billCodes.size() != 0;
    }
}
