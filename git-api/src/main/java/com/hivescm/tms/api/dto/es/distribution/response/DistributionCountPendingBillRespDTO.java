package com.hivescm.tms.api.dto.es.distribution.response;

import com.hivescm.tms.api.dto.es.waybill.WaybillEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author  boqiang.deng
 * @company 蜂网供应链管理（上海）有限公司
 * @since   2017/8/11
*/
@Data
@ToString
public class DistributionCountPendingBillRespDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	@ApiModelProperty("运单集合")
	private List<WaybillEsDTO> waybillList;

	@ApiModelProperty("线路名称")
	private String lineName;
	
	@ApiModelProperty("收货地址")
	private String receiptAddress;
	
	@ApiModelProperty("总运费")
	private BigDecimal totalFee;
	
	@ApiModelProperty("总单数")
	private Integer totalBill;
	
	@ApiModelProperty("总件数")
	private Integer totalGoodsNum;
	
	@ApiModelProperty("总体积")
	private BigDecimal totalVolume;
	
	@ApiModelProperty("总重量")
	private BigDecimal totalWeight;
	
}

