package com.hivescm.tms.finance.server.service.finance;

import com.hivescm.tms.api.dto.es.finance.FinanceManageGoodsGrantEsDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinanceManageGrantCodeReqDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinanceManageGrantConfirmQueryReqDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinanceManageGrantListReqDTO;

import java.util.List;

/**
 * @Description:
 * @Author: MaGuoDong
 * @Date: Created in 2018/5/7 15:29
 * @company 蜂网供应链管理（上海）有限公司
 */
public interface EsFinanceManageGoodsGrantService {

    boolean insert(FinanceManageGoodsGrantEsDTO financeManageGoodsGrantEsDTO);
    
    boolean insertBatch(List<FinanceManageGoodsGrantEsDTO> financeManageGoodsGrantList);

    boolean update(FinanceManageGoodsGrantEsDTO financeManageGoodsGrantEsDTO);
    
    boolean updateBatch(List<FinanceManageGoodsGrantEsDTO> financeManageGoodsGrantList);

    /**
     * 根据主键获取货款发放信息
     *
     * @param id
     * @return
     */
    FinanceManageGoodsGrantEsDTO getById(Long id);
    /**
     * 查询货款发放列表
     * @param financeManageGrantListReqDTO
     * @return
     */
    List<FinanceManageGoodsGrantEsDTO> getEsList(FinanceManageGrantListReqDTO financeManageGrantListReqDTO);

    /**
     * 查询总数
     * @param financeManageGrantListReqDTO
     * @return
     */
    Integer getEsListCount(FinanceManageGrantListReqDTO financeManageGrantListReqDTO);

    /**
     * 通过来源单号查询
     * @param orderSourceCode
     * @return
     */
	FinanceManageGoodsGrantEsDTO findByCode(String orderSourceCode);
	
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
     * 查询货款发放列表
     * @param orderSourceCodes
     * @return
     */
    List<FinanceManageGoodsGrantEsDTO> getEsList(List<Long> ids);
    /**
     * 批量更新
     *
     * @param financeManageGoodsGrantEsDtoList
     * @return
     */
    Boolean updateBatchEs(List<FinanceManageGoodsGrantEsDTO> financeManageGoodsGrantEsDtoList);

    /**
     * 通过运单ID查找货款发放记录
     * @param waybillId
     * @return
     */
	FinanceManageGoodsGrantEsDTO findByWaybillId(Long waybillId);

	/**
	 * 删除发放记录
	 * @param financeManageGoodsGrantEsDTO
	 * @return
	 */
	Boolean delete(Long id);
	/**
	 * 根据ID删除货款发放信息
     *
     * @param id
     * @return
     */
    Boolean deleteById(Long id);

    List<FinanceManageGoodsGrantEsDTO> findByWaybillIdList(List<Long> transportIdList);

    /**
     * 批量删除
     * @param idGrant
     * @return
     */
	Boolean deleteBatch(List<Long> idGrant);
}
