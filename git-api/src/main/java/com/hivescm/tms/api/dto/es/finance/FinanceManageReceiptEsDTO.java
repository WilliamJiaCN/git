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
 * @Date: Created in 2018/4/27 19:25
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class FinanceManageReceiptEsDTO implements Serializable, Cloneable{

    private static final long serialVersionUID = 4909805369250237657L;
    /**
     * 主键
     */
    @Mapping
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 收款网点ID
     */
    @Mapping
    @ApiModelProperty(value = "收款网点ID")
    private Integer deliveryNetworkId;


    /**
     * 收款网点名称
     */
    @Mapping
    @ApiModelProperty(value = "收款网点名称")
    private String deliveryNetworkName;

    /**
     * 来源单号
     */
    @Mapping
    @ApiModelProperty(value = "来源单号")
    private String orderSourceCode;
    
    /**
     * 运单ID
     */
    @Mapping
    @ApiModelProperty(value = "运单ID")
    private Long waybillId;
    

    /**
     * 公司ID
     */
    @Mapping
    @ApiModelProperty(value = "公司ID")
    private  Long companyId;

    /**
     * 单据类型
     */
    @Mapping
    @ApiModelProperty(value = "单据类型")
    private Integer codeType;

    /**
     * 单据类型名称
     */
    @Mapping
    @ApiModelProperty("单据类型 名称")
    private String codeTypeName;



    /**
     * 支付方式
     */
    @Mapping
    @ApiModelProperty(value = "支付方式  4、现付 5、欠付 6、月结 7、回单付 8、到付 9、货款扣 10、代收货款 11、代收货款手续费")
    private Integer payWay;
    
    /**
     * 支付方式名称
     */
    @Mapping
    @ApiModelProperty(value = "支付方式  4、现付 5、欠付 6、月结 7、回单付 8、到付 9、货款扣 10、代收货款 11、代收货款手续费")
    private String payWayName;

    /**
     * 收银状态
     */
    @Mapping
    @ApiModelProperty(value = "收银状态 1.未审核 2.未收银 3.部分收银 4.已收银")
    private Integer deliveryStatus;

    /**
     * 收银状态名称
     */
    @Mapping
    @ApiModelProperty(value = "收银状态 1.未审核 2.未收银 3.部分收银 4.已收银")
    private String deliveryStatusName;

    /**
     * 应收金额
     */
    @Mapping
    @ApiModelProperty(value = "应收金额")
    private BigDecimal receiptAmount;

    /**
     * 已收金额
     */
    @Mapping
    @ApiModelProperty(value = "已收金额")
    private BigDecimal receiptedAmount;
    
    /**
     * 本次收款金额
     */
    @Mapping
    @ApiModelProperty(value = "本次收款金额")
    private BigDecimal currentReceiptedAmount;

    /**
     * 未收金额
     */
    @Mapping
    @ApiModelProperty(value = "未收金额")
    private BigDecimal unreceiptAmount;

    /**
     * 回单状态
     */
    @Mapping
    @ApiModelProperty("回单状态")
    private Integer backStatus;
    
    /**
     * 回单状态
     */
    @Mapping
    @ApiModelProperty("回单状态名称")
    private String backStatusName;

    /**
     * 回单发放时间
     */
    @Mapping
    @ApiModelProperty("回单发放时间")
    private Long backGrantTime;

    /**
     * 签收状态
     */
    @Mapping
    @ApiModelProperty("签收状态")
    private Integer signStatus;
    
    /**
     * 签收状态
     */
    @Mapping
    @ApiModelProperty("签收状态名称")
    private String signStatusName;

    /**
     * 签收时间
     */
    @Mapping
    @ApiModelProperty("签收时间")
    private Long signTime;

    /**
     * 创建人ID
     */
    @Mapping
    @ApiModelProperty(value = "创建人ID")
    private  Integer createUserId;
    /**
     * 创建人姓名
     */
    @Mapping
    @ApiModelProperty(value = "创建人姓名")
    private  String createUserName;
    /**
     * 创建时间
     */
    @Mapping
    @ApiModelProperty(value = "创建时间")
    private  Long createTime;

    /**
     * 修改人ID
     */
    @Mapping
    @ApiModelProperty(value = "修改人ID")
    private  Integer updateUserId;

    /**
     * 修改时间
     */
    @Mapping
    @ApiModelProperty(value = "修改时间")
    private  Long updateTime;

    /**
     * 审核员ID
     */
    @Mapping
    @ApiModelProperty(value = "审核员ID")
    private  Integer checkUserId;

    /**
     * 审核员
     */
    @Mapping
    @ApiModelProperty(value = "审核员")
    private  String checkUserName;

    /**
     * 审核时间
     */
    @Mapping
    @ApiModelProperty(value = "审核时间")
    private  Long checkTime;

    /**
     * 取消审核员ID
     */
    @Mapping
    @ApiModelProperty(value = "取消审核员ID")
    private  Integer cancelCheckUserId;

    /**
     * 取消审核员名称
     */
    @Mapping
    @ApiModelProperty(value = "取消审核员名称")
    private  String cancelCheckUserName;

    /**
     * 取消审核时间
     */
    @Mapping
    @ApiModelProperty(value = "取消审核时间")
    private  Long cancelCheckTime;


    /****************************************************** 冗余运单信息 *******************************************************/

    /**
     * 发货客户ID
     */
    @Mapping
    @ApiModelProperty("发货方ID")
    private Integer invoiceCustomerId;

    /**
     * 发货人会员ID
     */
    @Mapping
    @ApiModelProperty("发货人会员ID")
    private Integer invoiceCustomerVipId;


    /**
     * 发货人ID
     */
    @Mapping
    @ApiModelProperty("发货人")
    private String invoiceUser;

    /**
     * 发货公司ID
     */
    @Mapping
    @ApiModelProperty("发货方名称")
    private String invoiceCompany;

    /**
     * 付款方ID
     */
    @Mapping
    @ApiModelProperty("付款方ID")
    private Integer payeeId;

    /**
     * 付款方名称
     */
    @Mapping
    @ApiModelProperty("付款方名称")
    private String payeeName;

    /**
     * 发货人电话
     */
    private @Mapping  @ApiModelProperty("发货人电话")String invoiceTelNo;
    /**
     * 发货人手机号码
     */
    private @Mapping  @ApiModelProperty("发货人手机号码")String invoiceMobleNo;

    /**
     * 发货网点ID
     */
    @Mapping
    @ApiModelProperty("发货网点ID")
    private Integer invoiceOrgId;

    /**
     * 发货网点ID
     */
    @Mapping
    @ApiModelProperty("发货网点名称")
    private String invoiceOrgName;

    /**
     * 收货客户ID
     */
    @Mapping
    @ApiModelProperty("收货方ID")
    private Integer receiptCustomerId;

    /**
     * 收货人会员ID
     */
    @Mapping
    @ApiModelProperty("收货人会员ID")
    private Integer receiptCustomerVipId;

    /**
     * 收货公司ID
     */
    @Mapping
    @ApiModelProperty("收货方名称")
    private String receiptCompany;

    /**
     * 收货人
     */
    @Mapping
    @ApiModelProperty("收货人")
    private String receiptUser;

    /**
     * 收货人电话
     */
    private @Mapping  @ApiModelProperty("收货人电话")String receiptConsignorTelNo;

    /**
     * 收货人手机号码
     */
    private @Mapping  @ApiModelProperty("收货人手机号码")String receiptConsignorMobleNo;

    /**
     * 目的地
     */
    @Mapping
    @ApiModelProperty("目的地")
    private String destName;

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
     * 备注
     */
    @Mapping
    @ApiModelProperty("备注")
    private String remark;

    /**
     * 业务时间
     */
    @Mapping
    @ApiModelProperty("业务时间，对应运单开单时间")
    private Long businessTime;


    /**
     * 业务员ID
     */
    @Mapping
    @ApiModelProperty("业务员ID")
    private Integer salesmanId;


    /**
     * 业务员
     */
    @Mapping
    @ApiModelProperty("业务员")
    private String salesmanName;


    /************************************************************** 外发冗余字段******************************************************************************/

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
     * 中转公司名称
     */
    @Mapping
    @ApiModelProperty("中转公司名称")
    private String transitCompanyName;

    /**
     * 中转id
     */
    @Mapping
    @ApiModelProperty("中转id")
    private Long transitBillId;
    
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
     * 中转网点名称
     */
    @Mapping
    @ApiModelProperty("中转网点名称")
    private String transitNetworkName;

    /**
     * 中转时间
     */
    @Mapping
    @ApiModelProperty("中转时间")
    private Long transitTime;

    @Override
    public FinanceManageReceiptEsDTO clone() {
        FinanceManageReceiptEsDTO financeManageReceiptEsDto = null;
        try{
            financeManageReceiptEsDto = (FinanceManageReceiptEsDTO)super.clone();
        }catch(CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return financeManageReceiptEsDto;
    }
}
