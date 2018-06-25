package com.hivescm.tms.api.dto.es.waybill.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.tms.api.dto.es.waybill.WaybillEsDTO;
import com.hivescm.tms.api.dto.es.stock.WaybillGoodsEsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 运单计算费用请求对象
 * @author 杨彭伟
 * @date 2018-02-27 14:21
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class WaybillTotalFee implements Serializable {
    private static final long serialVersionUID = 1L;

    @Mapping
    private List<WaybillGoodsEsDTO> goodsEsDTOList;
    @Mapping
    private WaybillEsDTO waybillEsDTO;
}
