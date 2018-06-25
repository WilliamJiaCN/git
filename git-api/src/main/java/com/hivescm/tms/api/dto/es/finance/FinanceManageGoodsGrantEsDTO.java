package com.hivescm.tms.api.dto.es.finance;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Description:
 * @Author: MaGuoDong
 * @Date: Created in 2018/5/7 15:12
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
public class FinanceManageGoodsGrantEsDTO implements Serializable, Cloneable{

    private static final long serialVersionUID = 5276817791314584039L;

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
     * 付款网点名字
     */
    @Mapping
    @ApiModelProperty("付款网点名字")
    private String payBranchName;
    /**
     * 公司ID
     */
    @Mapping
    @ApiModelProperty("公司ID")
    private Long companyId;

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
     * 进账状态
     */
    @Mapping
    @ApiModelProperty("进账状态")
    private Integer acountStatus;

    /**
     * 进账状态名字
     */
    @Mapping
    @ApiModelProperty("进账状态名字")
    private String acountStatusName;

    /**
     * 签收状态（1.未签收 2.部分签收 3.全部签收 4.全部拒签）
     */
    @Mapping
    @ApiModelProperty("签收状态1.未签收 2.部分签收 3.全部签收 4.全部拒签")
    private Integer signStatus;

    /**
     * 签收状态名字（1.未签收 2.部分签收 3.全部签收 4.全部拒签）
     */
    @Mapping
    @ApiModelProperty("签收状态1.未签收 2.部分签收 3.全部签收 4.全部拒签")
    private String signStatusName;

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
     * 发货方ID
     */
    @Mapping
    @ApiModelProperty(value = "发货方ID")
    private Integer shipperId;

    /**
     * 发货方名称
     */
    @Mapping
    @ApiModelProperty(value = "发货方名称")
    private String shipperName;

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
     * 收单状态
     */
    @Mapping
    @ApiModelProperty("收单状态")
    private Integer confirmBillwayStatus;
    /**
     * 收单状态名称
     */
    @Mapping
    @ApiModelProperty("收单状态名称")
    private String confirmBillwayStatusName;
    /**
     * 收单确认人ID
     */
    @Mapping
    @ApiModelProperty("收单确认人ID")
    private Integer confirmBillwayUserId;

    /**
     * 收单确认人名字
     */
    @Mapping
    @ApiModelProperty("收单确认人名字")
    private String confirmBillwayUserName;

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
     * 发放人名字
     */
    @Mapping
    @ApiModelProperty("发放人名字")
    private String sendUserName;

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
     * 进账确认人名字
     */
    @Mapping
    @ApiModelProperty("进账确认人名字")
    private String confirmAccountUserName;

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
     * 创建人姓名
     */
    @Mapping
    @ApiModelProperty("创建人姓名")
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
     * 修改人姓名
     */
    @Mapping
    @ApiModelProperty("修改人姓名")
    private String updateUserName;

    /**
     * 修改时间
     */
    @Mapping
    @ApiModelProperty("修改时间")
    private Long updateTime;

    /*****************************************冗余运单字段**********************************************/

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
    private BigDecimal goodsAmountFee;

    /**
     * 代收货款手续费
     */
    @Mapping
    @ApiModelProperty("代收货款手续费")
    private BigDecimal deliveryGoodsAmount;


    /**
     * 发货公司ID
     */
    @Mapping
    @ApiModelProperty("发货公司ID")
    private Integer invoiceCompanyId;

    /**
     * 发货公司
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
     * 目的地网点名字
     */
    @Mapping
    @ApiModelProperty("目的地网点名字")
    private String destOrgName;

    /**
     * 始发地
     */
    @Mapping
    @ApiModelProperty("始发地")
    private String startCityName;

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

    /**
     * 业务员名字
     */
    @Mapping
    @ApiModelProperty("业务员名字")
    private String salesmanName;


    /*********************************外发********************************************************/

    /**
     * 是否中转
     */
    @Mapping
    @ApiModelProperty("是否中转")
    private Boolean isTransit;

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
    private String transitCompany;

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

    /******************************************************收款单冗余字段***************************************************************/

    /**
     * 发货人会员ID
     */
    @Mapping
    @ApiModelProperty("发货人会员ID")
    private Integer invoiceCustomerVipId;

    /**
     * 发货人会员名字
     */
    @Mapping
    @ApiModelProperty("发货人会员名字")
    private String invoiceCustomerVipName;

    /**
     * 发货客户ID
     */
    @Mapping
    @ApiModelProperty("发货客户ID")
    private Integer invoiceCustomerId;

    /**
     * 发货客户名字
     */
    @Mapping
    @ApiModelProperty("发货客户名字")
    private String invoiceCustomerName;



    /**
     * 收货人会员ID
     */
    @Mapping
    @ApiModelProperty("收货人会员ID")
    private Integer receiptCustomerVipId;

    /**
     * 收货人会员名字
     */
    @Mapping
    @ApiModelProperty("收货人会员名字")
    private String receiptCustomerVipName;

    /**
     * 收货客户ID
     */
    @Mapping
    @ApiModelProperty("收货客户ID")
    private Integer receiptCustomerId;

    /**
     * 收货客户名字
     */
    @Mapping
    @ApiModelProperty("收货客户名字")
    private String receiptCustomerName;
    /*******************************************回收冗余字段***************************************/

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

    /***********************************汇款冗余字段*****************************************/

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

    @Override
    public FinanceManageGoodsGrantEsDTO clone() {
        FinanceManageGoodsGrantEsDTO financeManageGoodsGrantEsDto = null;
        try{
            financeManageGoodsGrantEsDto = (FinanceManageGoodsGrantEsDTO)super.clone();
        }catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return financeManageGoodsGrantEsDto;
    }
}
