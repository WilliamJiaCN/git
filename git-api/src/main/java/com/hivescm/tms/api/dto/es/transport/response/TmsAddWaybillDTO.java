package com.hivescm.tms.api.dto.es.transport.response;

import com.hivescm.tms.api.dto.es.waybill.component.TmsWaybillEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 单票添加返回信息
 * 
 * @author 鲁婷婷
 * @since 2018/3/30
 */
@Data
@ToString
public class TmsAddWaybillDTO implements Serializable {
	private static final long serialVersionUID = -532151097077810783L;

	/**
	 * 公司Id
	 */
	@ApiModelProperty(value = "公司Id", notes = "前端调用时不传")
	private Long companyId;

	/**
	 * 操作用户ID
	 */
	@ApiModelProperty(value = "操作用户ID", notes = "前端调用时不传")
	private Integer opUserId;

	/**
	 * 操作用户名称
	 */
	@ApiModelProperty(value = "操作用户名称", notes = "前端调用时不传")
	private String opUserName;

	/**
	 * 当前网点 网点ID
	 */
	@ApiModelProperty(value = "网点ID", notes = "前端调用时不传")
	private Integer branchId;
	@ApiModelProperty(value = "运单信息", notes = "运单信息")
	private TmsWaybillEsDTO tmsWaybillEsDTO;
	@ApiModelProperty(value = "到货网点", notes = "运单信息")
     private List<BranchDTO> branchList;
	@ApiModelProperty(value = "运单所在库存网点", notes = "运单信息")
	private	BranchDTO branch;

}
