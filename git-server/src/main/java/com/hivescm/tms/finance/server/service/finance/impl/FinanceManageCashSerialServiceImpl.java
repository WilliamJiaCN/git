package com.hivescm.tms.finance.server.service.finance.impl;

import com.hivescm.framework.common.exception.ExceptionFactory;
import com.hivescm.framework.entitymapping.utils.EntityUtils;
import com.hivescm.tms.api.dto.es.finance.FinanceManageCashSerialEsDTO;
import com.hivescm.tms.api.dto.es.finance.request.CashSerialRecordReqDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinanceManageCashSerialListReqDTO;
import com.hivescm.tms.api.dto.es.finance.response.FinanceManageCashSerialListRespDTO;
import com.hivescm.tms.api.enums.finance.FinanceIdEnum;
import com.hivescm.tms.api.enums.finance.FlowStatusEnum;
import com.hivescm.tms.api.enums.finance.PayWayEnum;
import com.hivescm.tms.constants.ExceptionCodeConstants;
import com.hivescm.tms.constants.GeneratedIdConstants;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageCashSerialDO;
import com.hivescm.tms.finance.server.service.finance.DbFinanceManageCashSerialService;
import com.hivescm.tms.finance.server.service.finance.EsFinanceManageCashSerialService;
import com.hivescm.tms.finance.server.service.finance.FinanceManageCashSerialService;
import com.hivescm.tms.intranet.gateway.api.feign.IGeneratedIdService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Description:
 * @Author: MaGuoDong
 * @Date: Created in 2018/5/4 17:55
 * @company 蜂网供应链管理（上海）有限公司
 */
@Service
@Slf4j
public class FinanceManageCashSerialServiceImpl implements FinanceManageCashSerialService {

    @Autowired
    private DbFinanceManageCashSerialService dbFinanceManageCashSerialService;

    @Autowired
    private EsFinanceManageCashSerialService esFinanceManageCashSerialService;

    /**
     * id 生成器
     */
    @Autowired
    private IGeneratedIdService generatedIdService;


    @Override
    public Boolean insert(FinanceManageCashSerialEsDTO financeManageCashSerialEsDTO) {
        boolean es=false;boolean db=false;
        try{
            FinanceManageCashSerialDO financeManageCashSerialDO=initFinanceManageCashSerialDO(financeManageCashSerialEsDTO);
            es=esFinanceManageCashSerialService.insert(financeManageCashSerialEsDTO);
            db=dbFinanceManageCashSerialService.insert(financeManageCashSerialDO);
        }catch (Exception e){
            if(es){
                esFinanceManageCashSerialService.deleteById(financeManageCashSerialEsDTO.getId());
            }
            throw ExceptionFactory.ex(ExceptionCodeConstants.ERROR_FINANCE_MANAGE_CASH_INSERT,e.getMessage());
        }
        return db;
    }


    /**
     * 现金流水修改
     * @param financeManageCashSerialEsDTO
     * @return
     */
    @Override
    public Boolean updateEs(FinanceManageCashSerialEsDTO financeManageCashSerialEsDTO) {
        return null;
    }

    @Override
    public FinanceManageCashSerialEsDTO findById(Long id) {
        return esFinanceManageCashSerialService.findById(id);
    }

    @Override
    public Boolean updateStatusById(Long id, Integer status,Long companyId) {
        boolean es=false;boolean db=false;
        try{
            es=esFinanceManageCashSerialService.updateStatusById(id,status);
            FinanceManageCashSerialDO financeManageCashSerialDO=initFinanceManageCashSerialDO(id,status,companyId);
            db=dbFinanceManageCashSerialService.updateById(financeManageCashSerialDO);
        }catch (Exception e){
            if(es){

            }
        }
        return db;
    }

    /**
     * 根据应收或者应付ID查询资金记录
     * @param id
     * @return
     */
    @Override
    public List<FinanceManageCashSerialEsDTO> checkFundRecord(CashSerialRecordReqDTO cashSerialRecordReqDTO) {
    	if(cashSerialRecordReqDTO.getPayWay()!=null&&cashSerialRecordReqDTO.getPayWay()==PayWayEnum.PAYMENTDUCTION.getType()) {
    		List<FinanceManageCashSerialEsDTO> listPayDuction = esFinanceManageCashSerialService.checkFundRecordByCode(cashSerialRecordReqDTO);
    		if(FinanceIdEnum.FINANCEGOODSGRANT.getType()==cashSerialRecordReqDTO.getType()) {
    			List<FinanceManageCashSerialEsDTO> list = esFinanceManageCashSerialService.checkFundRecordById(cashSerialRecordReqDTO);
    			if(listPayDuction==null) {
    				listPayDuction = list;
    			}else {
    				if(list!=null) {
    					listPayDuction.addAll(list);
    					listPayDuction.sort((h1,h2)->h2.getCreateTime().compareTo(h1.getCreateTime()));
    				}
    			}
    			return listPayDuction;
    		}
    		return listPayDuction;
    	}else {
    		return esFinanceManageCashSerialService.checkFundRecordById(cashSerialRecordReqDTO);
    	}
    }

    /**
     * 查询现金流水列表
     * @param financeManageCashSerialListReqDTO
     * @return
     */
    @Override
    public FinanceManageCashSerialListRespDTO getCashSerialEsList(FinanceManageCashSerialListReqDTO financeManageCashSerialListReqDTO) {
        FinanceManageCashSerialListRespDTO resp = new FinanceManageCashSerialListRespDTO();
        List<FinanceManageCashSerialEsDTO> esList = esFinanceManageCashSerialService.getCashSerialEsList(financeManageCashSerialListReqDTO);
        BigDecimal oldBalance = esFinanceManageCashSerialService.getOldBalance(financeManageCashSerialListReqDTO);
        Integer esListCount = esFinanceManageCashSerialService.getEsListCount(financeManageCashSerialListReqDTO);
        resp.setList(esList);
        resp.setOldBalance(oldBalance);
        resp.setTotalNum(esListCount);
        return resp;
    }

    private FinanceManageCashSerialDO initFinanceManageCashSerialDO(Long id, Integer status, Long companyId) {
        FinanceManageCashSerialDO financeManageCashSerialDO=new FinanceManageCashSerialDO();
        financeManageCashSerialDO.setId(id);
        financeManageCashSerialDO.setStatus(status);
        financeManageCashSerialDO.setCompanyId(companyId);
        return financeManageCashSerialDO;
    }

    /**
     * 初始化数据
     * @param financeManageCashSerialEsDTO
     * @return
     */
    private FinanceManageCashSerialDO initFinanceManageCashSerialDO(FinanceManageCashSerialEsDTO financeManageCashSerialEsDTO) {
        financeManageCashSerialEsDTO.setId(generatedIdService.getIncrUniqueId(GeneratedIdConstants.TMS_FINANCE_MANAGE_CASH_SERIAL));
        financeManageCashSerialEsDTO.setStatus(FlowStatusEnum.NORMAL.getType());//正常
        financeManageCashSerialEsDTO.setCreateTime(System.currentTimeMillis());
        FinanceManageCashSerialDO financeManageCashSerialDO=new FinanceManageCashSerialDO();
        EntityUtils.copyProperties(financeManageCashSerialEsDTO,financeManageCashSerialDO);
        return financeManageCashSerialDO;
    }
}
