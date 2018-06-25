package com.hivescm.tms.api.dto.es.transport.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Description:
 * @Author: MaGuoDong
 * @Date: Created in 2018/5/30 16:12
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class FareArrivalInfoRespDTO implements Serializable {

    private static final long serialVersionUID = 242746991816463750L;

    @ApiModelProperty("主键")
    private Long id;

    @ApiModelProperty("到货批次 ， 自动生成")
    private String arrivalBatch;

    @ApiModelProperty("到货网点ID")
    private Integer branchId;

    @ApiModelProperty("到货网点名称")
    private Integer branchName;

    @Mapping
    @ApiModelProperty("备注信息")
    private String remark;

}
