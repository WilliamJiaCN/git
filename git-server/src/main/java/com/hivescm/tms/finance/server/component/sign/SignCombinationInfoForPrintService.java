package com.hivescm.tms.finance.server.component.sign;

import com.hivescm.tms.api.dto.print.waybill.WaybillCombinationInfoForPrintDTO;

/**
 * 
 * @author lifan
 * 签收单组合信息查询-打印（给马勇）
 * 2017年12月29日
 *
 */
public interface SignCombinationInfoForPrintService {
	/**
	 * 根据运单id查询签收单组合信息
	 * @param waybillId
	 * @return
	 */
	public WaybillCombinationInfoForPrintDTO querySignCombinationInformationForPrint(Long waybillId);
}
