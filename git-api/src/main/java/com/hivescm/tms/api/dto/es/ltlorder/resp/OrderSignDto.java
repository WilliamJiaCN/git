package com.hivescm.tms.api.dto.es.ltlorder.resp;

import java.io.Serializable;
import java.util.List;

import com.hivescm.tms.api.dto.es.receipt.response.ReceiptStockTrackDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderSignDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty("签收状态")
	private Integer signStatus;
	
	@ApiModelProperty("签收状态名称")
	private String signStatusName;
	
	@ApiModelProperty("签收详情")
	private String signDetail;
	
	@ApiModelProperty("签收时间")
	private Long signTime;
	
	@ApiModelProperty("签收图片")
	private String signPictures;
	
	@ApiModelProperty("回单照片，多张照片；间隔")
	private String receiptPicture;
	@ApiModelProperty("轨迹信息")
	private List<ReceiptStockTrackDTO> tracks;

}
