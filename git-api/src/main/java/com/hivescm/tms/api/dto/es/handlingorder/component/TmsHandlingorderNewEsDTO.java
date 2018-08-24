package com.hivescm.tms.api.dto.es.handlingorder.component;

import com.hivescm.tms.api.dto.es.handlingorder.HandlingorderEsDTO;
import com.hivescm.tms.api.dto.es.handlingorder.HandlingorderFeeEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
@Data
@ToString
public class TmsHandlingorderNewEsDTO implements Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -461566631237735126L;

	/**
	 * 公司Id
	 */
	@ApiModelProperty("公司Id")
	private Long companyId;
	@ApiModelProperty("公司Id")
	private String companyName;
	
	@ApiModelProperty("网点Id")
	private Long branchId;
	@ApiModelProperty("网点名称")
	private String branchName;
	
	/**
	 * 公司Id
	 */
	@ApiModelProperty("公司Id")
	private Integer groupId;

	/**
	 * 操作用户ID
	 */
	@ApiModelProperty("操作用户ID")
	private Integer opUserId;

	/**
	 * 操作用户名称
	 */
	@ApiModelProperty("操作用户名称")
	private String opUserName;

	/**
	 * 操作用户名称
	 */
	@ApiModelProperty("交接单号")
	private String warehouseReceiptNo;

	/**
	 * "装卸单主表信息"
	 */
	@ApiModelProperty("装卸单主表信息")
	private HandlingorderEsDTO handlingorderEsDTO;

	/**
	 * 装卸单明细信息列表
	 */
	@ApiModelProperty("装卸单明细信息列表")
	private List<TmsHandlingorderDetailEsDTO> handlingorderDetailEsDTOList;

	@ApiModelProperty("装卸单費用列表")
	private List<HandlingorderFeeEsDTO> handlingorderFeeEsDTOList;
 
	public TmsHandlingorderNewEsDTO() {
	}

	public TmsHandlingorderNewEsDTO(HandlingorderEsDTO handlingorderEsDTO) {
		this.handlingorderEsDTO = handlingorderEsDTO;
	}

	/**
	 * 新增运单时初始化方法
	 */
	public void initOnInsert() {
		Long time = System.currentTimeMillis();
		handlingorderEsDTO.setCompanyId(companyId);
		handlingorderEsDTO.setCreateTime(time);
		handlingorderEsDTO.setCreateUserId(opUserId);

//		if (CollectionUtils.isNotEmpty(handlingorderDetailEsDTOList)) {
//			handlingorderDetailEsDTOList.forEach(dispatcherDetailDTO -> {
//				// dispatcherDetailDTO.setStatus(DispatcherDetailStatusEnum.ACCEPTED.getType());//
//				// 派车单明细初始状态
//				dispatcherDetailDTO.setCompanyId(companyId);
//				// dispatcherDetailDTO.setCreateTime(time);
//				// dispatcherDetailDTO.setCreateUser(opUserId);
//				// dispatcherDetailDTO.getDispatcherGoodsEsDTOList().forEach(dispatcherGoods
//				// -> {
//				// dispatcherGoods.setCompanyId(companyId);
//				// dispatcherGoods.setCreateUser(opUserId);
//				// dispatcherGoods.setCreateTime(time);
//				// });
//			});
//		}
	}
}
