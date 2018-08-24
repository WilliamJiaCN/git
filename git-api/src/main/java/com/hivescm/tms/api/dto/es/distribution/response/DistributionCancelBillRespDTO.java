package com.hivescm.tms.api.dto.es.distribution.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author boqiang.deng
 * @company 蜂网供应链管理（上海）有限公司
 * @since 2017/8/11
 */
@Data
@ToString
public class DistributionCancelBillRespDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 此处冗余运单数据与待处理相似所以直接使用待处理的运单冗余数据
	 */
	private DistributionPendingBillRespDTO waybill;
	@Mapping
	@ApiModelProperty("撤回时间")
	private Long cancelReleaseTime;

	@Mapping
	@ApiModelProperty("撤回人")
	private Integer cancelReleaseUser;

    @Mapping
    @ApiModelProperty(value = "撤回类型名称")
    private String releaseTypeName;
	@Mapping
	@ApiModelProperty("指派时间")
	private Long billTime;

	@Mapping
	@ApiModelProperty("接单时间")
	private Long acceptDate;
	private @Mapping @ApiModelProperty("运单ID") Long waybillId;
}
