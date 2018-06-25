package com.hivescm.tms.api.dto.es.finance;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description:
 * @Author: MaGuoDong
 * @Date: Created in 2018/5/7 15:14
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class FinanceManageGoodsRecycleEsDTO implements Serializable, Cloneable{

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
     * 回收状态名字
     */
    @Mapping
    @ApiModelProperty("回收状态名字")
    private String recycleStatusName;
    
    /**
     * 发放状态
     */
    @Mapping
    @ApiModelProperty("发放状态")
    private Integer sendStatus;

    /**
     * 发放状态名字
     */
    @Mapping
    @ApiModelProperty("发放状态名字")
    private String sendStatusName;

    /**
     * 汇款状态
     */
    @Mapping
    @ApiModelProperty("汇款状态")
    private Integer remitStatus;

    /**
     * 汇款状态名字
     */
    @Mapping
    @ApiModelProperty("汇款状态名字")
    private String remitStatusName;

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
     * 汇款网点名字
     */
    @Mapping
    @ApiModelProperty("汇款网点名字")
    private String remitBranchName;

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
     * 回收人名字
     */
    @Mapping
    @ApiModelProperty("回收人名字")
    private String recycleUserName;

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
     * 汇款人名字
     */
    @Mapping
    @ApiModelProperty("汇款人名字")
    private String remitUserName;

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
     * 创建人名字
     */
    @Mapping
    @ApiModelProperty("创建人名字")
    private String createUserName;

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
     * 修改人名字
     */
    @Mapping
    @ApiModelProperty("修改人名字")
    private String updateUserName;

    /**
     * 修改时间
     */
    @Mapping
    @ApiModelProperty("修改时间")
    private Long updateTime;

    /****************************************************** 冗余运单信息 *******************************************************/


    /**
     * 录单时间
     */
    @Mapping
    @ApiModelProperty("录单时间")
    private Long waybillCreateTime;

    /**
     * 运单状态
     */
    @Mapping
    @ApiModelProperty("运单状态")
    private Integer status;

    /**
     * 运单状态名字
     */
    @Mapping
    @ApiModelProperty("运单状态名字")
    private String statusName;

    /**
     * 签收状态
     */
    @Mapping
    @ApiModelProperty("签收状态")
    private Integer signStatus;

    /**
     * 签收状态名字
     */
    @Mapping
    @ApiModelProperty("签收状态名字")
    private String signStatusName;

    /**
     * 签收时间
     */
    @Mapping
    @ApiModelProperty("签收时间")
    private Long signTime;

    /**
     * 代收货款
     */
    @Mapping
    @ApiModelProperty("代收货款")
    private BigDecimal deliveryAmount;

    /**
     * 货款扣
     */
    @Mapping
    @ApiModelProperty("货款扣")
    private BigDecimal goodsAmount;

    /**
     * 代收货款手续费
     */
    @Mapping
    @ApiModelProperty("代收货款手续费")
    private BigDecimal deliveryGoodsAmount;

    /**
     * 收款网点名称
     */
    @Mapping
    @ApiModelProperty("收款网点名称")
    private String deliveryNetworkName;


    /**
     * 发货公司ID
     */
    @Mapping
    @ApiModelProperty("发货公司ID")
    private Integer invoiceCompanyId;

    /**
     * 发货公司名称
     */
    @Mapping
    @ApiModelProperty("发货公司名称")
    private String invoiceCompanyName;

    /**
     * 发货人ID
     */
    @Mapping
    @ApiModelProperty("发货人ID")
    private Integer invoiceUserId;

    /**
     * 发货人名称
     */
    @Mapping
    @ApiModelProperty("发货人名称")
    private String invoiceUserName;


    /**
     * 发货人手机号
     */
    @Mapping
    @ApiModelProperty("发货人手机号")
    private String invoiceUserMobile;

    /**
	 * 发货人电话		
	 */
    @Mapping
	private @ApiModelProperty("发货人电话")String invoiceTelNo;

    /**
     * 收货公司ID
     */
    @Mapping
    @ApiModelProperty("收货公司ID")
    private Integer receiptCompanyId;


    /**
     * 收货公司名称
     */
    @Mapping
    @ApiModelProperty("收货公司名称")
    private String receiptCompanyName;

    /**
     * 收货人ID
     */
    @Mapping
    @ApiModelProperty("收货人ID")
    private Integer receiptUserId;

    /**
     * 收货人名称
     */
    @Mapping
    @ApiModelProperty("收货人名称")
    private String receiptUserName;

    /**
     * 收货人手机号
     */
    @Mapping
    @ApiModelProperty("收货人手机号")
    private String receiptUserMobile;
    /**
	 * 收货人电话		
	 */
	private @Mapping  @ApiModelProperty("收货人电话")String receiptConsignorTelNo;

    /**
     * 发货网点ID
     */
    @Mapping
    @ApiModelProperty("发货网点ID")
    private Integer invoiceOrgId;

    /**
     * 发货网点名称
     */
    @Mapping
    @ApiModelProperty("发货网点名称")
    private String invoiceOrgName;

    /**
     * 目的地
     */
    @Mapping
    @ApiModelProperty("目的地")
    private String destName;

    /**
     * 目的地ID
     */
    @Mapping
    @ApiModelProperty("目的地ID")
    private String destId;

    /**
     * 目的地网点ID
     */
    @Mapping
    @ApiModelProperty("目的地网点ID")
    private Integer destOrgId;

    /**
     * 目的地网点名称
     */
    @Mapping
    @ApiModelProperty("目的地网点名称")
    private String destOrgName;

    /**
     * 始发地ID
     */
    @Mapping
    @ApiModelProperty("始发地ID")
    private String starId;

    /**
     * 始发地名字
     */
    @Mapping
    @ApiModelProperty("始发地名字")
    private String starName;

    /**
     * 始发地网点ID
     */
    @Mapping
    @ApiModelProperty("始发地网点ID")
    private Integer starOrgId;

    /**
     * 始发地网点名字
     */
    @Mapping
    @ApiModelProperty("始发地网点名字")
    private String starOrgName;

    /**
     * 备注
     */
    @Mapping
    @ApiModelProperty("备注")
    private String remark;

    /**
     * 业务时间
     */
    @Mapping
    @ApiModelProperty("业务时间")
    private Long businessTime;

    /**
     * 业务员ID
     */
    @Mapping
    @ApiModelProperty("业务员ID")
    private Integer salesmanId;



    /************************************************************** 外发冗余字段******************************************************************************/

    /**
     * 是否中转
     */
    @Mapping
    @ApiModelProperty("是否中转")
    private Boolean isTransit;

    /**
     * 中转id
     */
    @Mapping
    @ApiModelProperty("中转id")
    private Long transitBillId;
    
    /**
     * 中转公司ID
     */
    @Mapping
    @ApiModelProperty("中转公司ID")
    private Integer transitCompanyId;

    /**
     * 中转公司
     */
    @Mapping
    @ApiModelProperty("中转公司")
    private String transitCompanyName;

    /**
     * 中转单号
     */
    @Mapping
    @ApiModelProperty("中转单号")
    private String transitBillCode;

    /**
     * 中转网点ID
     */
    @Mapping
    @ApiModelProperty("中转网点ID")
    private Integer transitNetworkId;

    /**
     * 中转网点名字
     */
    @Mapping
    @ApiModelProperty("中转网点名字")
    private String transitNetworkName;

    /**
     * 中转时间
     */
    @Mapping
    @ApiModelProperty("中转时间")
    private Long transitTime;
    

    /**
     * 收货人会员ID
     */
    @Mapping
    @ApiModelProperty("收货人会员ID")
    private Integer receiptCustomerVipId;

    /**
     * 收货客户ID
     */
    @Mapping
    @ApiModelProperty("收货客户ID")
    private Integer receiptCustomerId;

    
    /**
     * 业务员
     */
    @Mapping
    @ApiModelProperty("业务员")
    private Integer salesMan;

    @Override
    public FinanceManageGoodsRecycleEsDTO clone() {
        FinanceManageGoodsRecycleEsDTO financeManageGoodsRecycleEsDTO = null;
        try{
            financeManageGoodsRecycleEsDTO = (FinanceManageGoodsRecycleEsDTO)super.clone();
        }catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return financeManageGoodsRecycleEsDTO;
    }

}
