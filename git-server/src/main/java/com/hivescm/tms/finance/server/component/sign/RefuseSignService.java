package com.hivescm.tms.finance.server.component.sign;

import com.hivescm.common.domain.DataResult;
import com.hivescm.tms.api.dto.es.sign.SignRefuseEsDTO;
import com.hivescm.tms.api.dto.es.sign.component.TmsGoodsDetailsEsDTO;
import com.hivescm.tms.api.dto.es.sign.request.RefuseForSignReqDTO;
import com.hivescm.tms.api.dto.es.sign.request.RefuseSignNoticeReqDTO;
import com.hivescm.tms.api.dto.es.sign.request.RefuseSignQueryReqDTO;

import java.util.List;

/**
 * 签收时拒绝签收
 *
 * @author 杨彭伟
 * @since 2017/11/8 11:20
 */
public interface RefuseSignService {

    /**
     * 通知拒绝签收结果
     * @param refuseSignNoticeReqDTO
     * @return
     */
    DataResult<Boolean> updateRefuseSign(RefuseSignNoticeReqDTO refuseSignNoticeReqDTO);

    /**
     * 根据拒收单id查询拒收单拒收金额
     * @param refuseId 拒收单id
     * @return
     */
    DataResult getRefuseSignAmount(Long refuseId);

    /**
     * 查询拒收单列表
     * @param refuseSignQueryReqDTO 拒收单查询请求实体
     * @return
     */
    List<SignRefuseEsDTO> getRefuseSignList(RefuseSignQueryReqDTO refuseSignQueryReqDTO);

    /**
     * 查询拒收单商品详情
     * @param refuseId 拒收单id
     * @return
     */
    DataResult<List<TmsGoodsDetailsEsDTO>> getRefuseSignGoodsDetails(Long refuseId);

    /**
     * 修改拒收单为作废状态
     * @param waybillCode 编号
     * @return
     */
    DataResult<Boolean> updateRefuseSignToCancel(String waybillCode,Long companyId);

    DataResult test();

	long getCount(RefuseSignQueryReqDTO refuseSignQueryReqDTO);

	Integer insertRefuseSignNew(RefuseForSignReqDTO refuseForSignReqDTO);
}
