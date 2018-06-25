package com.hivescm.tms.finance.server.service.finance;

import com.hivescm.tms.api.dto.es.finance.FinanceManageGoodsGrantEsDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinanceManageGrantCodeReqDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinanceManageGrantCommonReqDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinanceManageGrantConfirmQueryReqDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinanceManageGrantListReqDTO;
import com.hivescm.tms.api.dto.es.finance.response.FinanceManageGrantListRespDTO;

import java.util.List;

/**
 * @Description:
 * @Author: MaGuoDong
 * @Date: Created in 2018/5/7 15:04
 * @company 蜂网供应链管理（上海）有限公司
 */
public interface FinanceManageGoodsGrantService {
    /**
     * 新增货款回收
     * @param financeManageGoodsGrantEsDTO
     * @return
     */
    Boolean insert(FinanceManageGoodsGrantEsDTO financeManageGoodsGrantEsDTO);

    /**
     * 更新货款回收
     * @param financeManageGoodsGrantEsDTO
     * @return
     */
    Boolean update(FinanceManageGoodsGrantEsDTO financeManageGoodsGrantEsDTO);

    /**
     * 查询货款发放列表
     * @param financeManageGrantListReqDTO
     * @return
     */
    FinanceManageGrantListRespDTO getEsList(FinanceManageGrantListReqDTO financeManageGrantListReqDTO);

    /**
     * 查询发放确认列表
     * @param financeManageGrantConfirmQueryReqDTO
     * @return
     */
    List<FinanceManageGoodsGrantEsDTO> getEsForGrantConfirmList(FinanceManageGrantConfirmQueryReqDTO financeManageGrantConfirmQueryReqDTO);
    /**
     * 查询运单号
     * @param financeManageGrantCodeReqDTO
     * @return
     */
    List<FinanceManageGoodsGrantEsDTO> getCodeByGrant(FinanceManageGrantCodeReqDTO financeManageGrantCodeReqDTO);

    /**
     * 根据id快速添加
     * @param id
     * @return
     */
    List<FinanceManageGoodsGrantEsDTO> getEsByCodeForGrant(Long id);

    /**
     * 收单确认
     *
     * @param financeManageGrantCommonReqDto
     * @return
     */
    Boolean receiveConfirm(FinanceManageGrantCommonReqDTO financeManageGrantCommonReqDto);
}
