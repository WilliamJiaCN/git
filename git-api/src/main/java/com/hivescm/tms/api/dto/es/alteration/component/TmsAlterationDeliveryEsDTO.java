package com.hivescm.tms.api.dto.es.alteration.component;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.tms.api.dto.es.waybill.component.TmsWaybillEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 自提or送货更改组合类
 * @author zhenming.du
 * @date 2017年10月9日
 * @company 蜂网供应链
 */
@Data
@ToString
public class TmsAlterationDeliveryEsDTO implements Serializable {

	private static final long serialVersionUID = -644501899663848418L;
	/**
	 * 主键
	 */
	@Mapping
	private @ApiModelProperty("主键") Long id;
	/**
	 * 公司id
	 */
	@Mapping
    private @ApiModelProperty("公司id") Long companyId;
	/**
	 * 公司名称
	 */
	@Mapping
	private @ApiModelProperty("公司名称") String companyName;
    /**
     * 更改批次
     */
	@Mapping
    private @ApiModelProperty("更改批次") String chengeBatchCode;
    /**
     * 处理状态
     */
	@Mapping
    private @ApiModelProperty("处理状态") Integer processingStatus;
    /**
     * 运单信息
     */
    private @ApiModelProperty("运单信息") TmsWaybillEsDTO tmsWaybillEsDTO;
    /**
     * 预约配送时间
     */
    @Mapping
    private @ApiModelProperty("预约配送时间") Long deliverySendTime;
    /**
	 * 配送方式
	 */
    @Mapping
    private @ApiModelProperty("配送方式") Integer distributionType;
	/**
	 * 收货人
	 */
    @Mapping
    private @ApiModelProperty("收货人") String receiptUser;
    /**
	 * 收货人电话
	 */
    @Mapping({"consignorTelNo","receiptConsignorTelNo"})
    private @ApiModelProperty("收货人电话") String consignorTelNo;
    /**
	 * 收货人手机
	 */
    @Mapping({"consignorMobleNo","receiptConsignorMobleNo"})
    private @ApiModelProperty("收货人手机") String consignorMobleNo;
    /**
	 * 收货地址
	 */
    @Mapping
    private @ApiModelProperty("收货地址") String receiptAddress;
    /**
	 * 送货费
	 */
    @Mapping
    private @ApiModelProperty("送货费") BigDecimal deliveryFee;
    /**
	 * 付款方
	 */
    @Mapping
    private @ApiModelProperty("付款方") String payer;
    /**
	 * 收款方
	 */
    @Mapping
    private @ApiModelProperty("收款方") String payee;
    /**
	 * 备注
	 */
    @Mapping({"remark","alterationRemark"})
    private @ApiModelProperty("备注") String remark;
    /**
	 * 申请人
	 */
    @Mapping
    private @ApiModelProperty("申请人") String applicant;
    /**
	 * 申请时间
	 */
    @Mapping
    private @ApiModelProperty("申请时间") Long applicantTime;
    /**
	 * 申请网点
	 */
    @Mapping
    private @ApiModelProperty("申请网点") Integer applicantNetwork;
    /**
	 * 审核状态
	 */
    @Mapping
	private @ApiModelProperty("审核状态") Integer checked;
    /**
	 * 确认人
	 */
    @Mapping
    private @ApiModelProperty("确认人") String confirm;
    /**
	 * 确认时间
	 */
    @Mapping
    private @ApiModelProperty("确认时间") Long confirmTime;
    /**
	 * 创建人
	 */
    @Mapping
    private @ApiModelProperty(hidden=true) Integer createUser;
    /**
	 * 创建时间
	 */
    @Mapping
    private @ApiModelProperty(hidden=true) Long createTime;
    /**
	 * 修改人
	 */
    @Mapping
    private @ApiModelProperty(hidden=true) Integer updateUser;
    /**
	 * 修改时间
	 */
    @Mapping
    private @ApiModelProperty(hidden=true) Long updateTime;
}