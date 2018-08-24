package com.hivescm.tms.api.dto.es.line.component;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.tms.api.dto.es.line.LineServiceEsDTO;
import com.hivescm.tms.api.enums.capacity.line.SaveLineCarrierInfoTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@ToString
public class LineCarrierReqDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	@Mapping
	@ApiModelProperty("主键")
    private Long id;
	@Mapping
	@ApiModelProperty("线路id")
    private Long lineId;
	@Mapping
	@ApiModelProperty("线路名称")
    private String lineName;
	@Mapping
	@ApiModelProperty("承运商id")
    private Integer carrierId;
	@Mapping
	@ApiModelProperty("承运商姓名")
	private String carrierName;
	@Mapping
	@ApiModelProperty("承运商网点id")
    private Integer branchId;
	@Mapping
	@ApiModelProperty("提货距离")
    private BigDecimal distance;
	@Mapping
	@ApiModelProperty("城市编码")
    private String cityCode;
	@ApiModelProperty("创建人名称")
    private String createUserName;
	@Mapping
	@ApiModelProperty("创建人")
    private Integer createUser;
	@ApiModelProperty("配送服务信息")
	private List<LineServiceEsDTO> lineServiceEsDTOs;
	@Mapping
	@ApiModelProperty("区分新增，更新和删除 true = 新增,更新 false = 删除,前端需注意此字段特别重要一定要区分开准确传值")
    private SaveLineCarrierInfoTypeEnum addOrUpdate;
	
	/**
	 * 根据addOrUpdate字段分组
	 * @return
	 */
	public SaveLineCarrierInfoTypeEnum groupByAddOrUpdate() {
		return this.addOrUpdate;
		
	}
}
