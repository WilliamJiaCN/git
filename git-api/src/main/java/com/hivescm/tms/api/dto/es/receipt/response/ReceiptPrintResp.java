/**
 * 
 */
package com.hivescm.tms.api.dto.es.receipt.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author  boqiang.deng
 * @date    2018年6月7日
 * @company 蜂网供应链管理（上海）有限公司
 * 
 */
@Data
@ToString
public class ReceiptPrintResp implements Serializable{

	private static final long serialVersionUID = 1L;
	@Mapping @ApiModelProperty("寄出批次")
    private String transmitBatchCode;
	@Mapping @ApiModelProperty("寄出时间")
    private Long transmitTime;
	@Mapping @ApiModelProperty("寄出备注")
    private String transmitRemark;
	@Mapping
	@ApiModelProperty("寄出网点名称")
	private String transmitOrgName;
	@Mapping
	@ApiModelProperty("接收网点名称")
	private String receiveOrgName;
	@Mapping @ApiModelProperty("快递号/车牌号")
    private String expressVehicleNum;
	@Mapping @ApiModelProperty("快递费用")
    private BigDecimal fee;
	@Mapping
	@ApiModelProperty("接收人姓名")
	private String receiptUserName;
	@Logger @Mapping @ApiModelProperty("主键")
	private Long id;

}
