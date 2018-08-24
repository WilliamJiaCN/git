package com.hivescm.tms.api.dto.es.waybill;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
@Data
@ToString
public class WayBillSmsLogEsDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@Mapping
	@ApiModelProperty("ID")
	private Long id;

	/**
	 * 公司id
	 */
	@Mapping
	@ApiModelProperty("公司id")
	private Integer companyId;
	/**
	 * 公司id
	 */
	@Mapping
	@ApiModelProperty("公司名称")
	private String companyName;

	/**
	 * 运单id
	 */
	@Mapping
	@ApiModelProperty("运单id")
	private Long waybillId;

	/**
	 * 业务节点
	 */
	@Mapping
	@ApiModelProperty("业务节点")
	private Integer bussNode;
	/**
	 * 业务节点
	 */
	@Mapping
	@ApiModelProperty("名称")
	private String bussNodeName;

	/**
	 * 短信内容
	 */
	@Mapping
	@ApiModelProperty("短信内容")
	private String content;

	/**
	 * 状态
	 */
	@Mapping
	@ApiModelProperty("状态")
	private Integer status;

	/**
	 * 手机号码
	 */
	@Mapping
	@ApiModelProperty("手机号码")
	private String phoneNo;

	/**
	 * 接收人
	 */
	@Mapping
	@ApiModelProperty("接收人")
	private String user;

	/**
	 * 发送网点Id
	 */
	@Mapping
	@ApiModelProperty("发送网点Id")
	private Integer sendOrgId;
	/**
	 * 发送网点名称
	 */
	@Mapping
	@ApiModelProperty("发送网点名称")
	private String sendOrgName;

	/**
	 * 发送时间
	 */
	@Mapping
	@ApiModelProperty("发送时间")
	private Long sendTime;

	/**
	 * 运单号
	 */
	@Mapping
	@ApiModelProperty("运单号")
	private String waybillCode;

	/**
	 * 发送条数
	 */
	@Mapping
	@ApiModelProperty("发送条数")
	private Integer amount;

	/**
	 * 短信费
	 */
	@Mapping
	@ApiModelProperty("短信费")
	private BigDecimal fee;

	/**
	 * 发送人编码
	 */
	@Mapping
	@ApiModelProperty("发送人编码")
	private Integer sendUserId;

	/**
	 * 发送人名称
	 */
	@Mapping
	@ApiModelProperty("发送人名称")
	private String sendUserName;

	/**
	 * 创建人
	 */
	@Mapping
	@ApiModelProperty("创建人")
	private Integer createUser;

	/**
	 * 创建人名称
	 */
	@Mapping
	@ApiModelProperty("创建人名称")
	private String createUserName;

	/**
	 * 创建时间
	 */
	@Mapping
	@ApiModelProperty("创建时间")
	private Long createTime;

	/**
	 * 修改人
	 */
	@Mapping
	@ApiModelProperty("修改人")
	private Integer updateUser;
	/**
	 * 修改人名称
	 */
	@Mapping
	@ApiModelProperty("修改人名称")
	private String updateUserName;

	/**
	 * 修改时间
	 */
	@Mapping
	@ApiModelProperty("修改时间")
	private Long updateTime;

	/**
	 * 发送类型
	 */
	@Mapping
	@ApiModelProperty("发送类型")
	private Integer sendType;
	
	/**
	 * 发送类型名称
	 */
	@Mapping
	@ApiModelProperty("发送类型名称")
	private String sendTypeName;

	/**
	 * 发送类型名称
	 */
	@Mapping
	@ApiModelProperty("发送对象")
	private Integer sendTarget;

}
