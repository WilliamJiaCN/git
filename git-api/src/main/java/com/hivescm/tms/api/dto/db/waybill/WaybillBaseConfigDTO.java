package com.hivescm.tms.api.dto.db.waybill;

import java.io.Serializable;
import java.math.BigDecimal;

import com.hivescm.framework.validation.annotation.Required;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author
 * @Talbe(name="waybill_base_config")
 */
@Data
@ToString
public class WaybillBaseConfigDTO implements Serializable {

    /**
     * 主键
     */
    private Long id;

    /**
     * 公司ID
     */
    @Required
    private Integer companyId;

    /**
     * 创建运单显示方式
     */
    @Required
    @ApiModelProperty("创建运单显示方式， 1、完整版 2、简易版")
    private Integer edition;

    /**
     * 单号编码方式
     */
    @Required
    @ApiModelProperty("运单编码方式, 1,企业自动编号   2、单据领用")
    private Integer orderCodeType;

    /**
     * 运单编码系统自动生成时是否可以手动编辑
     */
    @Required
    @ApiModelProperty("运单编码系统自动生成时是否可以手动编辑")
    private Boolean orderCodeEditable;

    /**
     * 运单号长度
     */
    @Required
    @ApiModelProperty("运单号长度")
    private Integer orderCodeLength;

    /**
     * 是否启用黑名单功能
     */
    @Required
    @ApiModelProperty("是否启动黑名单功能")
    private Boolean blacklistOn;

    /**
     * 基础运费取值方式
     */
    @Required
    @ApiModelProperty("基础运费取值方式     1,四舍五入   2，向上  ")
    private Integer baseFeeCaculateType;

    /**
     * 基础运费小数点后保留位数
     */
    @Required
    @ApiModelProperty("基础运费小数点后保留位数")
    private Integer baseFeeCaculateDigits;

    /**
     * 代收货款手续费率
     */
    @Required
    @ApiModelProperty("代收货款手续费率")
    private BigDecimal coDeliveryFeeRate;

    /**
     * 代收货款最低手续费
     */
    @Required
    @ApiModelProperty("代收货款最低手续费")
    private BigDecimal coDeliveryFeeMin;

    /**
     * 代收货款最高手续费
     */
    @Required
    @ApiModelProperty("代收货款最高手续费")
    private BigDecimal coDeliveryFeeMax;

    /**
     * 代收货款手续费取值方式
     */
    @Required
    @ApiModelProperty("代收货款手续费取值方式    1,四舍五入   2，向上")
    private Integer feeRateType;

    /**
     * 代收货款手续费小数点后保留位数
     */
    @Required
    @ApiModelProperty("代收货款手续费小数点后保留位数")
    private Integer feeRateDigits;

    /**
     * 保险费率
     */
    @Required
    @ApiModelProperty("保险费率")
    private BigDecimal insuranceRate;

    /**
     * 保险费最低值
     */
    @Required
    @ApiModelProperty("保险费最低值")
    private BigDecimal insuranceMin;

    /**
     * 保险费最高值
     */ 
    @Required
    @ApiModelProperty("保险费最高值")
    private BigDecimal insuranceMax;

    /**
     * 保险费取值方式
     */
    @Required
    @ApiModelProperty("保险费取值方式  1,四舍五入   2，向上")
    private Integer insuranceCaculateType;

    /**
     * 保险费小数点后位数
     */
    @Required
    @ApiModelProperty("保险费小数点后位数")
    private Integer insuranceDecimalDigits;

    /**
     * 信息费金额
     */
    @Required
    @ApiModelProperty("信息费金额")
    private BigDecimal infoFee;

    /**
     * 税费取值方式
     */
    @Required
    @ApiModelProperty("税费取值方式  1,四舍五入   2，向上")
    private Integer taxFeeCaculateType;

    /**
     * 税费小数点后位数
     */
    @Required
    @ApiModelProperty("税费小数点后位数")
    private Integer taxFeeDecimalDigits;

    /**
     * 费用字段小数点后保留位数
     */
    @Required
    @ApiModelProperty("费用字段小数点后保留位数")
    private Integer feeDecimalDigits;

    /**
     * 付款方式
     */ 
    @Required
    @ApiModelProperty("付款方式")
    private String payWay;

    /**
     * 默认付款方式
     */
    @Required
    @ApiModelProperty("默认付款方式")
    private Integer defaultPayWay;

    /**
     * 保存并打印快捷键
     */
    @Required
    @ApiModelProperty("保存并打印快捷键")
    private String savePrint;

    /**
     * 保存快捷键
     */
    @Required
    @ApiModelProperty("保存快捷键")
    private String save;

    /**
     * 保存并新增快捷键
     */
    @Required
    @ApiModelProperty("保存并新增快捷键")
    private String saveAdd;

    /**
     * 创建人
     */
    @ApiModelProperty(hidden = true)
    private Integer createUser;

    /**
     * 创建时间
     */
    @ApiModelProperty(hidden = true)
    private Long createTime;

    /**
     * 修改人
     */
    @ApiModelProperty(hidden = true)
    private Integer updateUser;

    /**
     * 修改时间
     */
    @ApiModelProperty(hidden = true)
    private Long updateTime;

    private static final long serialVersionUID = 1L;

	@Override
	public String toString() {
		return "WaybillBaseConfigDTO [id=" + id + ", companyId=" + companyId + ", edition=" + edition
				+ ", orderCodeType=" + orderCodeType + ", orderCodeEditable=" + orderCodeEditable + ", orderCodeLength="
				+ orderCodeLength + ", blacklistOn=" + blacklistOn + ", baseFeeCaculateType=" + baseFeeCaculateType
				+ ", baseFeeCaculateDigits=" + baseFeeCaculateDigits + ", coDeliveryFeeRate=" + coDeliveryFeeRate
				+ ", coDeliveryFeeMin=" + coDeliveryFeeMin + ", coDeliveryFeeMax=" + coDeliveryFeeMax + ", feeRateType="
				+ feeRateType + ", feeRateDigits=" + feeRateDigits + ", insuranceRate=" + insuranceRate
				+ ", insuranceMin=" + insuranceMin + ", insuranceMax=" + insuranceMax + ", insuranceCaculateType="
				+ insuranceCaculateType + ", insuranceDecimalDigits=" + insuranceDecimalDigits + ", infoFee=" + infoFee
				+ ", taxFeeCaculateType=" + taxFeeCaculateType + ", taxFeeDecimalDigits=" + taxFeeDecimalDigits
				+ ", feeDecimalDigits=" + feeDecimalDigits + ", payWay=" + payWay + ", defaultPayWay=" + defaultPayWay
				+ ", savePrint=" + savePrint + ", save=" + save + ", saveAdd=" + saveAdd + ", createUser=" + createUser
				+ ", createTime=" + createTime + ", updateUser=" + updateUser + ", updateTime=" + updateTime + "]";
	}

}