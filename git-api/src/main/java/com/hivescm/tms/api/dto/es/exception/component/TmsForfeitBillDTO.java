package com.hivescm.tms.api.dto.es.exception.component;

import com.hivescm.tms.api.dto.es.exception.ForfeitBillDetailEsDTO;
import com.hivescm.tms.api.dto.es.exception.ForfeitBillEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 罚款单信息
 *
 * @author 李洪春
 * @since 2017/10/10 10:30
 */
@Data
@ToString
public class TmsForfeitBillDTO implements Serializable {
    private static final long serialVersionUID = 4371267178421452195L;

    /**
     * 罚款单基本信息
     */
    @ApiModelProperty("罚款单基本信息")
    private ForfeitBillEsDTO forfeitBill;

    /**
     * 罚款单明细信息
     */
    @ApiModelProperty("罚款单明细信息")
    private List<ForfeitBillDetailEsDTO> forfeitBillDetailList;
    
}
