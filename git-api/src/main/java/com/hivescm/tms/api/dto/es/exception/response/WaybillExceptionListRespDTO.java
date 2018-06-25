package com.hivescm.tms.api.dto.es.exception.response;

import com.hivescm.tms.api.dto.es.exception.WaybillExceptionEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class WaybillExceptionListRespDTO implements Serializable{
    private static final long serialVersionUID = 5078233026046547410L;
    @ApiModelProperty("总条数")
    private Integer totalNum;
    @ApiModelProperty("异常列表")
    private List<WaybillExceptionEsDTO> list;
}
