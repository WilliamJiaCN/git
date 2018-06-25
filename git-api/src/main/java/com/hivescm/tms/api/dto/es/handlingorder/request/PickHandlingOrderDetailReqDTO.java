package com.hivescm.tms.api.dto.es.handlingorder.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @Description:
 * @Author: MaGuoDong
 * @Date: Created in 2018/5/25 15:27
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class PickHandlingOrderDetailReqDTO implements Serializable {

    private static final long serialVersionUID = 1977593486692290071L;

    @ApiModelProperty(value = "公司Id", notes = "前端调用时不传",hidden = true)
    private Long companyId;

    @ApiModelProperty(value = "装卸批次",required = true)
    private List<String> handlingOrderCodeList;



}
