package com.hivescm.tms.finance.server.service.finance;

import com.hivescm.tms.api.dto.es.finance.FinanceManageGoodsRecycleEsDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinanceManageRecycleCodeReqDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinanceManageRecycleConfirmQueryReqDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinanceManageRecycleListReqDTO;
import com.hivescm.tms.api.dto.es.finance.response.FinanceManageRecycleListRespDTO;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageGoodsRecycleDO;

import java.util.List;

/**
 * @Description:
 * @Author: MaGuoDong
 * @Date: Created in 2018/5/7 15:05
 * @company 蜂网供应链管理（上海）有限公司
 */
public interface FinanceManageGoodsRecycleService {

    /**
     * 新增货款回收
     * @param financeManageGoodsRecycleEsDTO
     * @return
     */
    Boolean insert(FinanceManageGoodsRecycleEsDTO financeManageGoodsRecycleEsDTO);

    /**
     * 更新货款回收
     * @param financeManageGoodsRecycleEsDTO
     * @return
     */
    Boolean update(FinanceManageGoodsRecycleEsDTO financeManageGoodsRecycleEsDTO);

    /**
     * 查询货款回收列表
     * @param financeManageRecycleListReqDTO
     * @return
     */
    FinanceManageRecycleListRespDTO getEsList(FinanceManageRecycleListReqDTO financeManageRecycleListReqDTO);

   /**
     * 查询回收确认列表
     * @param financeManageRecycleConfirmQueryReqDTO
     * @return
     */
    List<FinanceManageGoodsRecycleEsDTO> getEsForRecyckeConfirmList(FinanceManageRecycleConfirmQueryReqDTO financeManageRecycleConfirmQueryReqDTO);
    /**
     * 查询运单号
     * @param financeManageRecycleCodeReqDTO
     * @return
     */
    List<FinanceManageGoodsRecycleEsDTO> getCodeByRecycle(FinanceManageRecycleCodeReqDTO financeManageRecycleCodeReqDTO);

    /**
     * 根据id快速添加
     * @param id
     * @return
     */
    List<FinanceManageGoodsRecycleEsDTO> getEsByCodeForRecycle(Long id);

    /**
     * 根据运单id查询
     * @param id
     * @return
     */
    FinanceManageGoodsRecycleEsDTO getEsByWaybillIdForRecycle(Long id);

    /**
     * 根据运单ID删除货款回收信息
     *
     * @param financeManageGoodsRecycleDo
     * @param waybillId
     * @return
     */
    Boolean deleteGoodsRecycleInfo(FinanceManageGoodsRecycleDO financeManageGoodsRecycleDo, Long waybillId);
}
