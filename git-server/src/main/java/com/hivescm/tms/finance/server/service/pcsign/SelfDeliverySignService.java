package com.hivescm.tms.finance.server.service.pcsign;

import com.hivescm.tms.api.dto.es.sign.component.TmsSignEsDTO;
import com.hivescm.tms.api.dto.es.sign.request.AppQueryReqDTO;
import com.hivescm.tms.api.dto.es.sign.request.PCSignReq;
import com.hivescm.tms.api.dto.es.sign.request.QuerySignBySignBatchNumberReq;
import com.hivescm.tms.api.dto.es.sign.request.SignInitializeWayBillReqDTO;
import com.hivescm.tms.api.dto.es.sign.response.SignListRespDTO;

/**
 * 自提签收列表接口
 * @author  boqiang.deng
 * @company 蜂网供应链管理（上海）有限公司
 * @since   2017/8/11
*/

public interface SelfDeliverySignService {
	
	/**
	 * 自提未签收和拒签列表接口
	 * @param pcSignReq
	 * @return
	 */
	SignListRespDTO getNoSign(PCSignReq pcSignReq);
	
	/**
	 * 自提签收，已签收，部分签收,拒签列表查询
	 * @param signReqDTO
	 * @return
	 */
	SignListRespDTO getSignedAndBillPartialSignBill(PCSignReq pcSignReq);
	/**
	 * 自提签收查看
	 * @param waybillCode
	 * @return
	 */
	TmsSignEsDTO getSignBill(QuerySignBySignBatchNumberReq req);
	
	/**
	 * 自提签收 签收界面初始化数据
	 * @param waybillCode
	 * @return
	 */
	TmsSignEsDTO addInitialize(SignInitializeWayBillReqDTO req);
	
	/**
	 * 自提签收全部列表页
	 * @param waybillCode
	 * @return
	 */
	SignListRespDTO getAllBill(PCSignReq pcSignReq);
	
	/**
	 * app搜索框
	 * @param pcSignReq
	 * @return
	 */
	SignListRespDTO getAppList(AppQueryReqDTO appQueryReqDTO);

}

