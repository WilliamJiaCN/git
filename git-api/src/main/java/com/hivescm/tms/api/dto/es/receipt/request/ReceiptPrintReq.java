/**
 * 
 */
package com.hivescm.tms.api.dto.es.receipt.request;

import java.io.Serializable;

import com.hivescm.framework.validation.annotation.Required;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author  boqiang.deng
 * @date    2018年6月7日
 * @company 蜂网供应链管理（上海）有限公司
 * 
 */
@Data
@ToString
public class ReceiptPrintReq implements Serializable{

	private static final long serialVersionUID = 1L;
	@ApiModelProperty("批次id")
	@Required
	private Long id;

}
