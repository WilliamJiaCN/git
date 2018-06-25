package com.hivescm.tms.api.dto.es.sitesearch.redundancy;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 短途发车信息
 * 运单提取短途发车单据的主表信息， 如果有做过多次短途发车或者拆单装车的，则短途信息按照发车时间字段先后排序
 * @author ke.huang
 * @date 2017年9月19日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class DepartShortNodeDTO implements Serializable{
	private static final long serialVersionUID = -6583756926766602369L;
	@ApiModelProperty("短途批次")
	private String departBatch;//短途发车单的短途批次
	@ApiModelProperty("发车网点")
	private String departBranchName;//短途发车单的网点
	@ApiModelProperty("装车件数")
	private Integer loadAmount;//短途发车单中的装车件数
	@ApiModelProperty("发车时间")
	private Long batchDepartTime;//短途发车操作到达后的到达时间
	@ApiModelProperty("到达时间 ")
	private Long batchArrivalTime;//短途发车单的派车时间
	@ApiModelProperty("到达网点 ")
	private String arrivalBranchName;//短途发车的到达网点
	@ApiModelProperty("车牌号码")
	private String vehicleNo;//短途发车单的车牌号码
	@ApiModelProperty("司机姓名")
	private String driverName;//短途发车单的司机姓名
	@ApiModelProperty("手机号码")
	private String driverPhoneNo;//短途发车单的手机号码
	@ApiModelProperty("备注")
	private String remark;//短途发车单的备注
	@ApiModelProperty("经办人")
	private String createUser;//短途发车单中的制单人
	
}
