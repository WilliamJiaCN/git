package com.hivescm.tms.api.dto.es.bill.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.tms.api.enums.biz.bill.BillTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 单据库存列表查询请求DTO
 * @author ke.huang
 * @date 2017年10月11日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class BillStockListReqDTO implements Serializable{
	private static final long serialVersionUID = -8212219705835191308L;
	@Mapping
	@ApiModelProperty("公司ID")
	private Long companyId;
	@Mapping
	@ApiModelProperty("入库开始时间")
	private Long instockStartTime;
	@Mapping
	@ApiModelProperty("入库结束时间")
	private Long instockEndTime;
	@Mapping
	@ApiModelProperty("单据类型")
	private BillTypeEnum billType;

	@ApiModelProperty("包含单号")
	private String billCode;
	@Mapping
	@ApiModelProperty("入库批次号")
	private String batchCode;
	@Mapping
	@ApiModelProperty("每页大小")
	private Integer pageSize;
	@Mapping
	@ApiModelProperty("当前页")
	private Integer currentPage;
	
	
	public boolean isValid(){
		if (null != instockStartTime || null != instockEndTime) {
			return null != instockStartTime && null != instockEndTime;
		}
		return true;
	}

	public void initReqDTO(BillStockListReqDTO billStockReqDTO) {
		billStockReqDTO.setBillCode("");
	}
}
