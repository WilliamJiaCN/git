/**
 * 
 */
package com.hivescm.tms.finance.server.controller.pcsign;

import com.hivescm.common.domain.DataResult;
import com.hivescm.framework.common.exception.ExceptionFactory;
import com.hivescm.tms.api.dto.es.sign.request.AppQueryReqDTO;
import com.hivescm.tms.api.dto.es.sign.response.SignListRespDTO;
import com.hivescm.tms.api.enums.biz.waybill.WaybillDistributionTypeEnum;
import com.hivescm.tms.finance.server.controller.BaseController;
import com.hivescm.tms.finance.server.interfaces.pcsign.SignQueryControllerDoc;
import com.hivescm.tms.finance.server.service.pcsign.QuerySignInfoService;
import com.hivescm.tms.finance.server.service.pcsign.SelfDeliverySignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author  boqiang.deng
 * @date    2018年5月8日
 * @company 蜂网供应链管理（上海）有限公司
 * 
 */
@RestController
@RequestMapping("/signquery")
public class SignQueryController extends BaseController implements SignQueryControllerDoc{

	@Autowired
	private SelfDeliverySignService selfDeliverySignService;
	@Autowired
	private QuerySignInfoService querySignInfoService;
	@Override
	@PostMapping("/getapplist")
	public DataResult<?> getAppList(@RequestBody AppQueryReqDTO appQueryReqDTO) {
		try {
			if(appQueryReqDTO.getDistributionType().getType() == WaybillDistributionTypeEnum.GET.getType()){
				//自提
				return DataResult.success(selfDeliverySignService.getAppList(appQueryReqDTO), SignListRespDTO.class);
			}else{
				//送货
				return DataResult.success(querySignInfoService.getDispatcherDetailLike(appQueryReqDTO).getItems());


			}
		} catch (Exception e) {
			logger.error("app搜索框列表失败+pcSignReq"+ appQueryReqDTO.toString());
			throw ExceptionFactory.ex(e);
		}
	}

}
