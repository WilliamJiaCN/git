package com.hivescm.tms.api.dto.es.dispatcher.component;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.hivescm.tms.api.dto.es.dispatcher.DispatcherDetailEsDTO;
import com.hivescm.tms.api.dto.es.dispatcher.DispatcherGoodsEsDTO;

/**
 * 派车单明细信息封装，
 * 包含运单和货物信息
 *
 * @author 李洪春
 * @since 2017/8/21 11:59
 */
@Data
@ToString
public class TmsDispatcherDetailEsDTO implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 4685655434932417574L;

    /**
     * 派车单明细
     */
    @ApiModelProperty("派车单明细信息")
    private DispatcherDetailEsDTO dispatcherDetailEsDTO;

    /**
     * 货物明细信息
     */
    @ApiModelProperty("货物明细信息")
    private List<DispatcherGoodsEsDTO> dispatcherGoodsEsDTOList;

    /**
     *
     */
    public TmsDispatcherDetailEsDTO() {
    }

    /**
     * 构造方法
     *
     * @param dispatcherDetailEsDTO
     */
    public TmsDispatcherDetailEsDTO(DispatcherDetailEsDTO dispatcherDetailEsDTO) {
        this.dispatcherDetailEsDTO = dispatcherDetailEsDTO;
    }

    /**
     * 添加货物信息
     *
     * @param dispatcherGoodsEsDTO 货物信息
     */
    public void addDispatcherGoodsEsDTO(DispatcherGoodsEsDTO dispatcherGoodsEsDTO) {
        if (null == dispatcherGoodsEsDTOList) {
            dispatcherGoodsEsDTOList = new ArrayList<>();
        }
        dispatcherGoodsEsDTOList.add(dispatcherGoodsEsDTO);

    }


}
