package com.hivescm.tms.api.dto.es.sitesearch.redundancy;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

/**
 * 送出中转信息(外发)
 * 运单操作送出中转环节关联的送出中转信息， 即通过外请车辆把需要中转
 * 的运单运输至下一家合作的承运公司中， 当中记录车辆中所关联的运单信息
 * @author ke.huang
 * @date 2017年9月20日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class OuterhairNodeDTO implements Serializable{
	private static final long serialVersionUID = 6790074842564499652L;

}
