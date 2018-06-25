package com.hivescm.tms.api.dto.es.waybill.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.validation.annotation.Required;
import io.swagger.annotations.ApiModelProperty;
import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Description:运单跟踪请求对象
 * @Author: MaGuoDong
 * @Date: Created in 2018/5/24 13:46
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class WaybillTrackReqEsDTO implements Serializable {

    private static final long serialVersionUID = -8870520358065001971L;

    @Mapping
    @ApiModelProperty(value = "公司ID",hidden = true)
    private Integer companyId;

    @Mapping
    @ApiModelProperty(value = "公司ID")
    private Integer groupId;

    /**
     * 当前页数
     */
    @ApiModelProperty(value = "当前页数",required = true)
    private Integer currentPage;
    /**
     * 每页显示条数
     */
    @ApiModelProperty(value = "每页显示条数",required = true)
    private Integer pageSize;

    @Required
    @ApiModelProperty(value = "/多条件查询参数",required = true)
    @Logger
    private String info;
}
