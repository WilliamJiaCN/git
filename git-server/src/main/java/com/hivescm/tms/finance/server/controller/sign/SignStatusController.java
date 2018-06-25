package com.hivescm.tms.finance.server.controller.sign;

import com.hivescm.common.domain.DataResult;
import com.hivescm.common.exception.SystemException;
import com.hivescm.tms.api.dto.es.sign.response.SignStatusRespDTO;
import com.hivescm.tms.finance.server.component.sign.SignStatusService;
import com.hivescm.tms.finance.server.interfaces.sign.SignStatusControllerDoc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 查询签收状态
 */
@RestController
@RequestMapping("/signStatus")
public class SignStatusController implements SignStatusControllerDoc {

	@Autowired
	private SignStatusService signStatusService;


	/**
	 * 查询签收的生命周期状态
	 * @param waybillId
	 * @return
	 */
	@Override
	@PostMapping("/getSignStatus/{waybillId}")
	public DataResult<SignStatusRespDTO> getSignStatus(@PathVariable("waybillId") Long waybillId) {
		DataResult<SignStatusRespDTO> rs = null;
		try {
			SignStatusRespDTO signStatusRespDTO = signStatusService.getSignStatus(waybillId);
			rs = DataResult.success(signStatusRespDTO, SignStatusRespDTO.class);
		} catch (SystemException e) {
			rs = DataResult.faild(e.getErrorCode(), e.getErrorReason());
		} catch (Exception e) {
			rs = DataResult.faild(1200, e.getMessage());
		}
		return rs;
	}

}
