package com.hivescm.tms.finance.server.service.sign;

import com.hivescm.tms.api.dto.es.sign.SignEsDTO;
import com.hivescm.tms.api.dto.es.sign.component.TmsGoodsDetailsEsDTO;

import java.util.List;

/**
 *
 * @author 杨彭伟
 * @date 2018-01-30 11:17
 */
public interface EsSignRefuseDetailService {
    List<TmsGoodsDetailsEsDTO> queryRefuseSignGoodsEsDTOList(Long waybillId);

    /**
     * 查询拒收单货物明细（全部拒收、部分签收）
     *
     * @param waybillId
     * @param signEsDTO
     * @return
     */
    List<TmsGoodsDetailsEsDTO> queryRefuseSignGoodsEsDTOList(Long waybillId, SignEsDTO signEsDTO);

    /**
     * 删除拒收单货物明细信息
     *
     * @param refuseId 拒收单ID
     * @return Boolean
     */
    Boolean deleteTmsGoodsDetailsEsDTOByRefuseId(Long refuseId);

    /**
     * 批量插入拒收单货物明细信息
     *
     * @param tmsGoodsDetailsEsDTOList 拒收单货物明细列表
     * @return Boolean
     */
    Boolean insertTmsGoodsDetailsEsDTOList(List<TmsGoodsDetailsEsDTO> tmsGoodsDetailsEsDTOList);

}
