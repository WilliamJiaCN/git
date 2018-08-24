package com.hivescm.tms.api.dto.es.exception;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 罚款单信息
 *
 * @author 李洪春
 */
@Data
@ToString
public class ForfeitBillEsDTO implements Serializable {
    private static final long serialVersionUID = -3917233168484515656L;
    /**
     * 主键
     */
    @Mapping
    @ApiModelProperty("主键")
    private Long id;

    /**
     * 公司ID
     */
    @Mapping
    @ApiModelProperty("公司ID")
    private Long companyId;

    /**
     * 罚款单号
     */
    @Mapping
    @ApiModelProperty("罚款单号")
    private String forfeitCode;

    /**
     * 状态
     */
    @Mapping
    @ApiModelProperty("状态")
    private Integer status;

    /**
     * 罚款金额
     */
    @Mapping
    @ApiModelProperty("罚款金额")
    private BigDecimal forfeitAmount;

    /**
     * 分摊方式
     */
    @Mapping
    @ApiModelProperty("分摊方式")
    private Integer shareType;

    /**
     * 发生时间
     */
    @Mapping
    @ApiModelProperty("发生时间")
    private Long happenTime;

    /**
     * 付款方类型 1、网点 2、承运商 3、司机
     */
    @Mapping
    @ApiModelProperty("付款方类型 1、网点 2、承运商 3、司机")
    private Integer payerType;

    /**
     * 付款方ID
     */
    @Mapping
    @ApiModelProperty("付款方ID")
    private Integer payerId;
    @ApiModelProperty("付款方姓名")
    private String payerName;

    /**
     * 收款方ID
     */
    @Mapping
    @ApiModelProperty("收款方ID")
    private Integer payeeId;
    @ApiModelProperty("收款方名称")
    private String payeeName;

    /**
     * 费用项目
     */
    @Mapping
    @ApiModelProperty("费用项目")
    private Integer expenseItem;

    /**
     * 备注信息
     */
    @Mapping
    @ApiModelProperty("备注信息")
    private String remark;

    /**
     * 办结人
     */
    @Mapping
    @ApiModelProperty("办结人")
    private Integer closeUser;
    @ApiModelProperty("办结人姓名")
    private String closeUserName;

    /**
     * 办结时间
     */
    @Mapping
    @ApiModelProperty("办结时间")
    private Long closeTime;

    /**
     * 发起人
     */
    @Mapping
    @ApiModelProperty("发起人")
    private Integer createUser;
    @ApiModelProperty("创建人姓名")
    private String createUserName;

    /**
     * 发起时间
     */
    @Mapping
    @ApiModelProperty("发起时间")
    private Long createTime;

    /**
     * 修改人
     */
    @Mapping
    @ApiModelProperty("修改人")
    private Integer updateUser;
    @ApiModelProperty("修改人姓名")
    private String updateUserName;

    /**
     * 修改时间
     */
    @Mapping
    @ApiModelProperty("修改时间")
    private Long updateTime;

    //********************************冗余字段************************************************

    @ApiModelProperty(hidden = true)
    private String waybillCode;
}