package com.hivescm.tms.api.dto.es.waybill.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class WaybillListAppReqEsDTO implements Serializable{
    private static final long serialVersionUID = -6266283198484194252L;

    @ApiModelProperty("当前登陆网点ID")
    private Integer invoiceOrgIds;

    @ApiModelProperty("输入查询信息")
    private String messager;


    /**
     * 分页 - 每页显示数
     */
   // @ApiModelProperty("分页 - 每页显示数")
    private @ApiModelProperty(hidden = true) Integer pageSize = 30;
    /**
     * 分页 - 当前页数
     */
    //@ApiModelProperty("分页 - 当前页数")
    private @ApiModelProperty(hidden = true) Integer currentPage = 1;

    /**
     * 初始化分页信息
     */
    public void initPage() {
        if (null == pageSize || pageSize == 0) {
            pageSize = 30;
        }
        if (null == currentPage || currentPage == 0) {
            currentPage = 1;
        }
    }
}
