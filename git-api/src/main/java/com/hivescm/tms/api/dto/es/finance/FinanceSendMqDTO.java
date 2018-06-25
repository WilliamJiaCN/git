package com.hivescm.tms.api.dto.es.finance;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FinanceSendMqDTO {
	
	/**
	 *操作类型
	 */
	@ApiModelProperty(value = "操作类型  0 新增 1 修改 2 删除")
	private Integer operateType;
	
	/**
	 *业务类型
	 */
	@ApiModelProperty(value = "业务类型  1 运单 2派车单 3发车配载单 4外发中转单 5装货单 6 卸货单 7 到货确认单 20 签收 21自提改配送 22 派车单费项更改 23 到货确认单费项更改")
	private Integer businessType;
	
	/**
	 * 对应ID
	 */
	@ApiModelProperty(value = "对应的ID")
	private List<Long> ids;

}
