package com.hivescm.tms.finance.server.component.sign;

import com.hivescm.common.domain.DataResult;
import com.hivescm.tms.api.dto.es.dispatcher.response.DispatcherWaybillDetailsRespDTO;
import com.hivescm.tms.api.dto.es.sign.SignEsDTO;
import com.hivescm.tms.api.dto.es.sign.component.TmsSignEsDTO;
import com.hivescm.tms.api.dto.es.sign.component.VerificationCodeDTO;
import com.hivescm.tms.api.dto.es.sign.request.SignPictureReqDTO;
import com.hivescm.tms.api.dto.es.sign.response.SignDetailsRespDTO;

import java.util.List;

public interface SignForService {
	
	/**
	 * 签收单详情
	 * @param waybillId
	 * @return
	 */
	
	DataResult<SignDetailsRespDTO> getSignBill(Long waybillId);

	/**
	 * 运单详情（签收前）
	 * @param waybillId
	 * @return
	 */
    DataResult<DispatcherWaybillDetailsRespDTO> getWaybillDetail(Long waybillId);

    /**
	 * 获取短信验证码
	 * @param dispatcherId
	 * @param waybillId
	 * @return
	 */

	/*DispatcherDetailEsDTO getSMSCodeByDispatcherId(Long dispatcherId, Long waybillId);*/
	DataResult<Boolean> checkSignode(VerificationCodeDTO verificationCodeDTO);
	/**
	 * 获取详情信息
	 * @param signForDetailsReqDTO
	 * @return
	 *//*
	DataResult<SignDetailsRespDTO> getSignDetailBill(SignForDetailsReqDTO signForDetailsReqDTO);*/
	/**
	 * 更新签收单图片路径
	 * @param signForDetailsReqDTO
	 * @return boolean
	 */
	boolean updateSignPicture(SignPictureReqDTO signPictureReqDTO);

	DataResult<List<TmsSignEsDTO>> getSignBillByWayBillId(Long waybillId);
	
	/**
	 * 根据运单id查询签收单
	 * @param waybillId
	 * @return
	 */
	SignEsDTO querySignEsDTOByWaybillId(Long waybillId);
}
