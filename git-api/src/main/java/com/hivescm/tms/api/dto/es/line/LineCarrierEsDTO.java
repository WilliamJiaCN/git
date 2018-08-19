package com.hivescm.tms.api.dto.es.line;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
@Data
@ToString
public class LineCarrierEsDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	@Logger
	@Mapping
	@ApiModelProperty("主键")
	private Long id;
	@Logger
	@Mapping
	@ApiModelProperty("线路id")
    private Long lineId;
	@Mapping
	@ApiModelProperty("承运商id")
    private Integer carrierId;
	@Mapping
	@ApiModelProperty("承运商网点id")
    private Integer branchId;
	@Mapping
	@ApiModelProperty("提货距离")
    private BigDecimal distance;
	@Mapping
	@ApiModelProperty("城市编码")
    private String cityCode;
	@Mapping
    @ApiModelProperty("创建人")
    private Integer createUser;
	@Mapping
	@ApiModelProperty("创建时间")
    private Long createTime;
	@Mapping
	@ApiModelProperty("修改人")
    private Integer updateUser;
	@Mapping
	@ApiModelProperty("修改时间")
    private Long updateTime;
	
	/*********************************冗余数据***************************/
	@Mapping
	@ApiModelProperty("承运商姓名")
	private String carrierName;
	
	@Mapping
	@ApiModelProperty("线路名称")
    private String lineName;
	
	/**
	 * 根据线路id
	 * @return
	 */
	public Long groupBylineId() {
		return this.lineId;
	}
}
