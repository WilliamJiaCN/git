package com.hivescm.tms.api.dto.es.waybill.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Description:
 * @Author: MaGuoDong
 * @Date: Created in 2018/5/24 19:17
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class CompanyInfo implements Serializable {

    private static final long serialVersionUID = 3045344424481600117L;

    @ApiModelProperty(value = "集团logo")
    private String logoUrl;

    @ApiModelProperty(value = "集团名称")
    private String orgName;

    @ApiModelProperty(value = "运单号")
    private String code;

}
