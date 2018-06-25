package com.hivescm.tms.finance.server.service.finance.impl;

import com.google.common.collect.Lists;
import com.hivescm.common.domain.DataResult;
import com.hivescm.common.utils.Assert;
import com.hivescm.framework.common.exception.ExceptionFactory;
import com.hivescm.framework.common.exception.FrameworkException;
import com.hivescm.framework.entitymapping.utils.EntityUtils;
import com.hivescm.tms.api.dto.es.finance.*;
import com.hivescm.tms.api.dto.es.finance.request.FinanceManageCashTransferAddReqDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinanceManageCashTransferCodeReqDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinanceManageCashTransferListReqDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinanceManageCashTransferReqDTO;
import com.hivescm.tms.api.dto.es.finance.request.*;
import com.hivescm.tms.api.dto.es.finance.response.*;
import com.hivescm.tms.api.enums.finance.*;
import com.hivescm.tms.constants.ExceptionCodeConstants;
import com.hivescm.tms.constants.GeneratedIdConstants;
import com.hivescm.tms.constants.TransactionConstants;
import com.hivescm.tms.finance.server.component.finance.FinanceManageGoodsRecycleComponentService;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageCashSerialDO;
import com.hivescm.tms.constants.TmsCodeRuleBizCode;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageCashBatchDO;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageCashTransferDO;
import com.hivescm.tms.finance.server.dao.mapper.finance.FinanceManageCashSerialMapper;
import com.hivescm.tms.finance.server.dao.mapper.finance.FinanceManageCashTransferMapper;
import com.hivescm.tms.finance.server.service.finance.*;
import com.hivescm.tms.finance.server.service.finance.DbFinanceManageCashTransferService;
import com.hivescm.tms.finance.server.service.finance.EsFinanceManageCashSerialService;
import com.hivescm.tms.finance.server.service.finance.EsFinanceManageCashTransferService;
import com.hivescm.tms.finance.server.service.finance.FinanceManageCashTransferService;
import com.hivescm.tms.intranet.gateway.api.dto.IdCode.IdCodeReqNewDTO;
import com.hivescm.tms.intranet.gateway.api.dto.boss.BizUnitWithFuncDetailVo;
import com.hivescm.tms.intranet.gateway.api.dto.boss.LogisticsOrgFuncInfoBean;
import com.hivescm.tms.intranet.gateway.api.dto.boss.QueryByIdReqParam;
import com.hivescm.tms.intranet.gateway.api.feign.IGeneratedIdService;
import com.hivescm.tms.intranet.gateway.api.feign.boss.BossBizUnitApi;
import com.mogujie.distributed.transction.ChainedTransaction;
import com.mogujie.distributed.transction.RouteParam;
import com.hivescm.tms.intranet.gateway.api.feign.IdCodeService;

import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * @Description:
 * @Author: LiXuan
 * @Date: Created in 2018/5/10 15:11
 * @company 蜂网供应链管理（上海）有限公司
 */
@Service
@Slf4j
public class FinanceManageCashTransferServiceImpl implements FinanceManageCashTransferService {
    private static String OUT = "out";
    private static String IN = "in";
    @Autowired
    private EsFinanceManageCashTransferService esFinanceManageCashTransferService;

    @Autowired
    private DbFinanceManageCashTransferService dbFinanceManageCashTransferService;
    @Autowired
    private DbFinanceManageCashSerialService dbFinanceManageCashSerialService;
    @Autowired
    private EsFinanceManageCashSerialService esFinanceManageCashSerialService;
    @Autowired
    private FinanceManageCashTransferMapper financeManageCashTransferMapper;
    @Autowired
    private FinanceManageCashSerialMapper financeManageCashSerialMapper;
    @Autowired
    private FinanceManageGoodsRecycleComponentService financeManageGoodsRecycleComponentService;


    @Autowired
    private IGeneratedIdService generatedIdService;

    @Autowired
    private IdCodeService codeService;

    @Autowired
    private BossBizUnitApi bossBizUnitApi;

    /**
     * 新增现金转账
     *
     * @param financeManageCashTransferInsertReqDTO
     * @return
     */
    @Override
    public Boolean insert(FinanceManageCashTransferInsertReqDTO financeManageCashTransferInsertReqDTO) {
        boolean es = true;
        boolean db = true;
        boolean esupdate = true;
        try {
        	if(financeManageCashTransferInsertReqDTO.getCompanyId()==null) {
        		throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_TRANSFER_INSERT, "新增转账失败参数错误，companyId为null");
        	}
        	financeManageCashTransferInsertReqDTO.initOnCreate();
            FinanceManageCashTransferEsDTO financeManageCashTransferEsDTO = financeManageCashTransferInsertReqDTO.getFinanceManageCashTransferEsDTO();
            financeManageCashTransferEsDTO.setCompanyId(financeManageCashTransferInsertReqDTO.getCompanyId());
            Long id = generatedIdService.getIncrUniqueId(GeneratedIdConstants.TMS_FINANCE_MANAGER_CASH_TRANSFER);
            financeManageCashTransferEsDTO.setId(id);
            //编码规则生成转账单号
            IdCodeReqNewDTO codeReqNewDTO = new IdCodeReqNewDTO();
            codeReqNewDTO.setGroupId(financeManageCashTransferInsertReqDTO.getGroupId());
            codeReqNewDTO.setBizCode(TmsCodeRuleBizCode.TMS_NEW_TA);
            String code = codeService.generatedNew(codeReqNewDTO).getResult();
            financeManageCashTransferEsDTO.setTransferOrdersource(code);
            //保存更新转账单号、状态为未提交
            FinanceManageCashTransferDO financeManageCashTransferDO = initFinanceManageCashTransferEsDTO(financeManageCashTransferEsDTO);
            List<FinanceManageCashSerialEsDTO> cashDetails = financeManageCashTransferInsertReqDTO.getCashDetails();
            FinanceManageCashBatchDO cashUpdate = new FinanceManageCashBatchDO();
        	cashUpdate.setCompanyId(financeManageCashTransferInsertReqDTO.getCompanyId());
        	cashUpdate.setSubmitBillCode(code);
        	cashUpdate.setSubmitBillId(id);
        	cashUpdate.setSubmitBillState(FinanceTransferStatusEnum.UNTRANSFER.getType());
        	List<Long> ids = new ArrayList<>();
        	for (FinanceManageCashSerialEsDTO cash : cashDetails) {
        		cash.setSubmitBillCode(code);
        		cash.setSubmitBillState(FinanceTransferStatusEnum.UNTRANSFER.getType());
        		cash.setSubmitBillStateName(FinanceTransferStatusEnum.UNTRANSFER.getName());
        		cash.setSubmitBillId(id);
        		ids.add(cash.getId());
        	}
        	cashUpdate.setIds(ids);
        	db = dbFinanceManageCashTransferService.insert(financeManageCashTransferDO, cashUpdate);
            es = esFinanceManageCashTransferService.insert(financeManageCashTransferEsDTO);
            if(cashDetails!=null&&cashDetails.size()>0){
                esupdate = esFinanceManageCashSerialService.updateBatch(cashDetails);
            }
        } catch (Exception e) {
            log.error("新增转账失败", e);
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_TRANSFER_INSERT, "新增转账失败",e);
        }
        return true;
    }

    /**
     * 更新现金转账
     *
     * @param financeManageCashTransferEsDTO
     * @return
     */
    @Override
    public Boolean update(FinanceManageCashTransferEsDTO financeManageCashTransferEsDTO) {
        boolean es = false;
        boolean db = false;
        try {
            FinanceManageCashTransferDO financeManageCashTransferDO = initUpdateFinanceManageCashTransferEsDTO(financeManageCashTransferEsDTO);
            es = esFinanceManageCashTransferService.update(financeManageCashTransferEsDTO);
            db = dbFinanceManageCashTransferService.update(financeManageCashTransferDO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return db;
    }

    private FinanceManageCashTransferDO initFinanceManageCashTransferEsDTO(FinanceManageCashTransferEsDTO financeManageCashTransferEsDTO) {
        FinanceManageCashTransferDO financeManageCashTransferDO = new FinanceManageCashTransferDO();
        EntityUtils.copyProperties(financeManageCashTransferEsDTO, financeManageCashTransferDO);
        return financeManageCashTransferDO;
    }

    private FinanceManageCashTransferDO initUpdateFinanceManageCashTransferEsDTO(FinanceManageCashTransferEsDTO financeManageCashTransferEsDTO) {
        FinanceManageCashTransferDO financeManageCashTransferDO = new FinanceManageCashTransferDO();
        EntityUtils.copyProperties(financeManageCashTransferEsDTO, financeManageCashTransferDO);
        return financeManageCashTransferDO;
    }

    /**
     * 查询现金转账列表
     *
     * @param financeManageCashTransferListReqDTO
     * @return
     */
    @Override
    public FinanceManageCashTransferListRespDTO getEsList(FinanceManageCashTransferListReqDTO financeManageCashTransferListReqDTO) {
        FinanceManageCashTransferListRespDTO resp = new FinanceManageCashTransferListRespDTO();
        List<FinanceManageCashTransferEsDTO> esDTOList = esFinanceManageCashTransferService.getEsList(financeManageCashTransferListReqDTO);
        Integer esListCount = esFinanceManageCashTransferService.getEsListCount(financeManageCashTransferListReqDTO);
        resp.setList(esDTOList);
        resp.setTotalNum(esListCount);
        return resp;
    }

    /**
     * 查询转账新增列表
     * @param financeManageCashTransferAddReqDTO
     * @return
     */
    @Override
    public List<FinanceManageCashSerialEsDTO> getEsForTransferAddList(FinanceManageCashTransferAddReqDTO financeManageCashTransferAddReqDTO) {
        Assert.isNotNull(financeManageCashTransferAddReqDTO.getTransferAccount(), "转账账号不可为空!");
        return esFinanceManageCashSerialService.getEsForTransferAddList(financeManageCashTransferAddReqDTO);
    }

    /**
     * 查询来源单号
     *
     * @param financeManageCashTransferCodeReqDTO
     * @return
     */
    @Override
    public List<FinanceManageCashSerialEsDTO> getCodeByTransfer(FinanceManageCashTransferCodeReqDTO financeManageCashTransferCodeReqDTO) {
        Assert.isNotNull(financeManageCashTransferCodeReqDTO.getTransferAccount(), "转账账号不可为空!");
        List<FinanceManageCashSerialEsDTO> codeByTransfer = esFinanceManageCashSerialService.getCodeByTransfer(financeManageCashTransferCodeReqDTO);
        if(codeByTransfer!=null){
            codeByTransfer.forEach(detail->{
                if(null==detail.getCodTypeName()){
                    detail.setCodTypeName("—");
                }
                if(null==detail.getFeeTypeName()){
                    detail.setFeeTypeName("—");
                }
                if(null==detail.getReceiptAmount()){
                    detail.setReceiptAmount(new BigDecimal(0));
                }
                if(null==detail.getPayAmount()){
                    detail.setPayAmount(new BigDecimal(0));
                }
            });
        }
        return codeByTransfer;
    }

    /**
     * 根据id快速添加
     *
     * @param id
     * @return
     */
    @Override
    public List<FinanceManageCashSerialEsDTO> getEsByCodeForTransfer(Long id) {
        return esFinanceManageCashSerialService.getEsByCodeForTransfer(id);
    }

    /**
     * @param
     * @author sql
     * @Date 2018\5\16 0016
     * @Describe 转账提交
     */
    @Override
    public boolean commit(FinanceManageCashTransferReqDTO transferAccountsReqDTO) {
        // 1.校验入参
        checkField(transferAccountsReqDTO);
        Long companyId = transferAccountsReqDTO.getCompanyId();
        Long submitBillId = transferAccountsReqDTO.getSubmitBillId();
        // 2.更新转账列表DB 状态为已提交,更新转账时间,更新现金流水DB,往现金流水插入支出数据
        FinanceManageCashSerialDO cashSerialDO = new FinanceManageCashSerialDO();
        FinanceManageCashSerialDO outFinanceManageCashSerialDO = incomeSpendingRecord(transferAccountsReqDTO, OUT);
        boolean commitUpdateDBFlag = commitUpdateDB(outFinanceManageCashSerialDO,transferAccountsReqDTO,submitBillId, companyId, cashSerialDO);
        // 3. 如果DB更新完毕,去更新转账列表和现金流水ES
        if (commitUpdateDBFlag) {
            commitupdateES(outFinanceManageCashSerialDO,transferAccountsReqDTO,submitBillId, companyId);
        }

        return commitUpdateDBFlag && commitUpdateDBFlag;
    }

    /**
     * @param
     * @author sql
     * @Date 2018\5\17 0017
     * @Describe 取消提交
     */
    @Override
    public boolean cancelCommit(FinanceManageCashTransferReqDTO transferAccountsReqDTO) {
        // 1.校验入参
        checkField(transferAccountsReqDTO);
        Assert.isNotNull(transferAccountsReqDTO.getSubmitBillId(),"转账单ID不能为空!");
        Long submitBillId = transferAccountsReqDTO.getSubmitBillId();

        //2. 更新DB
        //转账状态为：未提交，清空提交时间，提交人；添加更新取消提交人，取消提交时间；
        FinanceManageCashTransferDO financeManageCashTransferDO = new FinanceManageCashTransferDO();
        financeManageCashTransferDO.setId(submitBillId);
        financeManageCashTransferDO.setCompanyId(transferAccountsReqDTO.getCompanyId());
        financeManageCashTransferDO.setTransferStatus(FinanceTransferStatusEnum.UNTRANSFER.getType());
        financeManageCashTransferDO.setCancelSubmitTime(new Date().getTime());
        financeManageCashTransferDO.setCancelSubmitUserId(transferAccountsReqDTO.getUserId());

        //更新转账单明细对应现金流水的字段：转账状态：未提交；
        FinanceManageCashSerialDO financeManageCashSerialDO = new FinanceManageCashSerialDO();
        financeManageCashSerialDO.setSubmitBillId(submitBillId);
        financeManageCashSerialDO.setCompanyId(transferAccountsReqDTO.getCompanyId());
        financeManageCashSerialDO.setSubmitBillState(FinanceTransferStatusEnum.UNTRANSFER.getType());
        // 更新DB
        FinanceManageCashSerialDO outFinanceManageCashSerialDO = incomeSpendingRecord(transferAccountsReqDTO, OUT);
        outFinanceManageCashSerialDO.setPayAmount(new BigDecimal(0).subtract(transferAccountsReqDTO.getTransferAmount()));
        boolean cancelCommitUpdateDB = cancelCommitUpdateDB(outFinanceManageCashSerialDO,financeManageCashTransferDO, financeManageCashSerialDO);
        boolean cancelCommitUpdateES = false;
        if (cancelCommitUpdateDB) {
            //更新ES
            cancelCommitUpdateES = cancelCommitUpdateES(outFinanceManageCashSerialDO,transferAccountsReqDTO,financeManageCashTransferDO, financeManageCashSerialDO);
        }


        return cancelCommitUpdateDB && cancelCommitUpdateES;
    }

    /**
     * @param
     * @author sql
     * @Date 2018\5\17 0017
     * @Describe 进账方法
     */
    @Override
    public boolean intoAccount(FinanceManageCashTransferReqDTO transferAccountsReqDTO) {
        checkField(transferAccountsReqDTO);
        Assert.isNotNull(transferAccountsReqDTO.getInCurentOrgId(), "装账单进账网点ID不可为空!");
        Assert.isNotNull(transferAccountsReqDTO.getOutCurentOrgId(), "转账单出账网点ID不可为空!");
        Long submitBillId = transferAccountsReqDTO.getSubmitBillId();
        // 根据转账状态判断,是否已经进账
        FinanceManageCashTransferEsDTO cashTransferEsDTO = esFinanceManageCashTransferService.queryListBySubmitBillId(submitBillId);
        if(cashTransferEsDTO==null){
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_MANAGE_CASH_INSERT, "进账接口:根据转账单ID"+submitBillId+"没有查询到转账单");
        }else if(FinanceTransferStatusEnum.RECEIPTED.getType()==cashTransferEsDTO.getTransferStatus()){
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_MANAGE_CASH_INSERT, "进账接口:该转账单"+submitBillId+"已经进账，请不要重复进账!");
        }

        // 1.生成收入记录
        FinanceManageCashSerialDO inFinanceManageCashSerialDO = incomeSpendingRecord(transferAccountsReqDTO, IN);

        // 2.准备更新转账单的数据  转账单转账状态：已进账，更新进账时间，进账人
        FinanceManageCashTransferDO financeManageCashTransferDO = new FinanceManageCashTransferDO();
        financeManageCashTransferDO.setCompanyId(transferAccountsReqDTO.getCompanyId());
        financeManageCashTransferDO.setId(submitBillId);
        financeManageCashTransferDO.setTransferStatus(FinanceTransferStatusEnum.RECEIPTED.getType());
        financeManageCashTransferDO.setIncomeTime(new Date().getTime());
        financeManageCashTransferDO.setIncomeUserId(transferAccountsReqDTO.getUserId());

        // 3.插入现金流水DB和更新转账单
        boolean insertDB = intoAccountInsertDB(inFinanceManageCashSerialDO,financeManageCashTransferDO);

        if (insertDB) {
            // 5.插入ES
            try {
                String settleNetWorkName = getSettleNetWorkName(transferAccountsReqDTO);
                FinanceManageCashSerialEsDTO inFinanceManageCashSerialEsDTO = new FinanceManageCashSerialEsDTO();
                EntityUtils.copyProperties(inFinanceManageCashSerialDO, inFinanceManageCashSerialEsDTO);
                inFinanceManageCashSerialEsDTO.setCodTypeName(CodeTypeEnum.CASH.getName());
                inFinanceManageCashSerialEsDTO.setCodType(CodeTypeEnum.CASH.getType());
                inFinanceManageCashSerialEsDTO.setSettleNetworkName(settleNetWorkName);
                inFinanceManageCashSerialEsDTO.setReceiptPayNetworkName(transferAccountsReqDTO.getInCurentOrgName());
                inFinanceManageCashSerialEsDTO.setSubmitBillStateName(TransferAccountsStatusEnum.UNTRANSFER.getName());
                boolean inInsertES = esFinanceManageCashSerialService.insert(inFinanceManageCashSerialEsDTO);
            } catch (FrameworkException e) {
                // 回滚DB
                rollbackIntoAccountDB(inFinanceManageCashSerialDO);
                log.error("进账接口，现金流水添加收入记录到ES出现异常{}", e.getMessage(), e);
                throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_MANAGE_CASH_INSERT, "进账接口，现金流水添加添加收入记录到ES出现异常");
            }

            try {
                FinanceManageCashTransferEsDTO financeManageCashTransferEsDTO = new FinanceManageCashTransferEsDTO();
                EntityUtils.copyProperties(financeManageCashTransferDO, financeManageCashTransferEsDTO);
                financeManageCashTransferEsDTO.setTransferStatusName(FinanceTransferStatusEnum.RECEIPTED.getName());
                financeManageCashTransferEsDTO.setIncomeUserName(transferAccountsReqDTO.getUserName());
                esFinanceManageCashTransferService.update(financeManageCashTransferEsDTO);
            } catch (Exception e) {
                // 回滚DB
                rollbackIntoAccountDB(inFinanceManageCashSerialDO);
                //回滚ES
                esFinanceManageCashSerialService.deleteById(inFinanceManageCashSerialDO.getId());
                log.error("进账接口，更新ES转账数据异常{}", e.getMessage(), e);
                throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_MANAGE_CASH_INSERT, "进账接口根据ID" + financeManageCashTransferDO.getId() + "更新ES转账数据异常");
            }

            // 更新现金流水表
            try {
                FinanceManageCashSerialEsDTO financeManageCashSerialEsDTO = new FinanceManageCashSerialEsDTO();
                financeManageCashSerialEsDTO.setSubmitBillId(submitBillId);
                financeManageCashSerialEsDTO.setCompanyId(financeManageCashTransferDO.getCompanyId());
                financeManageCashSerialEsDTO.setSubmitBillState(FinanceTransferStatusEnum.RECEIPTED.getType());
                financeManageCashSerialEsDTO.setSubmitBillStateName(FinanceTransferStatusEnum.RECEIPTED.getName());
                esFinanceManageCashSerialService.updateBySubmitBillId(submitBillId,financeManageCashSerialEsDTO);
            } catch (Exception e) {
                log.error("进账接口更新现金流水异常{}", e.getMessage(), e);
                throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_SERIAL_UPDATE, "进账接口根据转账单ID：" + financeManageCashTransferDO.getId() + " 更新现金流水失败！");
            }
        }


        return insertDB;
    }

    /**
     * @param
     * @author sql
     * @Date 2018\5\18 0018
     * @Describe 进账接口回滚DB
     */
    private void rollbackIntoAccountDB(FinanceManageCashSerialDO inFinanceManageCashSerialDO) {

        try {
            financeManageCashSerialMapper.deleteByPrimaryKey(inFinanceManageCashSerialDO);

        } catch (Exception e) {
            log.error("进账接口回滚DB异常", e.getMessage(), e);
        }

    }


    /**
     * @param
     * @author sql
     * @Date 2018\5\17 0017
     * @Describe 进账入库DB
     */
    @ChainedTransaction(mapper = {FinanceManageCashSerialMapper.class, FinanceManageCashTransferMapper.class}, timeout = TransactionConstants.TIME_OUT)
    public boolean intoAccountInsertDB(@RouteParam(("FinanceManageCashSerialMapper.companyId")) FinanceManageCashSerialDO inFinanceManageCashSerialDO,@RouteParam(("FinanceManageCashTransferMapper.companyId")) FinanceManageCashTransferDO financeManageCashTransferDO) {

        boolean inInsertDB = false;
        try {
            inInsertDB = financeManageCashSerialMapper.insert(inFinanceManageCashSerialDO) > 0;
        } catch (Exception e) {
            log.error("进账接口，现金流水添加收入记录到DB出现异常{}", e.getMessage(), e);
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_MANAGE_CASH_INSERT, "进账接口，现金流水添加添加收入记录到DB出现异常");
        }

        boolean updateTransferDB = false;
        try {
            updateTransferDB = financeManageCashTransferMapper.updateByPrimaryKeySelective(financeManageCashTransferDO) > 0;
        } catch (Exception e) {
            log.error("进账接口，更新DB转账数据异常{}", e.getMessage(), e);
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_MANAGE_CASH_INSERT, "进账接口根据ID" + financeManageCashTransferDO.getId() + "更新DB转账数据异常");
        }
        // 更新现金流水表
        try {
            FinanceManageCashSerialDO financeManageCashSerialDO = new FinanceManageCashSerialDO();
            financeManageCashSerialDO.setSubmitBillId(financeManageCashTransferDO.getId());
            financeManageCashSerialDO.setCompanyId(financeManageCashTransferDO.getCompanyId());
            financeManageCashSerialDO.setSubmitBillState(FinanceTransferStatusEnum.RECEIPTED.getType());
            financeManageCashSerialMapper.updateBySubmitId(financeManageCashSerialDO);
        } catch (Exception e) {
            log.error("进账接口更新现金流水异常{}", e.getMessage(), e);
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_SERIAL_UPDATE, "进账接口根据转账单ID：" + financeManageCashTransferDO.getId() + " 更新现金流水失败！");
        }

        return inInsertDB & updateTransferDB;


    }

    /**
     * @param
     * @author sql
     * @Date 2018\5\17 0017
     * @Describe 进账接口，生成收支出记录
     */
    private FinanceManageCashSerialDO incomeSpendingRecord(FinanceManageCashTransferReqDTO transferAccountsReqDTO, String type) {

        FinanceManageCashSerialDO financeManageCashSerialDO = new FinanceManageCashSerialDO();
        Long id = generatedIdService.getIncrUniqueId(GeneratedIdConstants.TMS_FINANCE_MANAGE_CASH_SERIAL);
        financeManageCashSerialDO.setId(id);
        financeManageCashSerialDO.setCompanyId(transferAccountsReqDTO.getCompanyId());
//        financeManageCashSerialDO.setSubmitBillId(transferAccountsReqDTO.getSubmitBillId());
        financeManageCashSerialDO.setOrdersourceId(transferAccountsReqDTO.getTransferOrdersource());// 来源单号
        if (IN.equals(type)) {//收入
            financeManageCashSerialDO.setFundAccount(transferAccountsReqDTO.getIncomeAccount());// 资金账号
            financeManageCashSerialDO.setReceiptAmount(transferAccountsReqDTO.getTransferAmount());//收入
            financeManageCashSerialDO.setPayAmount(new BigDecimal(0));//支出
            financeManageCashSerialDO.setReceiptPayNetworkId(transferAccountsReqDTO.getInCurentOrgId());//转账网点
            //获取核算网点
            Integer settleNetworkId = financeManageGoodsRecycleComponentService.setSettleObjectId(transferAccountsReqDTO.getInCurentOrgId());
            financeManageCashSerialDO.setSettleNetworkId(settleNetworkId);//核算网点
        } else {//支出
            financeManageCashSerialDO.setFundAccount(transferAccountsReqDTO.getTransferAccount());// 资金账号
            financeManageCashSerialDO.setReceiptAmount(new BigDecimal(0));//收入
            financeManageCashSerialDO.setPayAmount(transferAccountsReqDTO.getTransferAmount());//支出
            financeManageCashSerialDO.setReceiptPayNetworkId(transferAccountsReqDTO.getOutCurentOrgId());//转账网点
            Integer settleNetworkId = financeManageGoodsRecycleComponentService.setSettleObjectId(transferAccountsReqDTO.getOutCurentOrgId());
            financeManageCashSerialDO.setSettleNetworkId(settleNetworkId);//核算网点
        }
        financeManageCashSerialDO.setRemark(transferAccountsReqDTO.getRemark());//备注
        financeManageCashSerialDO.setSubmitBillState(TransferAccountsStatusEnum.UNTRANSFER.getType());//  转账状态
        financeManageCashSerialDO.setCreateBillTime(new Date().getTime());//制单时间
        financeManageCashSerialDO.setReceiptPayTime(new Date().getTime());//发生时间
        financeManageCashSerialDO.setCreateTime(new Date().getTime());//创建时间
        financeManageCashSerialDO.setCreateUserId(transferAccountsReqDTO.getUserId());//制单人
        financeManageCashSerialDO.setStatus(FlowStatusEnum.NORMAL.getType());//状态

        return financeManageCashSerialDO;
    }

    /**
     *@author sql
     *@Date 2018\6\6 0006
     *@param
     *@Describe 获取核算网点名称
     */
    public String getSettleNetWorkName(FinanceManageCashTransferReqDTO transferAccountsReqDTO){

        //获取核算网点
        QueryByIdReqParam queryByIdReqParam = new QueryByIdReqParam();
        queryByIdReqParam.setId(transferAccountsReqDTO.getOutCurentOrgId());
        Integer userId = transferAccountsReqDTO.getUserId();
        if(userId!=null){
            queryByIdReqParam.setUserId(new Long(userId));
        }
        DataResult<BizUnitWithFuncDetailVo> bizUnitWithFuncDetailVoDataResult = bossBizUnitApi.queryBizUnitById(queryByIdReqParam);
        if(bizUnitWithFuncDetailVoDataResult!=null){
            BizUnitWithFuncDetailVo result = bizUnitWithFuncDetailVoDataResult.getResult();
            if(result!=null){
                LogisticsOrgFuncInfoBean logistics = result.getLogistics();
                if(logistics!=null){
                    return logistics.getAccountOrgName();
                }
            }
        }
        return null;
    }

    /**
     * @param
     * @author sql
     * @Date 2018\5\17 0017
     * @Describe 取消提交 更新ES方法
     */
    private boolean cancelCommitUpdateES(FinanceManageCashSerialDO outFinanceManageCashSerialDO,FinanceManageCashTransferReqDTO transferAccountsReqDTO,FinanceManageCashTransferDO financeManageCashTransferDO, FinanceManageCashSerialDO financeManageCashSerialDO) {
        boolean updateTransferES = false;
        Long companyId = financeManageCashTransferDO.getCompanyId();
        // 更新转账表ES
        try {
            FinanceManageTransferCancelCommitEsDTO financeManageTransferCancelCommitEsDTO = new FinanceManageTransferCancelCommitEsDTO();
            EntityUtils.copyProperties(financeManageCashTransferDO, financeManageTransferCancelCommitEsDTO);
            financeManageTransferCancelCommitEsDTO.setTransferStatusName(FinanceTransferStatusEnum.UNTRANSFER.getName());
            financeManageTransferCancelCommitEsDTO.setCancelSubmitUserName(transferAccountsReqDTO.getUserName());
            updateTransferES = esFinanceManageCashTransferService.updateNull(financeManageTransferCancelCommitEsDTO);
        } catch (Exception e) {
            // 回滚DB
            rollbackCancelCommitDB(financeManageCashSerialDO.getSubmitBillId(), companyId);
            log.error("取消提交,更新ES转账列表异常{}", e.getMessage(), e);
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_TRANSFER_UPDATE, "取消提交，根据转账单ID：" + financeManageCashTransferDO.getId() + " 更新ES转账单失败！");
        }

        //插入现金流水ES
        try {
            String settleNetWorkName = getSettleNetWorkName(transferAccountsReqDTO);
            FinanceManageCashSerialEsDTO outFinanceManageCashSerialEsDTO = new FinanceManageCashSerialEsDTO();
            EntityUtils.copyProperties(outFinanceManageCashSerialDO, outFinanceManageCashSerialEsDTO);
            outFinanceManageCashSerialEsDTO.setCodType(CodeTypeEnum.CASH.getType());
            outFinanceManageCashSerialEsDTO.setSubmitBillStateName(TransferAccountsStatusEnum.UNTRANSFER.getName());
            outFinanceManageCashSerialEsDTO.setReceiptPayNetworkName(transferAccountsReqDTO.getOutCurentOrgName());
            outFinanceManageCashSerialEsDTO.setCodTypeName(CodeTypeEnum.CASH.getName());
            outFinanceManageCashSerialEsDTO.setSettleNetworkName(settleNetWorkName);
            esFinanceManageCashSerialService.insert(outFinanceManageCashSerialEsDTO);
        } catch (FrameworkException e) {
            // 回滚DB
            rollbackCancelCommitDB(financeManageCashSerialDO.getSubmitBillId(), companyId);
            financeManageCashSerialMapper.deleteByPrimaryKey(outFinanceManageCashSerialDO);
            //回滚转账ES
            rollbackCancelCommitES(financeManageCashSerialDO);
            log.error("进账接口，现金流水添加支出记录到ES出现异常{}", e.getMessage(), e);
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_MANAGE_CASH_INSERT, "进账接口，现金流水添加添加支出记录到ES出现异常");
        }

        //更新现金流水ES
        try {
            FinanceManageSerialCancelCommitEsDTO financeManageSerialCancelCommitEsDTO = new FinanceManageSerialCancelCommitEsDTO();
            EntityUtils.copyProperties(financeManageCashSerialDO, financeManageSerialCancelCommitEsDTO);
            financeManageSerialCancelCommitEsDTO.setSubmitBillStateName(FinanceTransferStatusEnum.UNTRANSFER.getName());
            esFinanceManageCashSerialService.cancelCommitUpdateBySubmitBillId(financeManageSerialCancelCommitEsDTO.getSubmitBillId(), financeManageSerialCancelCommitEsDTO);
        } catch (FrameworkException e) {
            // 回滚DB
            rollbackCancelCommitDB(financeManageCashSerialDO.getSubmitBillId(), companyId);
            //回滚转账ES
            rollbackCancelCommitES(financeManageCashSerialDO);
            esFinanceManageCashSerialService.deleteById(outFinanceManageCashSerialDO.getId());
            financeManageCashSerialMapper.deleteByPrimaryKey(outFinanceManageCashSerialDO);
            log.error("取消提交,更新ES现金流水异常{}", e.getMessage(), e);
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_SERIAL_UPDATE, " 取消提交,根据转账单ID：" + financeManageCashSerialDO.getSubmitBillId() + " 更新ES现金流水失败！");
        }

        return updateTransferES;
    }

    /**
     * @param
     * @author sql
     * @Date 2018\5\17 0017
     * @Describe 回滚转账ES
     */
    private void rollbackCancelCommitES(FinanceManageCashSerialDO financeManageCashSerialDO) {

        //回滚转账ES
        FinanceManageCashTransferEsDTO financeManageCashTransferEsDTO = new FinanceManageCashTransferEsDTO();
        financeManageCashTransferEsDTO.setId(financeManageCashSerialDO.getSubmitBillId());
        financeManageCashTransferEsDTO.setTransferStatus(FinanceTransferStatusEnum.TRANSFERED.getType());
        esFinanceManageCashTransferService.update(financeManageCashTransferEsDTO);

    }

    /**
     * @param
     * @author sql
     * @Date 2018\5\17 0017
     * @Describe 取消提交, 更新转账表和现金流水DB
     */
    @ChainedTransaction(mapper = {FinanceManageCashTransferMapper.class, FinanceManageCashSerialMapper.class}, timeout = TransactionConstants.TIME_OUT)
    public boolean cancelCommitUpdateDB(FinanceManageCashSerialDO outFinanceManageCashSerialDO,@RouteParam("FinanceManageCashTransferMapper.companyId") FinanceManageCashTransferDO financeManageCashTransferDO,
                                        @RouteParam("FinanceManageCashSerialMapper.companyId") FinanceManageCashSerialDO financeManageCashSerialDO) {
        boolean updateTransferDB = false;
        try {
            updateTransferDB = financeManageCashTransferMapper.updateCancelCommit(financeManageCashTransferDO) > 0;
        } catch (Exception e) {
            log.error("取消提交,更新转账表DB异常{}", e.getMessage(), e);
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_TRANSFER_UPDATE, "取消提交,根据转账单ID：" + financeManageCashTransferDO.getId() + " 更新转账表异常");
        }
        if (!updateTransferDB) {
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_TRANSFER_UPDATE, "取消提交,根据转账单ID：" + financeManageCashTransferDO.getId() + " 更新转账单数量为0！");
        }

        try {
            financeManageCashSerialMapper.cancelCommitUpdate(financeManageCashSerialDO);
        } catch (Exception e) {
            rollbackCancelCommitDB(financeManageCashSerialDO.getSubmitBillId(), financeManageCashSerialDO.getCompanyId());
            log.error("取消提交,更新现金流水DB异常{}", e.getMessage(), e);
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_SERIAL_UPDATE, "取消提交,根据转账单ID：" + financeManageCashSerialDO.getSubmitBillId() + " 更新现金流水异常");
        }

        //插入一条转账金额为负数的现金流水支出数据
        try {
            financeManageCashSerialMapper.insert(outFinanceManageCashSerialDO);
        } catch (Exception e) {
            log.error("进账接口，现金流水添加支出记录到DB出现异常{}", e.getMessage(), e);
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_MANAGE_CASH_INSERT, "进账接口，现金流水添加添加支出记录到DB出现异常");
        }

        return updateTransferDB;
    }

    /**
     * @param
     * @author sql
     * @Date 2018\5\16 0016
     * @Describe 转账提交更新转账列表和现金流水的ES
     */
    private boolean commitupdateES(FinanceManageCashSerialDO outFinanceManageCashSerialDO,FinanceManageCashTransferReqDTO transferAccountsReqDTO,Long submitBillId, Long companyId) {
        boolean updateTransferEsFlag = false;
        //3.1 更新转账列表ES
        FinanceManageCashTransferEsDTO financeManageCashTransferEsDTO = new FinanceManageCashTransferEsDTO();
        financeManageCashTransferEsDTO.setId(submitBillId);
        financeManageCashTransferEsDTO.setTransferStatusName(FinanceTransferStatusEnum.TRANSFERED.getName());
        financeManageCashTransferEsDTO.setTransferStatus(FinanceTransferStatusEnum.TRANSFERED.getType());
        financeManageCashTransferEsDTO.setSubmitTime(new Date().getTime());
        financeManageCashTransferEsDTO.setSubmitUserId(transferAccountsReqDTO.getUserId());
        financeManageCashTransferEsDTO.setSubmitUserName(transferAccountsReqDTO.getUserName());
        try {
            updateTransferEsFlag = esFinanceManageCashTransferService.update(financeManageCashTransferEsDTO);
        } catch (Exception e) {
            // 回滚所有DB
            rollbackCommitDB(submitBillId, companyId);
            log.error("更新ES转账列表异常{}", e.getMessage(), e);
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_TRANSFER_UPDATE, "根据转账单ID：" + submitBillId + " 更新ES转账单失败！");
        }

        //3.2插入现金流水ES
        try {
            String settleNetWorkName = getSettleNetWorkName(transferAccountsReqDTO);
            FinanceManageCashSerialEsDTO outFinanceManageCashSerialEsDTO = new FinanceManageCashSerialEsDTO();
            EntityUtils.copyProperties(outFinanceManageCashSerialDO, outFinanceManageCashSerialEsDTO);
            outFinanceManageCashSerialEsDTO.setCodType(CodeTypeEnum.CASH.getType());
            outFinanceManageCashSerialEsDTO.setCodTypeName(CodeTypeEnum.CASH.getName());
            outFinanceManageCashSerialEsDTO.setReceiptPayNetworkName(transferAccountsReqDTO.getOutCurentOrgName());
            outFinanceManageCashSerialEsDTO.setSettleNetworkName(settleNetWorkName);
            outFinanceManageCashSerialEsDTO.setSubmitBillStateName(TransferAccountsStatusEnum.UNTRANSFER.getName());
            boolean outInsertES = esFinanceManageCashSerialService.insert(outFinanceManageCashSerialEsDTO);
        } catch (FrameworkException e) {
            // 回滚所有DB
            rollbackCommitDB(submitBillId, companyId);
            financeManageCashSerialMapper.deleteByPrimaryKey(outFinanceManageCashSerialDO);
            // 回滚更新转账列表ES
            financeManageCashTransferEsDTO.setTransferStatus(FinanceTransferStatusEnum.UNTRANSFER.getType());
            financeManageCashTransferEsDTO.setTransferStatusName(FinanceTransferStatusEnum.UNTRANSFER.getName());
            esFinanceManageCashTransferService.update(financeManageCashTransferEsDTO);

            log.error("进账接口，现金流水添加支出记录到ES出现异常{}", e.getMessage(), e);
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_MANAGE_CASH_INSERT, "进账接口，现金流水添加添加支出记录到ES出现异常");
        }

        //3.3 更新现金流水ES
        FinanceManageCashSerialEsDTO financeManageCashSerialEsDTO = new FinanceManageCashSerialEsDTO();
        financeManageCashSerialEsDTO.setSubmitBillState(FinanceTransferStatusEnum.TRANSFERED.getType());
        financeManageCashSerialEsDTO.setSubmitBillTime(new Date().getTime());
        financeManageCashSerialEsDTO.setSubmitBillStateName(FinanceTransferStatusEnum.TRANSFERED.getName());
        financeManageCashSerialEsDTO.setCompanyId(companyId);
        try {
            esFinanceManageCashSerialService.updateBySubmitBillId(submitBillId, financeManageCashSerialEsDTO);
        } catch (Exception e) {
            // 回滚所有DB
            rollbackCommitDB(submitBillId, companyId);
            financeManageCashSerialMapper.deleteByPrimaryKey(outFinanceManageCashSerialDO);
            esFinanceManageCashSerialService.deleteById(outFinanceManageCashSerialDO.getId());
            // 回滚更新转账列表ES
            financeManageCashTransferEsDTO.setTransferStatus(FinanceTransferStatusEnum.UNTRANSFER.getType());
            financeManageCashTransferEsDTO.setTransferStatusName(FinanceTransferStatusEnum.UNTRANSFER.getName());
            esFinanceManageCashTransferService.update(financeManageCashTransferEsDTO);
            log.error("更新ES现金流水异常{}", e.getMessage(), e);
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_SERIAL_UPDATE, "根据转账单ID：" + submitBillId + " 更新ES现金流水失败！");
        }

        return updateTransferEsFlag;
    }

    /**
     * @param
     * @author sql
     * @Date 2018\5\17 0017
     * @Describe 回滚取消提交DB的方法
     */
    private void rollbackCancelCommitDB(Long submitBillId, Long companyId) {

        // 更新转账列表DB 状态为已提交,更新转账时间
        FinanceManageCashTransferDO financeManageCashTransferDO = new FinanceManageCashTransferDO();
        financeManageCashTransferDO.setId(submitBillId);
        financeManageCashTransferDO.setCompanyId(companyId);
        financeManageCashTransferDO.setTransferStatus(FinanceTransferStatusEnum.TRANSFERED.getType());

        dbFinanceManageCashTransferService.update(financeManageCashTransferDO);

        //  更新现金流水DB
        FinanceManageCashSerialDO financeManageCashSerialDO = new FinanceManageCashSerialDO();
        financeManageCashSerialDO.setSubmitBillId(submitBillId);
        financeManageCashSerialDO.setCompanyId(companyId);
        financeManageCashSerialDO.setSubmitBillState(FinanceTransferStatusEnum.TRANSFERED.getType());

        dbFinanceManageCashSerialService.updateById(financeManageCashSerialDO);


    }

    /**
     * @param
     * @author sql
     * @Date 2018\5\16 0016
     * @Describe 回滚转账提交DB的方法
     */
    private void rollbackCommitDB(Long submitBillId, Long companyId) {

        // 更新转账列表DB 状态为已提交,更新转账时间
        FinanceManageCashTransferDO financeManageCashTransferDO = new FinanceManageCashTransferDO();
        financeManageCashTransferDO.setCompanyId(companyId);
        financeManageCashTransferDO.setId(submitBillId);
        financeManageCashTransferDO.setTransferStatus(FinanceTransferStatusEnum.UNTRANSFER.getType());

        dbFinanceManageCashTransferService.update(financeManageCashTransferDO);

        //  更新现金流水DB
        FinanceManageCashSerialDO financeManageCashSerialDO = new FinanceManageCashSerialDO();
        financeManageCashSerialDO.setSubmitBillId(submitBillId);
        financeManageCashSerialDO.setCompanyId(companyId);
        financeManageCashSerialDO.setSubmitBillState(FinanceTransferStatusEnum.UNTRANSFER.getType());

        dbFinanceManageCashSerialService.updateById(financeManageCashSerialDO);


    }

    /**
     * @param
     * @author sql
     * @Date 2018\5\16 0016
     * @Describe 转账提交更新DB的方法
     */
    @ChainedTransaction(mapper = {FinanceManageCashTransferMapper.class, FinanceManageCashSerialMapper.class}, timeout = TransactionConstants.TIME_OUT)
    public Boolean commitUpdateDB(FinanceManageCashSerialDO outFinanceManageCashSerialDO,FinanceManageCashTransferReqDTO transferAccountsReqDTO,Long submitBillId, Long companyId, @RouteParam("FinanceManageCashTransferMapper.companyId") FinanceManageCashSerialDO financeManageCashSerialDO) {
        // 更新转账列表DB 状态为已提交,更新转账时间
        FinanceManageCashTransferDO financeManageCashTransferDO = new FinanceManageCashTransferDO();
        financeManageCashTransferDO.setId(submitBillId);
        financeManageCashTransferDO.setCompanyId(companyId);
        financeManageCashTransferDO.setSubmitUserId(financeManageCashTransferDO.getSubmitUserId());
        financeManageCashTransferDO.setTransferStatus(FinanceTransferStatusEnum.TRANSFERED.getType());
        financeManageCashTransferDO.setSubmitTime(new Date().getTime());
        financeManageCashTransferDO.setSubmitUserId(transferAccountsReqDTO.getUserId());
        boolean updateTransferFlag = false;
        try {
            updateTransferFlag = financeManageCashTransferMapper.updateByPrimaryKeySelective(financeManageCashTransferDO) > 0;

        } catch (Exception e) {
            log.error("转账提交接口更新转账列表异常{}", e.getMessage(), e);
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_TRANSFER_UPDATE, "转账提交接口根据转账单ID：" + submitBillId + " 更新转账单失败！");
        }
        if (!updateTransferFlag) {
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_TRANSFER_UPDATE, "转账提交接口根据转账单ID：" + submitBillId + " 更新转账单数量为0！");
        }

        //  更新现金流水DB
        financeManageCashSerialDO.setSubmitBillId(submitBillId);
        financeManageCashSerialDO.setCompanyId(companyId);
        financeManageCashSerialDO.setSubmitBillTime(new Date().getTime());
        financeManageCashSerialDO.setSubmitBillState(FinanceTransferStatusEnum.TRANSFERED.getType());
        try {
            financeManageCashSerialMapper.updateBySubmitId(financeManageCashSerialDO);
        } catch (Exception e) {
            log.error("转账提交接口更新现金流水异常{}", e.getMessage(), e);
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_SERIAL_UPDATE, "转账提交接口根据转账单ID：" + submitBillId + " 更新现金流水失败！");
        }

        // 2.生成现金流水支出记录
        try {
            financeManageCashSerialMapper.insert(outFinanceManageCashSerialDO);
        } catch (Exception e) {
            log.error("进账接口，现金流水添加支出记录到DB出现异常{}", e.getMessage(), e);
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_MANAGE_CASH_INSERT, "进账接口，现金流水添加添加支出记录到DB出现异常");
        }

        return updateTransferFlag;
    }

    /**
     * @param
     * @author sql
     * @Date 2018\5\18 0018
     * @Describe 删除接口
     */
    @Override
    public boolean delete(FinanceManageCashTransferReqDTO transferAccountsReqDTO) {
        // 1.准备参数
        Assert.isNotNull(transferAccountsReqDTO.getSubmitBillId(),"转账单ID不能为空!");
        Assert.isNotNull(transferAccountsReqDTO.getCompanyId(),"公司ID不能为空!");
        Long companyId = transferAccountsReqDTO.getCompanyId();
        Long submitBillId = transferAccountsReqDTO.getSubmitBillId();
        // 根据submitBillId 查询出转账单和现金流水数据
        FinanceManageCashTransferEsDTO financeManageCashTransferEsDTO = esFinanceManageCashTransferService.queryListBySubmitBillId(submitBillId);
        if(financeManageCashTransferEsDTO==null){
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_TRANSFER_DELETE, "转账删除接口,根据转账单ID：" + submitBillId + " 没有查询到转账单数据！");
        }
        FinanceManageCashSerialEsDTO financeManageCashSerialEsDTO = new FinanceManageCashSerialEsDTO();
        financeManageCashSerialEsDTO.setSubmitBillId(submitBillId);
        List<FinanceManageCashSerialEsDTO> financeManageCashSerialEsDTOList = esFinanceManageCashSerialService.querySerialList(financeManageCashSerialEsDTO);
        // DB:删除转账单和更新现金流水方法
        boolean deleteAndUpdateDB = deleteAndUpdateDB(submitBillId, companyId, financeManageCashTransferEsDTO, financeManageCashSerialEsDTOList);
        boolean deleteES = false;
        // ES:删除转账单
        try {
            deleteES = esFinanceManageCashTransferService.deleteById(submitBillId);
        } catch (Exception e) {
            // 回滚DB
            deleteRollbackDB(financeManageCashTransferEsDTO, financeManageCashSerialEsDTOList);
            log.error("转账删除接口:删除转账列表异常{}", e.getMessage(), e);
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_TRANSFER_DELETE, "转账删除接口,根据转账单ID：" + submitBillId + " 删除转账单失败！");
        }
        // ES:更新现金流水方法
        try {
            FinanceManageSerialdeleteEsDTO financeManageSerialdeleteEsDTO = new FinanceManageSerialdeleteEsDTO();
            financeManageSerialdeleteEsDTO.setSubmitBillState(FinanceTransferStatusEnum.UNTRANSFER.getType());
            financeManageSerialdeleteEsDTO.setSubmitBillStateName(FinanceTransferStatusEnum.UNTRANSFER.getName());
            esFinanceManageCashSerialService.deleteUpdateBySubmitBillId(submitBillId, financeManageSerialdeleteEsDTO);
        } catch (Exception e) {
            //回滚DB
            deleteRollbackDB(financeManageCashTransferEsDTO, financeManageCashSerialEsDTOList);
            //回滚ES
            esFinanceManageCashTransferService.insert(financeManageCashTransferEsDTO);
            log.error("转账:删除接口,更新现金流水异常{}", e.getMessage(), e);
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_SERIAL_UPDATE, "转账:删除接口,根据转账单ID：" + submitBillId + " 更新现金流水失败！");
        }

        return deleteAndUpdateDB && deleteES;
    }

    /**
     * @param
     * @author sql
     * @Date 2018\5\19 0019
     * @Describe 删除接口，回滚DB方法
     */
    private void deleteRollbackDB(FinanceManageCashTransferEsDTO financeManageCashTransferEsDTO, List<FinanceManageCashSerialEsDTO> financeManageCashSerialEsDTOList) {

        // 回滚转账单
        FinanceManageCashTransferDO financeManageCashTransferDO = new FinanceManageCashTransferDO();
        EntityUtils.copyProperties(financeManageCashTransferEsDTO, financeManageCashTransferDO);
        financeManageCashTransferMapper.insert(financeManageCashTransferDO);

        if(financeManageCashSerialEsDTOList==null){
            return;
        }
        // 回滚现金流水
        List<FinanceManageCashSerialDO> financeManageCashSerialDOList = Lists.newArrayList();
        for (FinanceManageCashSerialEsDTO financeManageCashSerialEsDTO : financeManageCashSerialEsDTOList) {
            FinanceManageCashSerialDO financeManageCashSerialDO = new FinanceManageCashSerialDO();
            EntityUtils.copyProperties(financeManageCashSerialEsDTO, financeManageCashSerialDO);
            financeManageCashSerialDOList.add(financeManageCashSerialDO);
        }
        financeManageCashSerialMapper.updateBatch(financeManageCashSerialDOList);

    }


    /**
     * @param
     * @author sql
     * @Date 2018\5\18 0018
     * @Describe 修改接口
     */
    @Override
    public boolean update(FinanceManageCashTransferReqDTO transferReqDTO) {
        //获取参数
        checkField(transferReqDTO);
        Long submitBillId = transferReqDTO.getSubmitBillId();
        List<Long> serialIdList = transferReqDTO.getSerialIdList();
        FinanceManageCashTransferEsDTO tollBackFinanceManageCashTransferEsDTO = esFinanceManageCashTransferService.queryListBySubmitBillId(transferReqDTO.getSubmitBillId());
        // 更新主表数据
        FinanceManageCashTransferDO rollbackFinanceManageCashTransferDO = updateMainTable(transferReqDTO,tollBackFinanceManageCashTransferEsDTO);
        // 根据转账单号ID 查询所属的现金流水
        FinanceManageCashSerialEsDTO financeManageCashSerialEsDTO = new FinanceManageCashSerialEsDTO();
        financeManageCashSerialEsDTO.setSubmitBillId(submitBillId);
        List<FinanceManageCashSerialEsDTO> financeManageCashSerialEsDTOS = esFinanceManageCashSerialService.querySerialList(financeManageCashSerialEsDTO);

        //判断有没有新添加进来的
        Map<String, Long> reqMap = new HashMap<>();
        Map<String, Long> queryMap = new HashMap<>();
        serialIdList.forEach(n -> reqMap.put(n.toString(), n));
        if (financeManageCashSerialEsDTOS != null && financeManageCashSerialEsDTOS.size() > 0) {
            financeManageCashSerialEsDTOS.forEach(n -> queryMap.put(n.getId().toString(), n.getId()));
        }
        // 添加现金流水单方法
        List<FinanceManageCashSerialDO> financeManageCashSerialDOS = addTranfer(transferReqDTO, queryMap, serialIdList,rollbackFinanceManageCashTransferDO,tollBackFinanceManageCashTransferEsDTO);
        // 移除现金流水单方法
        if (financeManageCashSerialEsDTOS != null && financeManageCashSerialEsDTOS.size() > 0) {
            subtractTransfer(transferReqDTO, reqMap, financeManageCashSerialEsDTOS, financeManageCashSerialDOS,rollbackFinanceManageCashTransferDO,tollBackFinanceManageCashTransferEsDTO);
        }

        return true;
    }

    /**
     * 详情查询
     * @param id
     * @return
     */
    @Override
    public FinanceManageCashTransferDetailsRespDTO getDetails(Long id) {
        FinanceManageCashTransferDetailsRespDTO financeManageCashTransferDetailsRespDTO = new FinanceManageCashTransferDetailsRespDTO();
        FinanceManageCashTransferEsDTO financeManageCashTransferEsDTO = esFinanceManageCashTransferService.getDetails(id);
        List<FinanceManageCashTransferAddRespDTO> details = esFinanceManageCashSerialService.getDetails(id);
        //目标值为空用"—"显示
        if(details!=null){
            details.forEach(detail->{
                if(null==detail.getCodTypeName()){
                    detail.setCodTypeName("—");
                }
                if(null==detail.getFeeTypeName()){
                    detail.setFeeTypeName("—");
                }
            });
        }
        //转账Es
        financeManageCashTransferDetailsRespDTO.setTransferOrdersource(financeManageCashTransferEsDTO.getTransferOrdersource());
        financeManageCashTransferDetailsRespDTO.setTransferType(financeManageCashTransferEsDTO.getTransferType());
        financeManageCashTransferDetailsRespDTO.setTransferAccount(financeManageCashTransferEsDTO.getTransferAccount());
        financeManageCashTransferDetailsRespDTO.setIncomeAccount(financeManageCashTransferEsDTO.getIncomeAccount());
        financeManageCashTransferDetailsRespDTO.setTransferAmount(financeManageCashTransferEsDTO.getTransferAmount());
        financeManageCashTransferDetailsRespDTO.setRemark(financeManageCashTransferEsDTO.getRemark());
        financeManageCashTransferDetailsRespDTO.setId(financeManageCashTransferEsDTO.getId());
        financeManageCashTransferDetailsRespDTO.setTransferStatus(financeManageCashTransferEsDTO.getTransferStatus());
        financeManageCashTransferDetailsRespDTO.setTransferStatusName(financeManageCashTransferEsDTO.getTransferStatusName());
        financeManageCashTransferDetailsRespDTO.setIncomeNetworkId(financeManageCashTransferEsDTO.getIncomeNetworkId());
        financeManageCashTransferDetailsRespDTO.setIncomeNetworkName(financeManageCashTransferEsDTO.getIncomeNetworkName());
        financeManageCashTransferDetailsRespDTO.setTransferNetworkId(financeManageCashTransferEsDTO.getTransferNetworkId());
        financeManageCashTransferDetailsRespDTO.setTransferNetworkName(financeManageCashTransferEsDTO.getTransferNetworkName());
        financeManageCashTransferDetailsRespDTO.setTransferTypeName(financeManageCashTransferEsDTO.getTransferTypeName());
        financeManageCashTransferDetailsRespDTO.setIncomeAccountBelongId(financeManageCashTransferEsDTO.getIncomeAccountBelongId());
        financeManageCashTransferDetailsRespDTO.setIncomeAccountBelong(financeManageCashTransferEsDTO.getIncomeAccountBelong());
        financeManageCashTransferDetailsRespDTO.setTransferAccountBelongId(financeManageCashTransferEsDTO.getTransferAccountBelongId());
        financeManageCashTransferDetailsRespDTO.setSubmitTime(financeManageCashTransferEsDTO.getSubmitTime());
        financeManageCashTransferDetailsRespDTO.setCancelSubmitTime(financeManageCashTransferEsDTO.getCancelSubmitTime());
        //现金流水
        financeManageCashTransferDetailsRespDTO.setFinanceManageCashTransferAddRespDTO(details);
        return financeManageCashTransferDetailsRespDTO;
    }

    /**
     * 打印主表
     * @param req
     * @return
     */
    @Override
    public FinanceManagePrintTransferRsepDTO printCash(FinanceManagePrintCash req) {
        FinanceManagePrintTransferRsepDTO financeManagePrintTransferRsepDTO = new FinanceManagePrintTransferRsepDTO();
        FinanceManageCashTransferEsDTO financeManageCashTransferEsDTO = esFinanceManageCashTransferService.printCash(req);
        if(financeManageCashTransferEsDTO==null){
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_PRINT_CASH_TRANSFER, "没有打印的转账单");
        }
        financeManagePrintTransferRsepDTO.setTransferOrdersource(financeManageCashTransferEsDTO.getTransferOrdersource());
        financeManagePrintTransferRsepDTO.setTransferType(financeManageCashTransferEsDTO.getTransferType());
        financeManagePrintTransferRsepDTO.setTransferAccount(financeManageCashTransferEsDTO.getTransferAccount());
        financeManagePrintTransferRsepDTO.setIncomeAccount(financeManageCashTransferEsDTO.getIncomeAccount());
        financeManagePrintTransferRsepDTO.setTransferAmount(financeManageCashTransferEsDTO.getTransferAmount());
        financeManagePrintTransferRsepDTO.setRemark(financeManageCashTransferEsDTO.getRemark());
        financeManagePrintTransferRsepDTO.setId(financeManageCashTransferEsDTO.getId());
        financeManagePrintTransferRsepDTO.setTransferStatus(financeManageCashTransferEsDTO.getTransferStatus());
        financeManagePrintTransferRsepDTO.setTransferStatusName(financeManageCashTransferEsDTO.getTransferStatusName());
        financeManagePrintTransferRsepDTO.setIncomeNetworkId(financeManageCashTransferEsDTO.getIncomeNetworkId());
        financeManagePrintTransferRsepDTO.setIncomeNetworkName(financeManageCashTransferEsDTO.getIncomeNetworkName());
        financeManagePrintTransferRsepDTO.setTransferNetworkId(financeManageCashTransferEsDTO.getTransferNetworkId());
        financeManagePrintTransferRsepDTO.setTransferNetworkName(financeManageCashTransferEsDTO.getTransferNetworkName());
        financeManagePrintTransferRsepDTO.setTransferTypeName(financeManageCashTransferEsDTO.getTransferTypeName());
        financeManagePrintTransferRsepDTO.setIncomeAccountBelongId(financeManageCashTransferEsDTO.getIncomeAccountBelongId());
        financeManagePrintTransferRsepDTO.setIncomeAccountBelong(financeManageCashTransferEsDTO.getIncomeAccountBelong());
        financeManagePrintTransferRsepDTO.setTransferAccountBelongId(financeManageCashTransferEsDTO.getTransferAccountBelongId());
        financeManagePrintTransferRsepDTO.setSubmitTime(financeManageCashTransferEsDTO.getSubmitTime());
        financeManagePrintTransferRsepDTO.setCancelSubmitTime(financeManageCashTransferEsDTO.getCancelSubmitTime());
        return financeManagePrintTransferRsepDTO;
    }

    /**
     * 打印明细
     * @param req
     * @return
     */
    @Override
    public List<FinanceManagePrintTransferDteailsRsepDTO> printCashDetails(FinanceManagePrintCashDetails req) {


        List<FinanceManagePrintTransferDteailsRsepDTO> financeManagePrintTransferDteailsRsepDTOList = Lists.newArrayList();
        List<FinanceManageCashSerialEsDTO> financeManageCashSerialEsDTO = esFinanceManageCashSerialService.printCashDetails(req);
        if(financeManageCashSerialEsDTO == null){
            return Lists.newArrayList();
        }
        for (FinanceManageCashSerialEsDTO manageCashSerialEsDTO : financeManageCashSerialEsDTO) {
            FinanceManagePrintTransferDteailsRsepDTO financeManagePrintTransferDteailsRsepDTO = new FinanceManagePrintTransferDteailsRsepDTO();
            EntityUtils.copyProperties(manageCashSerialEsDTO,financeManagePrintTransferDteailsRsepDTO);
            financeManagePrintTransferDteailsRsepDTOList.add(financeManagePrintTransferDteailsRsepDTO);
        }
        return financeManagePrintTransferDteailsRsepDTOList;
    }


    /**
     *@author sql
     *@Date 2018\5\21 0021
     *@param
     *@Describe 转账修改接口：修改主表数据(转账账号、进账账号等)
     */
    private FinanceManageCashTransferDO updateMainTable(FinanceManageCashTransferReqDTO transferReqDTO,FinanceManageCashTransferEsDTO financeManageCashTransferEsDTO){
        FinanceManageCashTransferDO financeManageCashTransferDO = new FinanceManageCashTransferDO();
        FinanceManageCashTransferDO rollbackFinanceManageCashTransferDO = new FinanceManageCashTransferDO();
        EntityUtils.copyProperties(financeManageCashTransferEsDTO,rollbackFinanceManageCashTransferDO);
        Boolean updateTransferDB = false;
        try{
            // 填充转账单实体DB，更新主表信息
            EntityUtils.copyProperties(transferReqDTO,financeManageCashTransferDO);
            financeManageCashTransferDO.setUpdateTime(new Date().getTime());
            financeManageCashTransferDO.setId(transferReqDTO.getSubmitBillId());
            financeManageCashTransferDO.setUpdateUserId(transferReqDTO.getUserId());
            financeManageCashTransferDO.setRemark(transferReqDTO.getRemark());
            updateTransferDB = dbFinanceManageCashTransferService.update(financeManageCashTransferDO);
        } catch (Exception e){
            log.error("转账修改接口：修改主表DB数据异常{}",e.getMessage(),e);
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_SERIAL_UPDATE, "转账:修改接口,修改转账单主表DB数据异常");
        }
        if(!updateTransferDB){
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_TRANSFER_UPDATE, "转账:修改接口,修改转账单主表DB数据条数为0!");
        }

        // 修改ES
        try {
            FinanceManageCashTransferEsDTO cashTransferEsDTO = new FinanceManageCashTransferEsDTO();
            EntityUtils.copyProperties(financeManageCashTransferDO, cashTransferEsDTO);
            cashTransferEsDTO.setTransferTypeName(transferReqDTO.getTransferTypeName());
            cashTransferEsDTO.setUpdateUserName(transferReqDTO.getUserName());
            esFinanceManageCashTransferService.update(cashTransferEsDTO);
        }catch (Exception e){
            // 回滚DB
            dbFinanceManageCashTransferService.update(rollbackFinanceManageCashTransferDO);
            log.error("转账修改接口：修改主表ES数据异常{}",e.getMessage(),e);
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_SERIAL_UPDATE, "转账:修改接口,修改转账单主表ES数据异常");
        }
        return rollbackFinanceManageCashTransferDO;
    }

    /**
     * @param
     * @author sql
     * @Date 2018\5\18 0018
     * @Describe 修改接口，判断有没有移除现金流水单
     */
    private void subtractTransfer(FinanceManageCashTransferReqDTO transferReqDTO, Map<String, Long> reqMap, List<FinanceManageCashSerialEsDTO> financeManageCashSerialEsDTOS
                                , List<FinanceManageCashSerialDO> addFinanceManageCashSerialDOS,
                                  FinanceManageCashTransferDO rollbackFinanceManageCashTransferDO,
                                  FinanceManageCashTransferEsDTO rollBackFinanceManageCashTransferEsDTO) {

        List<FinanceManageSerialUpdateEsDTO> serialUpdateEsDTOList = Lists.newArrayList();
        List<FinanceManageCashSerialDO> financeManageCashSerialDOList = Lists.newArrayList();
        List<Long> ids = Lists.newArrayList();
        for (FinanceManageCashSerialEsDTO cashSerialEsDTO : financeManageCashSerialEsDTOS) {

            if (!reqMap.containsKey(cashSerialEsDTO.getId().toString())) {
                ids.add(cashSerialEsDTO.getId());
                //更新其对应现金流水的转账单号
                //以及交账时间为空，转账状态更新为：未提交
                FinanceManageCashSerialDO financeManageCashSerialDO = new FinanceManageCashSerialDO();
                financeManageCashSerialDO.setCompanyId(transferReqDTO.getCompanyId());
                financeManageCashSerialDO.setSubmitBillState(FinanceTransferStatusEnum.UNTRANSFER.getType());
                financeManageCashSerialDO.setId(cashSerialEsDTO.getId());
                financeManageCashSerialDOList.add(financeManageCashSerialDO);

                FinanceManageSerialUpdateEsDTO financeManageSerialUpdateEsDTO = new FinanceManageSerialUpdateEsDTO();
                EntityUtils.copyProperties(financeManageCashSerialDO, financeManageSerialUpdateEsDTO);
                financeManageSerialUpdateEsDTO.setSubmitBillStateName(FinanceTransferStatusEnum.UNTRANSFER.getName());
                serialUpdateEsDTOList.add(financeManageSerialUpdateEsDTO);
            }

        }
        if (financeManageCashSerialDOList.size() == 0) {
            return;
        }
        // 查询，用于回滚数据
        List<FinanceManageCashSerialDO> financeManageCashSerialDOS = queryCashSerialByIds(ids);
        // 查询用于回滚 增加方法里的数据
        List<FinanceManageSerialUpdateEsDTO> financeManageSerialUpdateEsDTOList = Lists.newArrayList();
        for (FinanceManageCashSerialDO cashSerialDO : addFinanceManageCashSerialDOS) {
            FinanceManageSerialUpdateEsDTO financeManageSerialUpdateEsDTO = new FinanceManageSerialUpdateEsDTO();
            EntityUtils.copyProperties(cashSerialDO, financeManageSerialUpdateEsDTO);
            financeManageSerialUpdateEsDTOList.add(financeManageSerialUpdateEsDTO);
        }
        boolean updateSerialFlag =false;
        try {
            // 更新DB
            updateSerialFlag = dbFinanceManageCashSerialService.updateBatchContainsNull(financeManageCashSerialDOList);
        } catch (Exception e) {
            //回滚
            if(addFinanceManageCashSerialDOS.size()>0){
                dbFinanceManageCashSerialService.updateBatchContainsNull(addFinanceManageCashSerialDOS);
                esFinanceManageCashSerialService.updateBatchByIdContainsNull(financeManageSerialUpdateEsDTOList);
            }
            dbFinanceManageCashTransferService.update(rollbackFinanceManageCashTransferDO);
            esFinanceManageCashTransferService.update(rollBackFinanceManageCashTransferEsDTO);
            log.error("转账:修改接口,移除现金流水单更新现金流水DB异常{}", e.getMessage(), e);
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_SERIAL_UPDATE, "转账:修改接口,移除现金流水单更新现金流水DB异常");
        }
        if (!updateSerialFlag) {
            // 回滚
            if(addFinanceManageCashSerialDOS.size()>0){
                dbFinanceManageCashSerialService.updateBatchContainsNull(addFinanceManageCashSerialDOS);
                esFinanceManageCashSerialService.updateBatchByIdContainsNull(financeManageSerialUpdateEsDTOList);
            }
            dbFinanceManageCashTransferService.update(rollbackFinanceManageCashTransferDO);
            esFinanceManageCashTransferService.update(rollBackFinanceManageCashTransferEsDTO);
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_SERIAL_UPDATE, "转账:修改接口,移除现金流水单:更新现金流水为0");
        }
        // 更新ES
        try {
            boolean updateES = esFinanceManageCashSerialService.updateBatchByIdContainsNull(serialUpdateEsDTOList);
        } catch (Exception e) {
            if(addFinanceManageCashSerialDOS.size()>0){
                dbFinanceManageCashSerialService.updateBatchContainsNull(addFinanceManageCashSerialDOS);
                esFinanceManageCashSerialService.updateBatchByIdContainsNull(financeManageSerialUpdateEsDTOList);
            }
            dbFinanceManageCashTransferService.update(rollbackFinanceManageCashTransferDO);
            esFinanceManageCashTransferService.update(rollBackFinanceManageCashTransferEsDTO);
            dbFinanceManageCashSerialService.updateBatch(financeManageCashSerialDOS);
            log.error("转账:修改接口,移除现金流水单更新现金流水ES异常{}", e.getMessage(), e);
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_SERIAL_UPDATE, "转账:修改接口,移除现金流水单更新现金流水ES异常");
        }

    }


    /**
     * @param
     * @author sql
     * @Date 2018\5\18 0018
     * @Describe 修改接口，判断有没有新添加进来的现金流水单
     */
    private List<FinanceManageCashSerialDO> addTranfer(FinanceManageCashTransferReqDTO transferReqDTO, Map<String, Long> queryMap, List<Long> serialIdList,
                                                       FinanceManageCashTransferDO rollbackFinanceManageCashTransferDO,FinanceManageCashTransferEsDTO rollBackFinanceManageCashTransferEsDTO) {

        List<FinanceManageCashSerialEsDTO> financeManageCashSerialEsDTOList = Lists.newArrayList();
        List<FinanceManageCashSerialDO> financeManageCashSerialDOList = Lists.newArrayList();
        List<Long> ids = Lists.newArrayList();

        for (Long serialId : serialIdList) {
            // 如果查出来的数据不包含
            if (!queryMap.containsKey(serialId.toString())) {
                ids.add(serialId);

                FinanceManageCashSerialDO financeManageCashSerialDO = new FinanceManageCashSerialDO();
                financeManageCashSerialDO.setSubmitBillCode(transferReqDTO.getTransferOrdersource());
                financeManageCashSerialDO.setSubmitBillId(transferReqDTO.getSubmitBillId());
                financeManageCashSerialDO.setSubmitBillTime(new Date().getTime());
                financeManageCashSerialDO.setCompanyId(transferReqDTO.getCompanyId());
                financeManageCashSerialDO.setSubmitBillState(FinanceTransferStatusEnum.UNTRANSFER.getType());
                financeManageCashSerialDO.setId(serialId);
                financeManageCashSerialDOList.add(financeManageCashSerialDO);

                FinanceManageCashSerialEsDTO financeManageCashSerialEsDTO = new FinanceManageCashSerialEsDTO();
                EntityUtils.copyProperties(financeManageCashSerialDO, financeManageCashSerialEsDTO);
                financeManageCashSerialEsDTO.setSubmitBillStateName(FinanceTransferStatusEnum.UNTRANSFER.getName());
                financeManageCashSerialEsDTOList.add(financeManageCashSerialEsDTO);
            }

        }


        if (ids.size() == 0) {// 说明没有新添加进来的单子
            List<FinanceManageCashSerialDO> financeManageCashSerialDOS = Lists.newArrayList();
            return financeManageCashSerialDOS;
        }
        // 查询，用于回滚数据
        List<FinanceManageCashSerialDO> financeManageCashSerialDOS = queryCashSerialByIds(ids);

        // 更新现金流水表
        boolean updateDB = false;
        try {
            updateDB = dbFinanceManageCashSerialService.updateBatch(financeManageCashSerialDOList);
        } catch (Exception e) {
            dbFinanceManageCashTransferService.update(rollbackFinanceManageCashTransferDO);
            esFinanceManageCashTransferService.update(rollBackFinanceManageCashTransferEsDTO);
            log.error("转账:修改接口,更新现金流水单DB方法异常{}", e.getMessage(), e);
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_SERIAL_UPDATE, "转账:修改接口,更新现金流水单方法异常");
        }
        if (!updateDB) {
            dbFinanceManageCashTransferService.update(rollbackFinanceManageCashTransferDO);
            esFinanceManageCashTransferService.update(rollBackFinanceManageCashTransferEsDTO);
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_SERIAL_UPDATE, "转账:修改接口,更新现金流水单数量为0!");
        }
        // 更新ES
        try {
            boolean updateES = esFinanceManageCashSerialService.updateBatch(financeManageCashSerialEsDTOList);
        } catch (Exception e) {
            // 回滚DB
            dbFinanceManageCashTransferService.update(rollbackFinanceManageCashTransferDO);
            esFinanceManageCashTransferService.update(rollBackFinanceManageCashTransferEsDTO);
            financeManageCashSerialMapper.updateBatchContainsNull(financeManageCashSerialDOS);
            log.error("转账:修改接口,更新现金流水单ES方法异常{}", e.getMessage(), e);
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_SERIAL_UPDATE, "转账:修改接口,更新现金流水单ES方法异常");
        }

        return financeManageCashSerialDOS;
    }


    /**
     * @param
     * @author sql
     * @Date 2018\5\19 0019
     * @Describe 查询现金流水数据，用于回滚
     */
    private List<FinanceManageCashSerialDO> queryCashSerialByIds(List<Long> ids) {

        // 查询，用于回滚
        List<FinanceManageCashSerialEsDTO> serialEsDTOList = esFinanceManageCashSerialService.querySerialListByIDS(ids);
        List<FinanceManageCashSerialDO> financeManageCashSerialDOS = Lists.newArrayList();
        for (FinanceManageCashSerialEsDTO serialEsDTO : serialEsDTOList) {
            FinanceManageCashSerialDO financeManageCashSerialDO = new FinanceManageCashSerialDO();
            EntityUtils.copyProperties(serialEsDTO, financeManageCashSerialDO);
            financeManageCashSerialDOS.add(financeManageCashSerialDO);
        }

        return financeManageCashSerialDOS;
    }

    /**
     * @param
     * @author sql
     * @Date 2018\5\18 0018
     * @Describe 删除接口：DB操作，删除转账单和更新现金流水接口
     */
    @ChainedTransaction(mapper = {FinanceManageCashTransferMapper.class, FinanceManageCashSerialMapper.class}, timeout = TransactionConstants.TIME_OUT)
    public boolean deleteAndUpdateDB(Long submitBillId, Long companyId,@RouteParam("FinanceManageCashTransferMapper.companyId") FinanceManageCashTransferEsDTO financeManageCashTransferEsDTO
            ,@RouteParam("FinanceManageCashSerialMapper.companyId") List<FinanceManageCashSerialEsDTO> financeManageCashSerialEsDTOList) {
        // 删除转账单
        boolean deleteTransferDB = false;
        try {
            deleteTransferDB = financeManageCashTransferMapper.deleteByPrimaryKey(companyId, submitBillId) > 0;
        } catch (Exception e) {
            log.error("转账删除接口删除转账单异常{}", e.getMessage(), e);
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_TRANSFER_DELETE, "转账删除接口根据转账单ID：" + submitBillId + " 删除转账单失败！");
        }
        if (!deleteTransferDB) {
            log.error("转账删除接口根据转账单ID：" + submitBillId + " 删除转账单数量为0！");
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_TRANSFER_DELETE, "转账删除接口根据转账单ID：" + submitBillId + " 删除转账单数量为0！");
        }

        // 更新现金流水表
        try {
            FinanceManageCashSerialDO financeManageCashSerialDO = new FinanceManageCashSerialDO();
            financeManageCashSerialDO.setSubmitBillId(submitBillId);
            financeManageCashSerialDO.setCompanyId(companyId);
            financeManageCashSerialDO.setSubmitBillState(FinanceTransferStatusEnum.UNTRANSFER.getType());
            financeManageCashSerialMapper.emptySpecifiedField(financeManageCashSerialDO);
        } catch (Exception e) {
            //回滚DB
            deleteRollbackDB(financeManageCashTransferEsDTO, financeManageCashSerialEsDTOList);
            log.error("转账删除接口更新现金流水异常{}", e.getMessage(), e);
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_SERIAL_UPDATE, "转账删除接口根据转账单ID：" + submitBillId + " 更新现金流水失败！");
        }
        return deleteTransferDB;
    }


    /**
     * @param
     * @author sql
     * @Date 2018\5\16 0016
     * @Describe 入参校验
     */
    private void checkField(FinanceManageCashTransferReqDTO transferAccountsReqDTO) {

        Assert.isNotNull(transferAccountsReqDTO.getTransferOrdersource(), "转账单号不可为空!");
        Assert.isNotNull(transferAccountsReqDTO.getCompanyId(), "公司ID不可为空!");
        Assert.isNotNull(transferAccountsReqDTO.getTransferType(), "转账类型不可为空!");
        Assert.isNotNull(transferAccountsReqDTO.getTransferAccount(), "转账账号不可为空!");
        Assert.isNotNull(transferAccountsReqDTO.getIncomeAccount(), "进账账号不可为空!");
        Assert.isNotNull(transferAccountsReqDTO.getTransferAmount(), "转账金额不可为空!");
        Assert.isNotNull(transferAccountsReqDTO.getSubmitBillId(), "转账单ID不可为空!");

    }


}
