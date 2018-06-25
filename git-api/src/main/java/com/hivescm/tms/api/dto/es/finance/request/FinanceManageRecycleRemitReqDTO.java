package com.hivescm.tms.api.dto.es.finance.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @Author: MaGuoDong
 * @Date: Created in 2018/5/17 16:45
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class FinanceManageRecycleRemitReqDTO implements Serializable{

    /**
     * 公司Id
     */
    @ApiModelProperty(value = "公司Id", notes = "前端调用时不传", hidden = true)
    private Long companyId;

    /**
     * 操作用户ID
     */
    @ApiModelProperty(value = "操作用户ID", notes = "前端调用时不传", hidden = true)
    private Integer opUserId;

    /**
     * 操作用户名称
     */
    @ApiModelProperty(value = "操作用户名称", notes = "前端调用时不传", hidden = true)
    private String opUserName;

    /**
     * 网点ID
     */
    @ApiModelProperty(value = "网点ID", notes = "前端调用时不传", hidden = true)
    private Integer branchId;

    /**
     * 网点名称
     */
    @ApiModelProperty(value = "网点名称", notes = "前端调用时不传", hidden = true)
    private String branchName;

    /**
     * 汇款网点ID
     */
    @ApiModelProperty(value = "汇款对象(汇款网点ID)" , required = true)
    private Integer remitBranchId;

    /**
     * 汇款网点名称
     */
    @ApiModelProperty(value = "汇款对象名称", required = true)
    private String remitBranchName;

    /**
     * 收款账户
     */
    @ApiModelProperty(value = "汇入账户", required = true)
    private String receivableBankAccount;

    /**
     * 付款账户
     */
    @ApiModelProperty(value = "汇出账户", required = true)
    private String payAccount;

    @ApiModelProperty(value = "汇款时间", required = true)
    private Long remitTime;

    @ApiModelProperty(value = "货款回收ID集合", required = true)
    private List<Long> idList;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", required = true)
    private String remark;
}
