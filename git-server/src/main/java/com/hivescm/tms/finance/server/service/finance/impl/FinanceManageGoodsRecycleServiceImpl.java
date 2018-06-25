package com.hivescm.tms.finance.server.service.finance.impl;

import com.hivescm.framework.entitymapping.utils.EntityUtils;
import com.hivescm.tms.api.dto.es.finance.FinanceManageGoodsRecycleEsDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinanceManageRecycleCodeReqDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinanceManageRecycleConfirmQueryReqDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinanceManageRecycleListReqDTO;
import com.hivescm.tms.api.dto.es.finance.response.FinanceManageRecycleListRespDTO;
import com.hivescm.tms.constants.GeneratedIdConstants;
import com.hivescm.tms.constants.TransactionConstants;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageGoodsRecycleDO;
import com.hivescm.tms.finance.server.dao.mapper.finance.FinanceManageGoodsRecycleMapper;
import com.hivescm.tms.finance.server.dao.mapper.finance.FinanceManagePayMapper;
import com.hivescm.tms.finance.server.service.finance.DbFinanceManageGoodsRecycleService;
import com.hivescm.tms.finance.server.service.finance.EsFinanceManageGoodsRecycleService;
import com.hivescm.tms.finance.server.service.finance.FinanceManageGoodsRecycleService;
import com.hivescm.tms.intranet.gateway.api.feign.IGeneratedIdService;
import com.mogujie.distributed.transction.ChainedTransaction;
import com.mogujie.distributed.transction.RouteParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:货款回收
 * @Author: MaGuoDong
 * @Date: Created in 2018/5/7 15:05
 * @company 蜂网供应链管理（上海）有限公司
 */
@Service
@Slf4j
public class FinanceManageGoodsRecycleServiceImpl implements FinanceManageGoodsRecycleService {

    @Autowired
    private DbFinanceManageGoodsRecycleService dbFinanceManageGoodsRecycleService;

    @Autowired
    private EsFinanceManageGoodsRecycleService esFinanceManageGoodsRecycleService;

    @Autowired
    private IGeneratedIdService generatedIdService;
    @Autowired
    private FinanceManageGoodsRecycleMapper financeManageGoodsRecycleMapper;


    @Override
    public Boolean insert(FinanceManageGoodsRecycleEsDTO financeManageGoodsRecycleEsDTO) {
        boolean es=false;boolean db=false;
        try{
            FinanceManageGoodsRecycleDO financeManageGoodsRecycleDO=initFinanceManageGoodsRecycleEsDTO(financeManageGoodsRecycleEsDTO);
            es=esFinanceManageGoodsRecycleService.insert(financeManageGoodsRecycleEsDTO);
            db=dbFinanceManageGoodsRecycleService.insert(financeManageGoodsRecycleDO);
        }catch (Exception e){
        	e.printStackTrace();
        }
        return db;
    }

    private FinanceManageGoodsRecycleDO initFinanceManageGoodsRecycleEsDTO(FinanceManageGoodsRecycleEsDTO financeManageGoodsRecycleEsDTO) {
        FinanceManageGoodsRecycleDO financeManageGoodsRecycleDO=new FinanceManageGoodsRecycleDO();
        financeManageGoodsRecycleEsDTO.setId(generatedIdService.getIncrUniqueId(GeneratedIdConstants.TMS_FINANCE_MANAGE_GOODS_RECYCLE));
        EntityUtils.copyProperties(financeManageGoodsRecycleEsDTO,financeManageGoodsRecycleDO);
        return financeManageGoodsRecycleDO;
    }

    @Override
    public Boolean update(FinanceManageGoodsRecycleEsDTO financeManageGoodsRecycleEsDTO) {
        boolean es=false;boolean db=false;
        try{
            FinanceManageGoodsRecycleDO financeManageGoodsRecycle=initUpdateFinanceManageGoodsRecycleEsDTO(financeManageGoodsRecycleEsDTO);
            es=esFinanceManageGoodsRecycleService.update(financeManageGoodsRecycleEsDTO);
            db=dbFinanceManageGoodsRecycleService.update(financeManageGoodsRecycle);
        }catch (Exception e){

        }
        return db;
    }

    /**
     * 查询货款回收列表
     * @param financeManageRecycleListReqDTO
     * @return
     */
    @Override
    public FinanceManageRecycleListRespDTO getEsList(FinanceManageRecycleListReqDTO financeManageRecycleListReqDTO) {
        FinanceManageRecycleListRespDTO resp = new FinanceManageRecycleListRespDTO();
        List<FinanceManageGoodsRecycleEsDTO> esList = esFinanceManageGoodsRecycleService.getEsList(financeManageRecycleListReqDTO);
        Integer esListCount = esFinanceManageGoodsRecycleService.getEsListCount(financeManageRecycleListReqDTO);
        resp.setList(esList);
        resp.setTotalNum(esListCount);
        return resp;
    }

    /**
     * 查询回收确认列表
     * @param financeManageRecycleConfirmQueryReqDTO
     * @return
     */
    @Override
    public List<FinanceManageGoodsRecycleEsDTO> getEsForRecyckeConfirmList(FinanceManageRecycleConfirmQueryReqDTO financeManageRecycleConfirmQueryReqDTO) {
        return esFinanceManageGoodsRecycleService.getEsForRecyckeConfirmList(financeManageRecycleConfirmQueryReqDTO);
    }

    /**
     * 查询运单号
     * @param financeManageRecycleCodeReqDTO
     * @return
     */
    @Override
    public List<FinanceManageGoodsRecycleEsDTO> getCodeByRecycle(FinanceManageRecycleCodeReqDTO financeManageRecycleCodeReqDTO) {
        return esFinanceManageGoodsRecycleService.getCodeByRecycle(financeManageRecycleCodeReqDTO);
    }

    /**
     * 根据id快速添加
     * @param id
     * @return
     */
    @Override
    public List<FinanceManageGoodsRecycleEsDTO> getEsByCodeForRecycle(Long id) {
        return esFinanceManageGoodsRecycleService.getEsByCodeForRecycle(id);
    }

    @Override
    public FinanceManageGoodsRecycleEsDTO getEsByWaybillIdForRecycle(Long waybillId) {
        return esFinanceManageGoodsRecycleService.findByWaybillId(waybillId);
    }

    @Override
    @ChainedTransaction(mapper = {FinanceManageGoodsRecycleMapper.class}, timeout = TransactionConstants.TIME_OUT)
    public Boolean deleteGoodsRecycleInfo(@RouteParam("FinanceManageGoodsRecycleMapper.companyId") FinanceManageGoodsRecycleDO financeManageGoodsRecycleDo, Long waybillId) {
        try {
            financeManageGoodsRecycleMapper.deleteByWaybillId(financeManageGoodsRecycleDo);
            esFinanceManageGoodsRecycleService.deleteEsByWaybillId(waybillId);
            return true;
        } catch (Exception e) {
            log.error("操作数据库或ES异常");
            throw e;
        }
    }

    private FinanceManageGoodsRecycleDO initUpdateFinanceManageGoodsRecycleEsDTO(FinanceManageGoodsRecycleEsDTO financeManageGoodsRecycleEsDTO) {
        FinanceManageGoodsRecycleDO financeManageGoodsRecycleDO=new FinanceManageGoodsRecycleDO();
        EntityUtils.copyProperties(financeManageGoodsRecycleEsDTO,financeManageGoodsRecycleDO);
        return financeManageGoodsRecycleDO;
    }

}
