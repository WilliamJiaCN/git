package com.hivescm.tms.api.dto.es.line.resp;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
* @author  boqiang.deng
* @company 蜂网供应链(上海)管理有限公司
* @version 2017年12月14日 下午7:11:45
* 
*/
@Data
@ToString
public class CheckCityServiceEnable implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private boolean flag;
	
	private String seviceName;

}
