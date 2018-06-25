/**
 * 
 */
package com.hivescm.tms.finance.server.interfaces.pcsign;

import org.springframework.web.bind.annotation.RequestBody;

import com.hivescm.common.domain.DataResult;
import com.hivescm.tms.api.dto.es.sign.request.AppQueryReqDTO;
import com.hivescm.tms.api.dto.es.sign.response.SignListRespDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * @author  boqiang.deng
 * @date    2018年5月8日
 * @company 蜂网供应链管理（上海）有限公司
 * 
 */
@Api(value="signQuery",description="签收列表接口-以后签收查询接口全放这")
public interface SignQueryControllerDoc {
	@ApiOperation(value = "app搜索框接口")
	@ApiImplicitParam(value="请求实体",name = "appQueryReqDTO",required = true,paramType="body",dataType = "AppQueryReqDTO")
	DataResult<?> getAppList(@RequestBody AppQueryReqDTO appQueryReqDTO);
}
