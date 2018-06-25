package com.hivescm.tms.api.dto.es.handlingorder.response.details;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.tms.api.dto.es.handlingorder.savData.HandlingorderGoodsReqDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Title: HandlingOrderDetailsResDTO</p>
 * <p>Description: </p>
 * <p>Email: syenging@gmail.com</p>
 *
 * @author Jie.Yang
 * @version 1.0
 * @create 2018-05-23-16:50
 */
@ToString
@Data
public class TmsHandlingorderDetailResDTO implements Serializable {

    private static final long serialVersionUID = -5885301369364948721L;

    @Mapping @ApiModelProperty("装卸运单")
    private HandlingorderDetailResDTO handlingorderDetailResDTO;

    @Mapping @ApiModelProperty("装卸货物明细")
    private   List<HandlingorderGoodsReqDTO> goodsList;
}
