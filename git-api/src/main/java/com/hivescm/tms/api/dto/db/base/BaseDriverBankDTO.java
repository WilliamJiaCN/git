package com.hivescm.tms.api.dto.db.base;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author 
@Talbe(name="base_driver_bank")
 */
@Data
@ToString
public class BaseDriverBankDTO implements Serializable {

	/**
	 * ID
	 * 
	 * @Id
	 * @GeneratedValue
	 * @GeneratedValue(strategy = GenerationType.IDENTITY)
	 */
	private Integer id;

	/**
	 * 司机主表ID
	 * 
	 * @NotEmpty
	 */
	@Mapping
	@ApiModelProperty("司机id")
	private Integer driverId;

	/**
	 * 账户名称
	 */
	@Mapping
	@ApiModelProperty("账户名称")
	private String accountName;

	/**
	 * 开户银行
	 */
	@Mapping
	@ApiModelProperty("开户银行")
	private String openBank;

	/**
	 * 银行账号
	 */
	@Mapping
	@ApiModelProperty("银行账号")
	private String bankAccount;

	/**
	 * 默认 0   否1
	 * 
	 * @NotEmpty
	 */
	@Mapping
	@ApiModelProperty("默认 0 是  否 1")
	private Integer iDafault;

	/**
	 * 备注
	 */
	@Mapping
	@ApiModelProperty("备注")
	private String remarks;
	/**
	 * 
	 * @NotEmpty
	 */
	@Mapping
	@ApiModelProperty(hidden = true)
	private Integer createUser;
	/**
	 * 
	 * @NotEmpty
	 */
	@Mapping
	@ApiModelProperty(hidden = true)
	private Long createTime;
	/**
	 * 
	 * @NotEmpty
	 */
	@Mapping
	@ApiModelProperty(hidden = true)
	private Integer updateUser;
	/**
	 * 
	 * @NotEmpty
	 */
	@Mapping
	@ApiModelProperty(hidden = true)
	private Long updateTime;

	@Mapping
	private static final long serialVersionUID = 1L;
	
}