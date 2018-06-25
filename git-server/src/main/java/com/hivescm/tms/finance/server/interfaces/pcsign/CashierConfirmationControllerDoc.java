package com.hivescm.tms.finance.server.interfaces.pcsign;

import com.hivescm.common.domain.DataResult;
import com.hivescm.tms.api.dto.es.finance.CashierConfirmationDTO;
import com.hivescm.tms.api.dto.es.finance.request.CapitalAccountReqDTO;
import com.hivescm.tms.api.dto.es.finance.request.CashierConfirmationReqDTO;
import com.hivescm.tms.api.dto.es.finance.request.WayBillCashierConfirmationReqDTO;
import com.hivescm.tms.api.dto.es.finance.response.CapitalAccountResDTO;
import com.hivescm.tms.api.dto.es.finance.response.CashierConfirmationResDTO;
import com.hivescm.tms.api.dto.es.sign.component.TmsSignEsDTO;
import com.hivescm.tms.api.dto.es.waybill.WaybillEsDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Api(value="cashierConfirmation",description="收银确认")
public interface CashierConfirmationControllerDoc {

	@ApiOperation(value = "收银确认")
	@ApiImplicitParam(value="收银确认实体",name = "cashierConfirmationDTO",required = true,paramType="body",dataType = "CashierConfirmationReqDTO")
	DataResult<Boolean> cashierConfirmation(@RequestBody CashierConfirmationReqDTO cashierConfirmationDTO);

	@ApiOperation(value = "收银确认展示金额接口")
	@ApiImplicitParam(value="收银确认实体",name = "cashierConfirmationDTO",required = true,paramType="body",dataType = "CashierConfirmationReqDTO")
	DataResult<List<CashierConfirmationResDTO>> cashierConfirmationAmount(@RequestBody CashierConfirmationReqDTO cashierConfirmationDTO);

	@ApiOperation(value = "收银确认资金账户接口")
	@ApiImplicitParam(value="资金账户实体",name = "capitalAccountReqDTO",required = true,paramType="body",dataType = "CapitalAccountReqDTO")
	DataResult<List<CapitalAccountResDTO>> capitalAccount(@RequestBody CapitalAccountReqDTO capitalAccountReqDTO);

	@ApiOperation(value = "获取运单接口")
	WaybillEsDTO queryWaybill(@RequestBody CashierConfirmationReqDTO cashierConfirmationDTO);

   @ApiOperation(value = "修改收银状态")
   public Boolean updateSignCashierStatus(@RequestBody WayBillCashierConfirmationReqDTO reqDTO);

}
