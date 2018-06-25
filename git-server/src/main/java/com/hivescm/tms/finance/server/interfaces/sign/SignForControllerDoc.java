package com.hivescm.tms.finance.server.interfaces.sign;

import com.hivescm.common.domain.DataResult;
import com.hivescm.tms.api.dto.es.dispatcher.response.DispatcherWaybillDetailsRespDTO;
import com.hivescm.tms.api.dto.es.sign.SignDetailsEsDTO;
import com.hivescm.tms.api.dto.es.sign.component.VerificationCodeDTO;
import com.hivescm.tms.api.dto.es.sign.request.SignForDetailsReqDTO;
import com.hivescm.tms.api.dto.es.sign.request.SignPictureReqDTO;
import com.hivescm.tms.api.dto.es.sign.request.SignQueryReqDTO;
import com.hivescm.tms.api.dto.es.sign.response.SignBillEsRespDTO;
import com.hivescm.tms.api.dto.es.sign.response.SignDetailsRespDTO;
import com.hivescm.tms.api.dto.es.sign.response.SignQueryPageResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(value = "signFor", description = "签收服务")
public interface SignForControllerDoc {



	// 生成签收单
	@ApiOperation(value = "签收新增")
	@ApiImplicitParam(value = "签收新增实体", name = "signForDetailsReqDTO", required = true, paramType = "body", dataType = "SignForDetailsReqDTO")
	DataResult<SignBillEsRespDTO> insertSign(@RequestBody SignForDetailsReqDTO signForDetailsReqDTO);

	// 签收单详情--获取签收单
	@ApiOperation(value = "签收后的详情")
	DataResult<SignDetailsRespDTO> getSignBill(@PathVariable("waybillId") Long waybillId);

	// 运单详情
	@ApiOperation(value = "签收前的详情")
    DataResult<DispatcherWaybillDetailsRespDTO> getWaybillDetail(@PathVariable("waybillId") Long waybillId);

    // 查询验证码
	@ApiOperation(value = "查询验证码")
	@ApiImplicitParam(value = "查询验证码", name = "verificationCodeDTO", required = true, paramType = "body", dataType = "VerificationCodeDTO")
	DataResult<Boolean> checkSignode(@RequestBody VerificationCodeDTO verificationCodeDTO);

	// 签收图片路径跟新
	@ApiOperation(value = "更新签收单图片路径")
	DataResult<?> updateSignPicture(@ApiParam(name = "SignPictureReqDTO", value = "签收图片DTO", required = true)
                                           @RequestBody SignPictureReqDTO signPictureReqDTO);
	

	@ApiOperation(value = "pc端送货签收列表查询")
    DataResult<SignQueryPageResponseDTO> getSignList(@RequestBody SignQueryReqDTO signQueryReqDTO);

	@ApiOperation(value = "送货签收商品详情")
    DataResult<List<SignDetailsEsDTO>> getGoodsDetails(
            @PathVariable("type") Integer type, @PathVariable("signId") Long signId);


	@ApiOperation(value = "手动提交")
	DataResult<Boolean> reCommit(@PathVariable("waybillId") Long waybillId,
								 @ApiParam(name = "commitType", value = "提交类型", required = true)
								 @PathVariable("commitType") Integer commitType);
}