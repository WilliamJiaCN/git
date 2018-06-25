package com.hivescm.tms.api.dto.es.sign.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.tms.api.dto.es.sign.SignDetailsEsDTO;
import com.hivescm.tms.api.dto.es.sign.SignPackageDTO;
import com.hivescm.tms.api.dto.es.sign.SignPackageDetailsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@ToString
public class SignDetailsRespDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 运单ID
	 */
	private @Mapping @ApiModelProperty("运单ID") Long waybillId;
	/**
	 * 签收单id
	 */
	private @Mapping @ApiModelProperty(" 签收单id") Long id;
	/**
	 * 公司Id
	 */
	private @Mapping @ApiModelProperty("公司Id") Long companyId;
	@Mapping
	@ApiModelProperty("是否装车")
	private Boolean loaded;
	/**
	 * 运单号
	 */

	private @Mapping @ApiModelProperty("运单号,已作废") String waybillCode;

	/**
	 * 运单号
	 */

	private @Mapping @ApiModelProperty("运单号") String code;

	/**
	 * 收货人
	 */
	private @Mapping @ApiModelProperty("收货人") String receiptUser;
	/**
	 * 收货人手机号码
	 */
	private @Mapping @ApiModelProperty("收货人手机号码") String receiptConsignorMobleNo;

	/**
	 * 总运费
	 */
	private @Mapping @ApiModelProperty("总运费") BigDecimal totalFee;
	
	private @Mapping @ApiModelProperty("订单号") String orderCode;
	private @Mapping @ApiModelProperty("付款方式") Integer payWay;
	@Mapping
	@ApiModelProperty("到付运费")
	private BigDecimal toPay;
	@Mapping
	@ApiModelProperty("结算方式")
	private Integer settlementMethod;
	@Mapping
	@ApiModelProperty("签收类型")
	private Integer signType;
	@Mapping
	@ApiModelProperty("签收图片")
	private String signPic;

	/**
	 * 代收货款
	 */
	@Mapping
	@ApiModelProperty("代收货款")
	private BigDecimal collectPayment;

	/**
	 * 应收总金额
	 */
	private @Mapping @ApiModelProperty("应收总金额(到付运费+代收货款)") Double aggregateAmount;
	/**
	 * 包裹信息
	 */

	private @Mapping @ApiModelProperty("包裹信息") List<SignPackageDTO> signPackageDTO;
	private @Mapping @ApiModelProperty("货物信息") List<SignDetailsEsDTO> signDetailsEsDTO;
	/**
	 * 包裹号下的物品详细信息
	 */
	private @Mapping @ApiModelProperty("包裹号下的详细信息") List<SignPackageDetailsDTO> signPackageDetailsDTO;
	@Mapping
	@ApiModelProperty("应收合计")
	private BigDecimal totalReceivable;


	/**
	 * 指定配送时间是上面三个拼接的
	 */
	@Mapping
	@ApiModelProperty("指定配送时间")
	private String appointDispatchTime;


	@Mapping
	@ApiModelProperty("门店名称")
	private String storeName;

	/**
	 * 是否加急
	 */
	@Mapping
	@ApiModelProperty("是否加急")
	private Boolean iemergency;
}