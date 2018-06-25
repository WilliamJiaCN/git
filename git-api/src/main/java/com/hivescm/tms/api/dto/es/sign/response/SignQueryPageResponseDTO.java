package com.hivescm.tms.api.dto.es.sign.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 送货签收列表查询响应对象
 * @author 杨彭伟
 * @date 2017-12-07 19:28
 */
@Data
@ToString
public class SignQueryPageResponseDTO implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1244274286695080984L;

    @ApiModelProperty("总条数")
    private Integer totalNum;
    @ApiModelProperty("分页数据")
    private List<SignQueryResponseDTO> list;

}
