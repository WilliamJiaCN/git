package com.hivescm.tms.api.dto.es.sitesearch.response;

import com.hivescm.tms.api.dto.es.sitesearch.redundancy.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 综合查询 - 运营信息所有业务节点响应体
 * @author ke.huang
 * @date 2017年9月19日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class SearchBillNodeRespDTO implements Serializable{
	private static final long serialVersionUID = -7017865111998570029L;
	@ApiModelProperty(" 提货信息")
	private List<TakeGoodsNodeDTO> takeGoodsNodeDTOs;
	@ApiModelProperty("短途发车信息")
	private List<DepartShortNodeDTO> departShortNodeDTOs;
	@ApiModelProperty("短途到货信息")
	private List<DepartShortArrivalNodeDTO> departShortArrivalNodeDTOs;
	@ApiModelProperty("发车信息")
	private List<DepartLoadingNodeDTO> departLoadingNodeDTOs;
	@ApiModelProperty("到货信息")
	private List<DepartLoadingArrivalNodeDTO> departLoadingArrivalNodeDTOs;
	@ApiModelProperty("送货信息")
	private List<SendGoodsNodeDTO> sendGoodsNodeDTOs;
	@ApiModelProperty("装货单信息")
	private List<ShippingNodeDTO> shippingNodeDTOs;
	@ApiModelProperty("卸货单信息")
	private List<DischargingNodeDTO> dischargingNodeDTOs;
	@ApiModelProperty("送出中转信息")
	private List<OuterhairNodeDTO> outerhairNodeDTOs;
	@ApiModelProperty("中转信息")
	private List<TransterNodeDTO> transterNodeDTOs;
	@ApiModelProperty("回单信息")
	private List<BackorderNodeDTO> backorderNodeDTOs;
	@ApiModelProperty("调拨信息")
	private List<AllocationNodeDTO> allocationNodeDTOs;
	@ApiModelProperty("签收信息")
	private List<SignNodeDTO> signNodeDTOs;
	@ApiModelProperty("自提改送货信息")
	private List<ChangeSendNodeDTO> changeSendNodeDTOs;
}
