package com.hivescm.tms.finance.server.dao.entity.finance;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Description:
 * @Author: MaGuoDong
 * @Date: Created in 2018/5/7 14:32
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class FinanceManageGoodsGrantDO implements Serializable {

    private static final long serialVersionUID = 6160679972465265481L;

    /**
     * 主键ID
     */
    @Mapping
    @ApiModelProperty("主键ID")
    private Long id;

    /**
     * 付款网点ID
     */
    @Mapping
    @ApiModelProperty("付款网点ID")
    private Integer payBranchId;

    /**
     * 公司ID
     */
    @Mapping
    @ApiModelProperty("公司ID")
    private Long companyId;

    /**
     * 收单状态
     */
    @Mapping
    @ApiModelProperty("收单状态")
    private Integer confirmBillwayStatus;

    /**
     * 发放状态
     */
    @Mapping
    @ApiModelProperty("发放状态")
    private Integer sendStatus;

    /**
     * 进账状态
     */
    @Mapping
    @ApiModelProperty("进账状态")
    private Integer acountStatus;

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
     * 收款方
     */
    @Mapping
    @ApiModelProperty("收款方")
    private String receiveName;

    /**
     * 已发货款
     */
    @Mapping
    @ApiModelProperty("已发货款")
    private BigDecimal sendAmount;

    /**
     * 未发货款
     */
    @Mapping
    @ApiModelProperty("未发货款")
    private BigDecimal unsendAmount;

    /**
     * 已结货款扣
     */
    @Mapping
    @ApiModelProperty("已结货款扣")
    private BigDecimal goodsAmount;

    /**
     * 未结货款扣
     */
    @Mapping
    @ApiModelProperty("未结货款扣")
    private BigDecimal ungoodsAmount;

    /**
     * 已结货款手续费
     */
    @Mapping
    @ApiModelProperty("已结货款手续费")
    private BigDecimal goodsFee;


    /**
     * 未结货款手续费
     */
    @Mapping
    @ApiModelProperty("未结货款手续费")
    private BigDecimal ungoodsFee;
    
    /**
     * 付款手续费
     */
    @Mapping
    @ApiModelProperty("付款手续费")
    private BigDecimal payFee;

    /**
     * 实付金额
     */
    @Mapping
    @ApiModelProperty("实付金额")
    private BigDecimal actualAmount;

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
     * 收单确认人ID
     */
    @Mapping
    @ApiModelProperty("收单确认人ID")
    private Integer confirmBillwayUserId;

    /**
     * 收单确认时间
     */
    @Mapping
    @ApiModelProperty("收单确认时间")
    private Long confirmBillwayTime;

    /**
     * 发放人ID
     */
    @Mapping
    @ApiModelProperty("发放人ID")
    private Integer sendUserId;

    /**
     * 发放时间
     */
    @Mapping
    @ApiModelProperty("发放时间")
    private Long sendTime;

    /**
     * 进账确认人ID
     */
    @Mapping
    @ApiModelProperty("进账确认人ID")
    private Integer confirmAccountUserId;

    /**
     * 进账确认时间
     */
    @Mapping
    @ApiModelProperty("进账确认时间")
    private Long confirmAccountTime;

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

    /**
     * 货款发放信息id列表
     */
    @ApiModelProperty("货款发放信息id列表")
    private List<Long> idList;
}
