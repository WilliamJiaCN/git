package com.hivescm.tms.api.dto.es.distribution.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author  boqiang.deng
 * @company 蜂网供应链管理（上海）有限公司
 * @since   2017/8/11
*/
@Data
@ToString
public class DistributionLineSortReqDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	@ApiModelProperty("派车单明细id")
	@Mapping("id")
	private Long dispatcherDetailId;
	@ApiModelProperty("派车单明细顺序值")
	@Mapping
	private Integer sort;
    @ApiModelProperty("公司id")
    @Mapping
    private Long companyId;
}

