package com.hivescm.tms.api.dto.es.waybill.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Description:根据运单号和公司Id查询运单跟踪信息
 * @Author: MaGuoDong
 * @Date: Created in 2018/5/24 19:11
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class WaybillTrackInfoReqEsDTO implements Serializable {

    private static final long serialVersionUID = -1015656781971593443L;


    @Mapping
    @ApiModelProperty(value = "公司ID",hidden = true)
    private Integer companyId;

    @Mapping
    @ApiModelProperty(value = "集团ID",required = true)
    private Long groupId;

    @ApiModelProperty(value = "运单号",required = true)
    private String code;


}
