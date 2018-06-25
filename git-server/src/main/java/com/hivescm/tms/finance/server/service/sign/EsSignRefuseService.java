package com.hivescm.tms.finance.server.service.sign;

import com.hivescm.tms.api.dto.es.sign.SignRefuseEsDTO;

import java.util.List;

/**
 * @author 杨彭伟
 * @date 2018-01-29 16:12
 */
public interface EsSignRefuseService {
    /**
     * es中插入拒收单主表信息
     *
     * @param SignRefuseEsDTO 拒收单信息
     * @return 插入结果
     */
    Boolean insertSignRefuseEsDTO(SignRefuseEsDTO SignRefuseEsDTO);

    /**
     * 更新拒收单信息
     *
     * @param SignRefuseEsDTO 拒收单信息
     * @return Boolean
     */
    Boolean updateSignRefuseEsDTO(SignRefuseEsDTO SignRefuseEsDTO);
   /**
    * 批量更新拒收单
    * @param SignRefuseEsDTOs
    * @return
    */
    Boolean updateBatchSignRefuseEsDTO(List<SignRefuseEsDTO> signRefuseEsDTOs);


    /**
     * 删除拒收单信息
     *
     * @param id 拒收单ID
     * @return Boolean
     */
    Boolean deleteSignRefuseEsDTO(Long id);

    /**
     * 根据signId删除拒收单
     * @param signId
     * @return
     */
    Boolean deleteSignRefuseEsDTOBySignId(Long signId);

    /**
     * 根据signIds集合删除拒收单
     * @param signIds
     * @return
     */
    Boolean deleteSignRefuseEsDTOBySignIds(List<Long> signIds);


    /**
     * 获取拒收单信息
     *
     * @param id 拒收单ID
     * @return SignRefuseEsDTO
     */
    SignRefuseEsDTO getSignRefuseEsDTO(Long id);

    /**
     * 根据运单id查询拒收单信息
     * @param waybillId 运单id
     * @return SignRefuseEsDTO
     */
    SignRefuseEsDTO getSignRefuseEsDTOByWaybillId(Long waybillId);

    SignRefuseEsDTO getSignRefuseEsDTOByDispatcherDetailId(Long dispatcherDetailId);
    
    /**
     * 批量插入拒签表
     * @param SignRefuseEsDTO
     * @return
     */
    Boolean insertBatchSignRefuseEsDTO(List<SignRefuseEsDTO> SignRefuseEsDTOs);

    /**
     * 根据签收id查询签收单
     * @param signIds
     * @return
     */
    List<SignRefuseEsDTO> queryRefuseSignBySignIds(List<Long> signIds);


    /**
     * 根据签收id查询签收单
     * @param signId
     * @return
     */
    SignRefuseEsDTO queryRefuseSignBySignId(Long signId);
}
