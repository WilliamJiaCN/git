package com.hivescm.tms.api.dto.es.transport.request;

import com.hivescm.tms.api.dto.es.transport.component.TmsTransportDetailDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 单票添加请对象
 *
 * @author 李洪春
 * @since 2017/9/7 8:46
 */
@Data
@ToString
public class QueryWaybillReqDTO implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -2249931820986877269L;

    
    /**
     * 网点ID
     */
    @ApiModelProperty(value = "网点ID", notes = "前端调用时不传")
    private Integer branchId;


    /**
     * 运输批次ID
     */
    @ApiModelProperty("运输批次ID")
    private Long transportId;

    /**
     * 单票添加运单信息
     */
    @ApiModelProperty("单票添加运单信息")
    private String code;

    
}
