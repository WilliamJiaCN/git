package com.hivescm.tms.api.dto.es.sitesearch.redundancy;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 短途到货信息
 * 提取该运单关联的短途到货批次信息字段
 * @author ke.huang
 * @date 2017年9月19日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class DepartShortArrivalNodeDTO implements Serializable{
	private static final long serialVersionUID = 3635998657941256661L;
	@ApiModelProperty("短途到货批次")
	private String arrivalBatch;//短途到货单的短途到货批次
	@ApiModelProperty("关联批次")
	private String departBatch;//短途到货单关联的短途发车批次
	@ApiModelProperty("到货网点")
	private String branchName;//短途到货单的到货网点
	@ApiModelProperty("到货件数")
	private Integer unloadAmount;//短途到货单明细该运单的到货件数
	@ApiModelProperty("到达时间")
	private Long arrivalTime;//短途到货单操作到达后的到达时间
	@ApiModelProperty("备注")
	private String remark;//短途到货单的备注
	@ApiModelProperty("经办人")
	private String createUserName;//短途到货单单中的制单人
}
