package com.hivescm.tms.api.dto.es.waybill.redundancy;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

import com.hivescm.framework.entitymapping.annotation.Mapping;

/**
 * 运单状态查询结果
 *
 * @author 李洪春
 * @since 2017/9/4 17:02
 */
@Data
@ToString
public class WaybillStatusEsDTO implements Serializable {

    private static final long serialVersionUID = 7633680390647121761L;
    /**
     * 主键
     */
    @Mapping
    @ApiModelProperty("主键ID")
    private Long id;
    /**
     * 公司id
     */
    @Mapping
    @ApiModelProperty("公司id")
    private Integer companyId;

    /**
     * 运单状态
     */
    @Mapping
    @ApiModelProperty("运单状态")
    private Integer status;
}
