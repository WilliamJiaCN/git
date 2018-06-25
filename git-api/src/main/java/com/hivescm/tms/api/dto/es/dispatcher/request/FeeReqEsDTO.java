package com.hivescm.tms.api.dto.es.dispatcher.request;

import java.io.Serializable;
import java.math.BigDecimal;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 派车单费用明细
 *
 * @author 李洪春
 * @since 2017/8/18 8:07
 */
@Data
@ToString
public class FeeReqEsDTO implements Serializable, Cloneable {

	private static final long serialVersionUID = 1779764710194959965L;

	@Override
	public FeeReqEsDTO clone() {
		FeeReqEsDTO order = null;
		try {
			order = (FeeReqEsDTO) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return order;
	}

	/**
	 * 主键
	 */
	@Logger
	@Mapping
	@ApiModelProperty("主键")
	private Long id;
	/**
	 * 公司id
	 */
	@Mapping
	@ApiModelProperty("公司id")
	private Long companyId;


	/**
	 * 费用类型ID(数据字典)
	 */
	@Mapping
	@ApiModelProperty("费用类型ID(数据字典)")
	private String feeType;

	@Mapping
	@ApiModelProperty("费用类型ID(数据字典)")
	private String feeTypeName;

	/**
	 * 费用金额
	 */
	@Mapping
	@ApiModelProperty("费用金额")
	private BigDecimal amount;

	/**
	 * 付款方ID
	 */
	@Mapping
	@ApiModelProperty("付款方ID")
	private Integer payerId;

	/**
	 * 收款方ID
	 */
	@Mapping
	@ApiModelProperty("收款方ID")
	private Integer payeeId;

	/**
	 * 费用说明
	 */
	@Mapping
	@ApiModelProperty("费用说明")
	private String remark;

	/**
	 * 付款方名称
	 */
	@Mapping
	@ApiModelProperty("付款方名称")
	private String payerName;

	/**
	 * 收款方名称
	 */
	@Mapping
	@ApiModelProperty("收款方名称")
	private String payeeName;

}
