package com.hivescm.tms.api.dto.es.finance.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @Author: MaGuoDong
 * @Date: Created in 2018/5/2 18:02
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class VerifyFinanceReceiptReqDTO implements Serializable {

    private static final long serialVersionUID = 2227072657083220075L;

    /**
     * 公司Id
     */
    @ApiModelProperty(value = "公司Id", notes = "前端调用时不传")
    private Long companyId;

    /**
     * 操作用户ID
     */
    @ApiModelProperty(value = "操作用户ID", notes = "前端调用时不传")
    private Integer opUserId;

    /**
     * 操作用户名称
     */
    @ApiModelProperty(value = "操作用户名称", notes = "前端调用时不传")
    private String opUserName;

    /**
     * 网点ID
     */
    @ApiModelProperty(value = "网点ID", notes = "前端调用时不传")
    private Integer branchId;

    /**
     * 网点名称
     */
    @ApiModelProperty(value = "网点名称" , notes = "前端调用时不传")
    private String branchName;

    @ApiModelProperty("类型1:审核2:取消审核")
    private Integer type;

    @ApiModelProperty("应收审核集合")
    List<VerifyFinanceReceiptEsDTO> verifyFinanceReceiptEsDTOList;


}
