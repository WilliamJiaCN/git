package com.hivescm.tms.api.dto.es.alteration.response;

import com.hivescm.tms.api.enums.bill.alteration.AlterationQueryRespTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 自提改配送获取运单信息
 * @author ke.huang
 * @date 2018年5月17日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class AlterationDeliveryWaybillRespDTO implements Serializable{
	private static final long serialVersionUID = -3912617221531147109L;
	
	@ApiModelProperty("查询结果类型")
	private AlterationQueryRespTypeEnum queryRespType;
	@ApiModelProperty("查询结果")
	private AlterationDeliveryWaybillDTO alterationDeliveryWaybillDTO;
}
