package com.hivescm.tms.api.dto.es.receipt.response;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 回单轨迹response信息
 * @author ke.huang
 * @date 2018年4月9日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class ReceiptStockTrackRespDTO implements Serializable{
	private static final long serialVersionUID = 2804646913891951127L;
	@ApiModelProperty("回单照片，多张照片；间隔")
	private String url;
	@ApiModelProperty("轨迹信息")
	private List<ReceiptStockTrackDTO> tracks;
}
