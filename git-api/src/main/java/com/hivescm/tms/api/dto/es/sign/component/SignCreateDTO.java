package com.hivescm.tms.api.dto.es.sign.component;

import com.hivescm.tms.api.dto.es.dispatcher.DispatcherDetailEsDTO;
import com.hivescm.tms.api.dto.es.dispatcher.DispatcherEsDTO;
import com.hivescm.tms.api.dto.es.sign.SignDetailsEsDTO;
import com.hivescm.tms.api.dto.es.sign.SignEsDTO;
import com.hivescm.tms.api.dto.es.sign.SignFeeEsDTO;
import com.hivescm.tms.api.dto.es.sign.SignRefuseEsDTO;
import com.hivescm.tms.api.dto.es.stock.WaybillGoodsEsDTO;
import com.hivescm.tms.api.dto.es.waybill.WaybillEsDTO;
import com.hivescm.tms.api.dto.es.waybill.WaybillFeeEsDTO;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author 杨彭伟
 * @date 2018-01-06 13:47
 */
@Data
@ToString
public class SignCreateDTO {
    Long signId;
    Boolean updateDispacherFlag;
    WaybillEsDTO waybillEsDTO;
    List<WaybillGoodsEsDTO> waybillGoodsEsDTOList;
    List<WaybillFeeEsDTO> waybillFeeEsDTOList;
    // 当前派车单
    DispatcherEsDTO dispatcherEsDTO;
    // 当前派车单明细
    DispatcherDetailEsDTO dispatcherDetailEsDTO;
    SignRefuseEsDTO signRefuseEsDTO;
    SignRefuseEsDTO newSignRefuseEsDTO;
    List<TmsGoodsDetailsEsDTO> refuseSignGoods;
    // 要更新的派车单明细
    DispatcherDetailEsDTO updateDispatcherDetailEsDTO;
    // 要更新的派车单
    DispatcherEsDTO newDispatcherEsDTO;

    SignEsDTO signEsDTO;
    List<SignDetailsEsDTO> signDetailsEsDTOList;
    SignFeeEsDTO signFeeEsDTO;
}
