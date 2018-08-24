package com.hivescm.tms.api.dto.es.dispatcher.component;

import com.hivescm.framework.validation.annotation.Required;
import com.hivescm.tms.api.dto.es.dispatcher.DispatcherDetailEsDTO;
import com.hivescm.tms.api.dto.es.dispatcher.DispatcherEsDTO;
import com.hivescm.tms.api.dto.es.dispatcher.DispatcherFeeEsDTO;
import com.hivescm.tms.api.enums.biz.dispatcher.DispatcherDetailStatusEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.apache.commons.collections.CollectionUtils;

import java.io.Serializable;
import java.util.List;

/**
 * 派车单信息
 *
 * @author 李洪春
 * @since 2017/8/9 15:56
 */
@Data
@ToString
public class TmsDispatcherEsDTO implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -461566631237735126L;

	/**
	 * 公司Id
	 */
	@Required
	@ApiModelProperty("公司Id")
	private Long companyId;
	
	
	@ApiModelProperty("网点Id")
	private Long orgId;
	
	@ApiModelProperty("集团Id")
	private Integer groupId;
	
	@ApiModelProperty("网点Id")
	private String orgName;
	

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
	 * 是否使用城配 Deprecated 过期字段，需要删除
	 */
	@ApiModelProperty("是否使用城配")
	private Boolean needRelease;

	/**
	 * 派车单基本信息
	 */
	@Required
	@ApiModelProperty("派车单基本信息")
	private DispatcherEsDTO dispatcher;

	/**
	 * 派车单费用列表
	 */
	
	@ApiModelProperty("派车单费用列表")
	private List<DispatcherFeeEsDTO> dispatcherFeeList;

	/**
	 * 派车单运单信息列表
	 */
	@Required
	@ApiModelProperty("派车单运单信息列表")
	private List<DispatcherDetailEsDTO> dispatcherDetailEsDTOList;

	public TmsDispatcherEsDTO() {
	}

	public TmsDispatcherEsDTO(DispatcherEsDTO dispatcher) {
		this.dispatcher = dispatcher;
	}

	/**
	 * 新增运单时初始化方法
	 */
	public void initOnInsert() {
		Long time = System.currentTimeMillis();
		dispatcher.setCompanyId(companyId);
		dispatcher.setCreateTime(time);
		dispatcher.setCreateUser(opUserId);
		dispatcher.setCreateUserName(opUserName);

		if (CollectionUtils.isNotEmpty(dispatcherFeeList)) {
			dispatcherFeeList.forEach(dispatcherFee -> {
				dispatcherFee.setCompanyId(companyId);
				dispatcherFee.setCreateTime(time);
				dispatcherFee.setCreateUser(opUserId);
			});
		}
		if (CollectionUtils.isNotEmpty(dispatcherDetailEsDTOList)) {
			dispatcherDetailEsDTOList.forEach(dispatcherDetailDTO -> {
				dispatcherDetailDTO.setStatus(DispatcherDetailStatusEnum.UN_COMPLETE.getType());// 派车单明细初始状态
				dispatcherDetailDTO.setCompanyId(companyId);
				dispatcherDetailDTO.setCreateTime(time);
				dispatcherDetailDTO.setCreateUser(opUserId);
				dispatcherDetailDTO.setCreateUserName(opUserName);
				if (CollectionUtils.isNotEmpty(dispatcherDetailDTO.getDispatcherGoodsEsDTOList())) {
				 dispatcherDetailDTO.getDispatcherGoodsEsDTOList().forEach(dispatcherGoods
				 -> {
				 dispatcherGoods.setCompanyId(companyId);
				 dispatcherGoods.setCreateUser(opUserId);
				 dispatcherGoods.setCreateTime(time);
				 });
				}
			});
			
		}
	}

	/**
	 * 修改派车单时初始化
	 */
	public void initOnUpdate() {
		Long time = System.currentTimeMillis();
		dispatcher.setCompanyId(companyId);
		dispatcher.setUpdateTime(time);
		dispatcher.setUpdateUser(opUserId);
		dispatcher.setUpdateUserName(opUserName);

		if (CollectionUtils.isNotEmpty(dispatcherFeeList)) {
			dispatcherFeeList.forEach(dispatcherFee -> {
				dispatcherFee.setCompanyId(companyId);
				dispatcherFee.setUpdateTime(time);
				dispatcherFee.setCreateTime(time);
				dispatcherFee.setCreateUser(opUserId);
				dispatcherFee.setUpdateUser(opUserId);
				
			});
		}
		if (CollectionUtils.isNotEmpty(dispatcherDetailEsDTOList)) {
			dispatcherDetailEsDTOList.forEach(dispatcherDetailDTO -> {
				dispatcherDetailDTO.setCompanyId(companyId);
				dispatcherDetailDTO.setUpdateTime(time);
				dispatcherDetailDTO.setUpdateUser(opUserId);
				dispatcherDetailDTO.setCreateTime(time);
				dispatcherDetailDTO.setCreateUser(opUserId);
				dispatcherDetailDTO.setUpdateUserName(opUserName);
				if (CollectionUtils.isNotEmpty(dispatcherDetailDTO.getDispatcherGoodsEsDTOList())) {
				 dispatcherDetailDTO.getDispatcherGoodsEsDTOList().forEach(dispatcherGoods -> {
				 dispatcherGoods.setCompanyId(companyId);
				 dispatcherGoods.setUpdateTime(time);
				 dispatcherGoods.setUpdateUser(opUserId);
				 });
				}
			});
		}
	}

}
