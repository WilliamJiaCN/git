package com.hivescm.tms.api.dto.es.handlingorder.component;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.hivescm.tms.api.dto.es.handlingorder.HandlingorderDetailEsDTO;
import com.hivescm.tms.api.dto.es.handlingorder.HandlingorderEsDTO;
import com.hivescm.tms.api.dto.es.handlingorder.HandlingorderFeeEsDTO;
import com.hivescm.tms.api.dto.es.handlingorder.HandlingorderGoodsEsDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 派车单信息
 *
 * @author 李洪春
 * @since 2017/8/9 15:56
 */
@Data
@ToString
public class TmsHandlingorderEsDTO implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -461566631237735126L;

	/**
	 * 公司Id
	 */
	@ApiModelProperty("公司Id")
	private Long companyId;

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
	private List<HandlingorderDetailEsDTO> handlingorderDetailEsDTOList;


	public TmsHandlingorderEsDTO() {
	}

	public TmsHandlingorderEsDTO(HandlingorderEsDTO handlingorderEsDTO) {
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

		if (CollectionUtils.isNotEmpty(handlingorderDetailEsDTOList)) {
			handlingorderDetailEsDTOList.forEach(dispatcherDetailDTO -> {
				// dispatcherDetailDTO.setStatus(DispatcherDetailStatusEnum.ACCEPTED.getType());//
				// 派车单明细初始状态
				dispatcherDetailDTO.setCompanyId(companyId);
				// dispatcherDetailDTO.setCreateTime(time);
				// dispatcherDetailDTO.setCreateUser(opUserId);
				// dispatcherDetailDTO.getDispatcherGoodsEsDTOList().forEach(dispatcherGoods
				// -> {
				// dispatcherGoods.setCompanyId(companyId);
				// dispatcherGoods.setCreateUser(opUserId);
				// dispatcherGoods.setCreateTime(time);
				// });
			});
		}
	}

}
