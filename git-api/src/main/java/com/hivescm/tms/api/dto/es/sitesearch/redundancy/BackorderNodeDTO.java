package com.hivescm.tms.api.dto.es.sitesearch.redundancy;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 回单信息
 * 系统判断该运单是否签回单，如果签回单是则体现该窗口，且显示目前回
 * 单的状态以及回单签收情况
 * @author ke.huang
 * @date 2017年9月20日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class BackorderNodeDTO implements Serializable{
	private static final long serialVersionUID = -8130697514312292654L;

}
