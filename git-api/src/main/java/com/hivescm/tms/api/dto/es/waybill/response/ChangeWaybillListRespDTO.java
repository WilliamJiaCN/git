package com.hivescm.tms.api.dto.es.waybill.response;

import com.hivescm.tms.api.dto.es.waybill.ChangeWaybillEsDTO;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 运单请求体
 * 
 * @author ke.huang
 * @date 2017年7月18日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class ChangeWaybillListRespDTO implements Serializable {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<ChangeWaybillEsDTO> waybillEsDTOList;
	
	private Integer count;
	
}
