package com.hivescm.tms.finance.server.controller.sign;

import com.hivescm.common.domain.DataResult;
import com.hivescm.common.exception.SystemException;
import com.hivescm.tms.api.dto.es.dispatcher.response.DispatcherWaybillDetailsRespDTO;
import com.hivescm.tms.api.dto.es.sign.SignDetailsEsDTO;
import com.hivescm.tms.api.dto.es.sign.component.VerificationCodeDTO;
import com.hivescm.tms.api.dto.es.sign.request.RefuseForSignReqDTO;
import com.hivescm.tms.api.dto.es.sign.request.SignForDetailsReqDTO;
import com.hivescm.tms.api.dto.es.sign.request.SignPictureReqDTO;
import com.hivescm.tms.api.dto.es.sign.request.SignQueryReqDTO;
import com.hivescm.tms.api.dto.es.sign.response.SignBillEsRespDTO;
import com.hivescm.tms.api.dto.es.sign.response.SignDetailsRespDTO;
import com.hivescm.tms.api.dto.print.waybill.WaybillCombinationInfoForPrintDTO;
import com.hivescm.tms.constants.ExceptionCodeConstants;
import com.hivescm.tms.finance.server.component.sign.*;
import com.hivescm.tms.finance.server.interfaces.sign.SignForControllerDoc;
import com.hivescm.tms.finance.server.service.lock.SignLockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/signFor")
public class SignForController implements SignForControllerDoc {
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private GiveSignService giveSignService;

	@Autowired
	private SignForService signForService;
	@Autowired
	private AllSignForListService allSignForListService;
	@Autowired
	private AllSignForQueryService allSignForQueryService;
	@Autowired
	private SignCombinationInfoForPrintService signCombinationInfoForPrintService;

	@Autowired
	private SignLockService signLockService;
	/**
	 * 签收
	 *
	 * 重复提交 唯一性验证 非幂等
	 * 如果失败 报错误码
	 *
	 * 不区分  全拒签 部分签 全签
	 *
	 * 方案确定
	 * 第一种，若操作用户操作全部签收提交后，然后提交过程中，再次点击返回，在修改这个部分签收，那这块需要三个系统联动，且从开发角度涉及修改的比较多，就是说白了，工作量大
	 * 第二种方案，由TMS系统的APP自行控制，即提交了全部签收了，就不能反悔了，即使点击返回 并且再次修改部分签收，点击提交的时候也会验证之前做过的操作，这个改动最少的。
	 * 目前按照第二种方案实行，请熟知
	 *
	 * @see RefuseSignController#saveRefuseSign(RefuseForSignReqDTO)
	 *
	 * {@link RefuseSignController#saveRefuseSign(RefuseForSignReqDTO)}
	 * @param signForDetailsReqDTO
	 * @return
	 */
	@Override
	@PostMapping("/add")
//	@Validated(uniqueKey = IdempotencyConstants.BIZ_TMS_SIGN_TYPE_RECOMMIT,uniqueValue = "signForDetailsReqDTO.waybillId")
	public DataResult<SignBillEsRespDTO> insertSign(@RequestBody SignForDetailsReqDTO signForDetailsReqDTO) {

		Boolean lock = signLockService.signRecommitLock(signForDetailsReqDTO.getWaybillId());

		if (!lock) {
			return DataResult.faild(ExceptionCodeConstants.ERROR_ADD_SIGN, "请勿重复提交");
		}

		try {
            logger.debug("保存签收单，参数为：{}", signForDetailsReqDTO);
            SignBillEsRespDTO signBillEsRespDTO = giveSignService.insertSign(signForDetailsReqDTO);
            logger.debug("保存签收单，返回参数为：{}", signBillEsRespDTO);
            return DataResult.success(signBillEsRespDTO, SignBillEsRespDTO.class);
        } catch (SystemException e) {
			e.printStackTrace();
			try {
				signLockService.signRecommitUnLock(signForDetailsReqDTO.getWaybillId());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
            throw new SystemException(e.getErrorCode(), e.getErrorReason());
        }
	}

	@Override
	@PostMapping("/getSignBill/{waybillId}")
	public DataResult<SignDetailsRespDTO> getSignBill(@PathVariable("waybillId") Long waybillId) {
		return signForService.getSignBill(waybillId);
	}

	@Override
	@PostMapping("/getWaybillById/{waybillId}")
	public DataResult<DispatcherWaybillDetailsRespDTO> getWaybillDetail(@PathVariable("waybillId") Long waybillId) {
		return signForService.getWaybillDetail(waybillId);
	}

	@Override
	@PostMapping("/checkSignode")
	public DataResult<Boolean> checkSignode(@RequestBody VerificationCodeDTO verificationCodeDTO) {
		return signForService.checkSignode(verificationCodeDTO);
	}

	/**
	 * 更新签收单签收图片路径
	 */
	@Override
	@PostMapping("/updateSignPicture")
	public DataResult<?> updateSignPicture(@RequestBody SignPictureReqDTO signPictureReqDTO) {
		logger.debug("更新签收图片路径，请求参数:{}", signPictureReqDTO);
		boolean result;
		try {
			result = this.signForService.updateSignPicture(signPictureReqDTO);
		} catch (SystemException e) {
			logger.error("更新签收图片路径失败", e);
			throw e;
		}
		return DataResult.success(result);
	}

	@Override
	@PostMapping("/getSignList")
	public DataResult getSignList(@RequestBody SignQueryReqDTO signQueryReqDTO) {
		try {
			return DataResult.success(allSignForQueryService.getSignPage(signQueryReqDTO));
		} catch (SystemException e) {
			logger.error("更新签收图片路径失败", e);
			return DataResult.faild(e.getErrorCode(), e.getErrorReason());
		}
	}

	@Override
	@GetMapping("/goods/{type}/{signId}")
	public DataResult<List<SignDetailsEsDTO>> getGoodsDetails(@PathVariable("type") Integer type,
                                                              @PathVariable("signId") Long signId) {
		return allSignForListService.getGoodsDetails(type, signId);
	}

	@PostMapping("/signCombinationInfo/{waybillId}")
	public DataResult<WaybillCombinationInfoForPrintDTO> querySignCombinationInformationForPrint(@PathVariable("waybillId")Long waybillId){
		DataResult<WaybillCombinationInfoForPrintDTO> dataResult=new DataResult<WaybillCombinationInfoForPrintDTO>();
		dataResult.setResult(signCombinationInfoForPrintService.querySignCombinationInformationForPrint(waybillId));
		return dataResult;
	}

	@Override
	@PostMapping("/reCommitToOMS/{commitType}/{waybillId}")
	public DataResult<Boolean> reCommit(@PathVariable("waybillId") Long waybillId,
											 @PathVariable("commitType") Integer commitType){

		switch (commitType) {
			case 1:
				return giveSignService.reCommitToOMS(waybillId, commitType);

			default:
			return null;
		}
	}
}
