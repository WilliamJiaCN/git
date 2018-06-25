package com.hivescm.tms.finance.server.service.db.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.hivescm.framework.common.exception.ExceptionFactory;
import com.hivescm.tms.constants.ExceptionCodeConstants;
import com.hivescm.tms.constants.TransactionConstants;
import com.hivescm.tms.finance.server.dao.entity.sign.SignDO;
import com.hivescm.tms.finance.server.dao.entity.sign.SignDetailDO;
import com.hivescm.tms.finance.server.dao.entity.sign.SignFeeDO;
import com.hivescm.tms.finance.server.dao.entity.sign.SignRefuseDO;
import com.hivescm.tms.finance.server.dao.mapper.sign.SignDetailDOMapper;
import com.hivescm.tms.finance.server.dao.mapper.sign.SignFeeMapper;
import com.hivescm.tms.finance.server.dao.mapper.sign.SignMapper;
import com.hivescm.tms.finance.server.dao.mapper.sign.SignRefuseDOMapper;
import com.hivescm.tms.finance.server.service.db.DbOperationService;
import com.mogujie.distributed.transction.ChainedTransaction;
import com.mogujie.distributed.transction.RouteParam;
/**
* @author  boqiang.deng
* @company 蜂网供应链(上海)管理有限公司
* @version 2018年3月26日 下午7:02:22
* 
*/
@Service
public class DbOperationServiceImpl implements DbOperationService{

	@Autowired
	private SignMapper signMapper;
	@Autowired
	private SignDetailDOMapper signDetailDOMapper;
	@Autowired
	private SignFeeMapper signFeeMapper;
	@Autowired
    private SignRefuseDOMapper signRefuseDOMapper;
	@Override
	@ChainedTransaction(mapper= {SignMapper.class,SignDetailDOMapper.class,SignFeeMapper.class,SignRefuseDOMapper.class},timeout=TransactionConstants.TIME_OUT)
	public Boolean saveSignInfo(@RouteParam("SignMapper.companyId") SignDO signDO, @RouteParam("SignDetailDOMapper.companyId") List<SignDetailDO> signDetailDOs, @RouteParam("SignFeeMapper.companyId") SignFeeDO signFeeDO,@RouteParam("SignRefuseDOMapper.companyId") SignRefuseDO record) {
		if(signMapper.insertSelective(signDO)!=1 || signDetailDOMapper.insertBatch(signDO.getCompanyId(), signDetailDOs) != signDetailDOs.size() || signFeeMapper.insertSelective(signFeeDO) != 1 || signRefuseDOMapper.insert(record) != 1){
			throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_ADD_SIGN, "签收保存数据库失败");
		}
		return true;
	}
	@ChainedTransaction(mapper= {SignMapper.class,SignDetailDOMapper.class,SignFeeMapper.class},timeout=TransactionConstants.TIME_OUT)
	public Boolean saveSignInfo(@RouteParam("SignMapper.companyId") SignDO signDO, @RouteParam("SignDetailDOMapper.companyId") List<SignDetailDO> signDetailDOs, @RouteParam("SignFeeMapper.companyId") SignFeeDO signFeeDO) {
		if(signMapper.insertSelective(signDO)!=1 || signDetailDOMapper.insertBatch(signDO.getCompanyId(), signDetailDOs) != signDetailDOs.size() || signFeeMapper.insertSelective(signFeeDO) != 1){
			throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_ADD_SIGN, "签收保存数据库失败");
		}
		return true;
	}

	@Override
	@ChainedTransaction(mapper= {SignMapper.class,SignDetailDOMapper.class,SignFeeMapper.class,SignRefuseDOMapper.class},timeout=TransactionConstants.TIME_OUT)
	public Boolean cancelSignInfo(@RouteParam("SignMapper.companyId") SignDO signDO, @RouteParam("SignDetailDOMapper.companyId") List<SignDetailDO> signDetailDOs, @RouteParam("SignFeeMapper.companyId") SignFeeDO signFeeDO,@RouteParam("SignRefuseDOMapper.companyId") SignRefuseDO record,List<Long> signIds) {
		Boolean flag = false;
		signMapper.deleteBatcheByPrimaryKey(signDO.getCompanyId(), signIds);
		signDetailDOMapper.deleteBatchBySignId(signDO.getCompanyId(), signIds);
		signFeeMapper.deleteBatchBySignId(signDO.getCompanyId(), signIds);
		signRefuseDOMapper.deleteBatch(signDO.getCompanyId(), signIds);
		flag=true;
		return flag;
	}

	@Override
	@ChainedTransaction(mapper= {SignMapper.class,SignDetailDOMapper.class,SignFeeMapper.class},timeout=TransactionConstants.TIME_OUT)
	public Boolean saveBatchDB(@RouteParam("SignMapper.companyId") List<SignDO> signDOs, @RouteParam("SignDetailDOMapper.companyId") List<SignDetailDO> signDetailDOs,@RouteParam("SignFeeMapper.companyId") List<SignFeeDO> signFeeDOs) {
		if(signMapper.insertBatch(signDOs.get(0).getCompanyId(),signDOs)!=signDOs.size() || signDetailDOMapper.insertBatch(signDOs.get(0).getCompanyId(), signDetailDOs) != signDetailDOs.size() || signFeeMapper.insertBatch(signDOs.get(0).getCompanyId(), signFeeDOs) != signFeeDOs.size()){
			throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_ADD_SIGN, "批量签收保存数据库失败");
		}
		return true;
	}
	@Override
	public Boolean saveOutboundBatchDB(@RouteParam("SignMapper.companyId") List<SignDO> signDOs, @RouteParam("SignDetailDOMapper.companyId") List<SignDetailDO> signDetailDOs,@RouteParam("SignRefuseDOMapper.companyId") List<SignRefuseDO> signRefuseDO) {
		Boolean flag = false;
		signMapper.insertBatch(signDOs.get(0).getCompanyId(),signDOs);
		signDetailDOMapper.insertBatch(signDOs.get(0).getCompanyId(), signDetailDOs);
		if(!CollectionUtils.isEmpty(signRefuseDO)){
			signRefuseDOMapper.insertBatch(signDOs.get(0).getCompanyId(),signRefuseDO);
		}
		flag=true;
		return flag;
	}
	@Override
	public Boolean updateBatchSignAndRefuseSignStatusBySignId(@RouteParam("SignMapper.companyId") SignDO signDO, @RouteParam("SignDetailDOMapper.companyId") SignRefuseDO signRefuseDO,
			List<Long> signIds,List<Long> refuseSignIds) {
		if(!CollectionUtils.isEmpty(refuseSignIds)){
			signRefuseDOMapper.updateBatchRefuseSignByPrimaryKey(signRefuseDO.getCompanyId(), signRefuseDO, refuseSignIds);
		}
		signMapper.updateBatchSignByPrimaryKey(signDO.getCompanyId(), signDO, signIds);
		return true;
	}
	@Override
	public Boolean delete(@RouteParam("SignMapper.companyId") SignDO signDO, @RouteParam("SignDetailDOMapper.companyId") SignDetailDO signDetailDO, @RouteParam("SignFeeMapper.companyId") SignFeeDO signFeeDO,@RouteParam("SignRefuseDOMapper.companyId") SignRefuseDO record,List<Long> signIds) {
		signMapper.deleteBatcheByPrimaryKey(signDO.getCompanyId(), signIds);
		signDetailDOMapper.deleteBatchBySignId(signDO.getCompanyId(), signIds);
		signFeeMapper.deleteBatchBySignId(signDO.getCompanyId(), signIds);
		signRefuseDOMapper.deleteBatch(signDO.getCompanyId(), signIds);
		return true;
	}

}
