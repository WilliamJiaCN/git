package com.hivescm.tms.api.dto.es.receipt.request;

import java.io.Serializable;
import java.util.List;

import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.framework.validation.annotation.Required;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 回单图片上传DTO
 * @author ke.huang
 * @date 2018年4月8日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class ReceiptStockPicReqDTO implements Serializable{
	private static final long serialVersionUID = 3203809069912738534L;
	
	@Required @Logger
	@ApiModelProperty(value="回单库存ID",required=true)
	private List<Long> stockId;
	@ApiModelProperty(value="回单图片，分号间隔，最多5张",required=true)
	private String picUrl;
}
