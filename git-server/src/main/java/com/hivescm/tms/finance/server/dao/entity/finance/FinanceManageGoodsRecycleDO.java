package com.hivescm.tms.finance.server.dao.entity.finance;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description:
 * @Author: MaGuoDong
 * @Date: Created in 2018/5/7 14:37
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class FinanceManageGoodsRecycleDO implements Serializable {

    private static final long serialVersionUID = -1983390046683911346L;

    /**
     * 主键ID
     */
    @Mapping
    @ApiModelProperty("主键ID")
    private Long id;

    /**
     * 收款网点ID
     */
    @Mapping
    @ApiModelProperty("收款网点ID")
    private Integer receiveBranchId;

    /**
     * 公司ID
     */
    @Mapping
    @ApiModelProperty("公司ID")
    private Long companyId;

    /**
     * 回收状态
     */
    @Mapping
    @ApiModelProperty("回收状态")
    private Integer recycleStatus;
    
    /**
     * 发放状态
     */
    @Mapping
    @ApiModelProperty("发放状态")
    private Integer sendStatus;

    /**
     * 汇款状态
     */
    @Mapping
    @ApiModelProperty("汇款状态")
    private Integer remitStatus;

    /**
     * 运单ID
     */
    @Mapping
    @ApiModelProperty("运单ID")
    private Long waybillId;

    /**
     * 来源单号
     */
    @Mapping
    @ApiModelProperty("来源单号")
    private String orderSourceCode;

    /**
     * 付款方
     */
    @Mapping
    @ApiModelProperty("付款方")
    private String payeeName;

    /**
     * 已收货款
     */
    @Mapping
    @ApiModelProperty("已收货款")
    private BigDecimal receiptedAmount;

    /**
     * 未收货款
     */
    @Mapping
    @ApiModelProperty("未收货款")
    private BigDecimal unreceiptAmount;

    /**
     * 本次回收
     */
    @Mapping
    @ApiModelProperty("本次回收")
    private BigDecimal receiptAmount;

    /**
     * 汇款网点ID
     */
    @Mapping
    @ApiModelProperty("汇款网点ID")
    private Integer remitBranchId;

    /**
     * 汇出账户
     */
    @Mapping
    @ApiModelProperty("汇出账户")
    private String remitOutAccount;

    /**
     * 汇入账户
     */
    @Mapping
    @ApiModelProperty("汇入账户")
    private String remitInAccount;

    /**
     * 汇款备注
     */
    @Mapping
    @ApiModelProperty("汇款备注")
    private String remitRemark;

    /**
     * 回收人ID
     */
    @Mapping
    @ApiModelProperty("回收人ID")
    private Integer recycleUserId;

    /**
     * 回收时间
     */
    @Mapping
    @ApiModelProperty("回收时间")
    private Long recycleTime;

    /**
     * 汇款人ID
     */
    @Mapping
    @ApiModelProperty("汇款人ID")
    private Integer remitUserId;

    /**
     * 汇款时间
     */
    @Mapping
    @ApiModelProperty("汇款时间")
    private Long remitTime;

    /**
     * 创建人ID
     */
    @Mapping
    @ApiModelProperty("创建人ID")
    private Integer createUserId;

    /**
     * 创建时间
     */
    @Mapping
    @ApiModelProperty("创建时间")
    private Long createTime;

    /**
     * 修改人ID
     */
    @Mapping
    @ApiModelProperty("修改人ID")
    private Integer updateUserId;

    /**
     * 修改时间
     */
    @Mapping
    @ApiModelProperty("修改时间")
    private Long updateTime;

}
