package com.hivescm.tms.api.dto.es.ltlorder.resp;

import java.io.Serializable;
import java.util.List;

import com.hivescm.tms.api.dto.es.ltlorder.LtlOrderEsDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 订单列表应答DTO
 * @author wenxiong.jia
 * @date 2018/4/8
 */
@Data
@ToString
public class LtlOrderListRespDTO implements Serializable{
	private static final long serialVersionUID = 5761738217000544787L;
	@ApiModelProperty("总条数")
	private Integer totalNum;
	@ApiModelProperty("订单列表")
	private List<LtlOrderEsDTO> orders;
}
