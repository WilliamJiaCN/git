package com.hivescm.tms.api.dto.es.sitesearch.redundancy;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 提货信息 
 * 运单根据做派车单时判断是提货送货的或者提货入库的情况时提取派车单信息
 * @author ke.huang
 * @date 2017年9月19日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class TakeGoodsNodeDTO implements Serializable{
	private static final long serialVersionUID = 3906943876704646562L;
	@ApiModelProperty("提货批次")
	private String takeBatchCode;//派车单批次
	@ApiModelProperty("提货网点")
	private String takeDotName;//派车单的网点
	@ApiModelProperty("提货时间")
	private Long dispatcherTime;//派车单的派车时间
	@ApiModelProperty("装车时间")
	private Long shippingTime;//城配 APP 司机端或者 TMS 手机 APP 端操作的装货确认时间
	@ApiModelProperty("预装件数")
	private Integer packageNum;//派车单明细总件数
	@ApiModelProperty("车牌号码")
	private String vehicleNo;
	@ApiModelProperty("司机姓名")
	private String driverName;
	@ApiModelProperty("手机号码")
	private String phoneNo;
	@ApiModelProperty("提货地址")
	private String invoiceAddress;//运单 ID 对应的发货地址
	@ApiModelProperty("提货联系人")
	private String consigneeName;//派车单中的提货联系人
	@ApiModelProperty("备注")
	private String remark;//派车单的备注
}
