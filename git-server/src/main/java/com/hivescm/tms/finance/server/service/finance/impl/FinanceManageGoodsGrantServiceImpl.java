package com.hivescm.tms.finance.server.service.finance.impl;

import com.hivescm.framework.entitymapping.utils.EntityUtils;
import com.hivescm.tms.api.dto.es.finance.FinanceManageGoodsGrantEsDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinanceManageGrantCodeReqDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinanceManageGrantCommonReqDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinanceManageGrantConfirmQueryReqDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinanceManageGrantListReqDTO;
import com.hivescm.tms.api.dto.es.finance.response.FinanceManageGrantListRespDTO;
import com.hivescm.tms.api.enums.finance.ConfirmBillwayStatusEnum;
import com.hivescm.tms.constants.GeneratedIdConstants;
import com.hivescm.tms.finance.server.component.finance.FinanceCommonService;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageGoodsGrantDO;
import com.hivescm.tms.finance.server.service.finance.DbFinanceManageGoodsGrantService;
import com.hivescm.tms.finance.server.service.finance.EsFinanceManageGoodsGrantService;
import com.hivescm.tms.finance.server.service.finance.FinanceManageGoodsGrantService;
import com.hivescm.tms.intranet.gateway.api.feign.IGeneratedIdService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: MaGuoDong
 * @Date: Created in 2018/5/7 15:06
 * @company 蜂网供应链管理（上海）有限公司
 */
@Service
@Slf4j
public class FinanceManageGoodsGrantServiceImpl implements FinanceManageGoodsGrantService {

    @Autowired
    private DbFinanceManageGoodsGrantService dbFinanceManageGoodsGrantService;

    @Autowired
    private EsFinanceManageGoodsGrantService esFinanceManageGoodsGrantService;

    @Autowired
    private IGeneratedIdService generatedIdService;
    @Autowired
    private FinanceCommonService financeCommonService;

    @Override
    public Boolean insert(FinanceManageGoodsGrantEsDTO financeManageGoodsGrantEsDTO) {
        boolean es=false;boolean db=false;
        try{

            FinanceManageGoodsGrantDO financeManageGoodsGrantDO=initFinanceManageGoodsGrantDO(financeManageGoodsGrantEsDTO);
            esFinanceManageGoodsGrantService.insert(financeManageGoodsGrantEsDTO);
            db=dbFinanceManageGoodsGrantService.insert(financeManageGoodsGrantDO);
        }catch (Exception e){
        	e.printStackTrace();
        }
        return db;
    }

    private FinanceManageGoodsGrantDO initFinanceManageGoodsGrantDO(FinanceManageGoodsGrantEsDTO financeManageGoodsGrantEsDTO) {
        FinanceManageGoodsGrantDO financeManageGoodsGrantDO=new FinanceManageGoodsGrantDO();
        financeManageGoodsGrantEsDTO.setId(generatedIdService.getIncrUniqueId(GeneratedIdConstants.TMS_FINANCE_MANAGE_GOODS_GRANT));
        EntityUtils.copyProperties(financeManageGoodsGrantEsDTO,financeManageGoodsGrantDO);
        return financeManageGoodsGrantDO;
    }

    @Override
    public Boolean update(FinanceManageGoodsGrantEsDTO financeManageGoodsGrantEsDTO) {
        boolean es=false;boolean db=false;
        try{
            FinanceManageGoodsGrantDO financeManageGoodsGrantDO=initUpdateFinanceManageGoodsGrantEsDTO(financeManageGoodsGrantEsDTO);
            es=esFinanceManageGoodsGrantService.update(financeManageGoodsGrantEsDTO);
            db=dbFinanceManageGoodsGrantService.update(financeManageGoodsGrantDO);
        }catch (Exception e){

        }
        return db;
    }

    /**
     * 查询货款发放列表
     * @param financeManageGrantListReqDTO
     * @return
     */
    @Override
    public FinanceManageGrantListRespDTO getEsList(FinanceManageGrantListReqDTO financeManageGrantListReqDTO) {
        FinanceManageGrantListRespDTO resp = new FinanceManageGrantListRespDTO();
        List<FinanceManageGoodsGrantEsDTO> esList = esFinanceManageGoodsGrantService.getEsList(financeManageGrantListReqDTO);
        Integer esListCount = esFinanceManageGoodsGrantService.getEsListCount(financeManageGrantListReqDTO);
        resp.setList(esList);
        resp.setTotalNum(esListCount);
        return resp;
    }

    /**
     * 查询发放确认列表
     * @param financeManageGrantConfirmQueryReqDTO
     * @return
     */
    @Override
    public List<FinanceManageGoodsGrantEsDTO> getEsForGrantConfirmList(FinanceManageGrantConfirmQueryReqDTO financeManageGrantConfirmQueryReqDTO) {
        return esFinanceManageGoodsGrantService.getEsForGrantConfirmList(financeManageGrantConfirmQueryReqDTO);
    }

    /**
     * 查询运单号
     * @param financeManageGrantCodeReqDTO
     * @return
     */
    @Override
    public List<FinanceManageGoodsGrantEsDTO> getCodeByGrant(FinanceManageGrantCodeReqDTO financeManageGrantCodeReqDTO) {
        return esFinanceManageGoodsGrantService.getCodeByGrant(financeManageGrantCodeReqDTO);
    }

    /**
     * 通过id快速添加
     * @param id
     * @return
     */
    @Override
    public List<FinanceManageGoodsGrantEsDTO> getEsByCodeForGrant(Long id) {
        return esFinanceManageGoodsGrantService.getEsByCodeForGrant(id);
    }

    @Override
    public Boolean receiveConfirm(FinanceManageGrantCommonReqDTO financeManageGrantCommonReqDto) {
        //操作时间
        Long receiveConfirmTime = Instant.now().toEpochMilli();

        List<FinanceManageGoodsGrantDO> financeManageGoodsGrantDoList = new ArrayList<>();
        List<FinanceManageGoodsGrantEsDTO> financeManageGoodsGrantEsDtoList = esFinanceManageGoodsGrantService.getEsList(financeManageGrantCommonReqDto.getIds());
        if (CollectionUtils.isNotEmpty(financeManageGoodsGrantEsDtoList)) {
            for (FinanceManageGoodsGrantEsDTO financeManageGoodsGrantEsDto : financeManageGoodsGrantEsDtoList) {
                financeManageGoodsGrantEsDto.setConfirmBillwayStatus(ConfirmBillwayStatusEnum.RECEIVED.getType());
                financeManageGoodsGrantEsDto.setConfirmBillwayStatusName(ConfirmBillwayStatusEnum.RECEIVED.getName());
                financeManageGoodsGrantEsDto.setConfirmBillwayUserId(financeManageGrantCommonReqDto.getOprId());
                financeManageGoodsGrantEsDto.setConfirmBillwayUserName(financeManageGrantCommonReqDto.getOprName());
                financeManageGoodsGrantEsDto.setConfirmBillwayTime(receiveConfirmTime);
                FinanceManageGoodsGrantDO financeManageGoodsGrantDo = EntityUtils.copyProperties(financeManageGoodsGrantEsDto, FinanceManageGoodsGrantDO.class);
                financeManageGoodsGrantDoList.add(financeManageGoodsGrantDo);
            }
        }
        //更新DB
        boolean db = dbFinanceManageGoodsGrantService.updateBatch(financeManageGoodsGrantDoList);
        //更新ES
        boolean es = esFinanceManageGoodsGrantService.updateBatchEs(financeManageGoodsGrantEsDtoList);

        return db && es;
    }

    private FinanceManageGoodsGrantDO initUpdateFinanceManageGoodsGrantEsDTO(FinanceManageGoodsGrantEsDTO financeManageGoodsGrantEsDTO) {
        FinanceManageGoodsGrantDO financeManageGoodsGrantDO=new FinanceManageGoodsGrantDO();
        EntityUtils.copyProperties(financeManageGoodsGrantEsDTO,financeManageGoodsGrantDO);
        return financeManageGoodsGrantDO;
    }
}
