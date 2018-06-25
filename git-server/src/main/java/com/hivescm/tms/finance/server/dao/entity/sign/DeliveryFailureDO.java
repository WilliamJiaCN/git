package com.hivescm.tms.finance.server.dao.entity.sign;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
@Data
@ToString
public class DeliveryFailureDO  implements Serializable {

	private static final long serialVersionUID = 1L;

	@Mapping
	@ApiModelProperty("派送失败id，主键")
	private Long id;
	@Mapping
	@ApiModelProperty("派送失败单号")
	private String failureCode;
	@Mapping
	@ApiModelProperty("公司id")
	private Long companyId;
	@Mapping
	@ApiModelProperty("运单id")
	private Long waybillId;
	@Mapping
	@ApiModelProperty("运单明细id")
	private Long waybillDetailId;
	@Mapping
	@ApiModelProperty("当前网点id")
	private Integer orgId;
	@Mapping
	@ApiModelProperty("运单号")
	private String waybillCode;
	@Mapping
	@ApiModelProperty("派车单id")
	private Long dispatcherId;
	@Mapping
	@ApiModelProperty("派车单明细id")
	private Long dispatcherDetailId;
	@Mapping
	@ApiModelProperty("派车批次")
	private String dispatcherBatchNumber;
	@Mapping
	@ApiModelProperty("运单派送的批次id")
	private Long dispacherCode;
	@Mapping
	@ApiModelProperty("派送类型")
	private Integer deliveryType;
	@Mapping
	@ApiModelProperty("派送失败原因类型")
	private Integer failureReasionType;
	@Mapping
	@ApiModelProperty("派送失败原因")
	private String failureReasion;
	@Mapping
	@ApiModelProperty("发车网点 运单派送的网点名称")
	private String invoicewayName;
	@Mapping
	@ApiModelProperty("司机名称")
	private String carName;
	@Mapping
	@ApiModelProperty("司机手机号码")
	private String carPhone;
	@Mapping
	@ApiModelProperty("派送运单的车辆车牌号码")
	private String carNumber;
	@Mapping
	@ApiModelProperty("创建人")
	private Integer createUser;
	@Mapping
	@ApiModelProperty("创建时间")
	private Long createTime;
	@Mapping
	@ApiModelProperty("修改人")
	private Integer updateUser;
	@Mapping
	@ApiModelProperty("修改时间")
	private Long updateTime;
}
