package com.hivescm.tms.finance.server.interfaces.sign;

import com.hivescm.common.domain.DataResult;
import com.hivescm.tms.api.dto.es.sign.response.SignStatusRespDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 */
@Api(value = "signStatus", description = "签收状态")
public interface SignStatusControllerDoc {

	@ApiOperation(value = "签收状态查看")
	DataResult<SignStatusRespDTO> getSignStatus(@PathVariable("waybillId") Long waybillId);
}
