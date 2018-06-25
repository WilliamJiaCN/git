package com.hivescm.tms.api.dto.es.regulation;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 仓位转移基础信息
 *
 * @author 张文龙
 */
@Data
@ToString
public class PositionTransInfoEsDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1816309195270360010L;

	/**
     * 主键
     */
	@Mapping
	@ApiModelProperty("主键")
	private Long id;

	/**
	 * 仓位转移批次 ， 自动生成
	 */
	@Mapping
	@ApiModelProperty("仓位转移批次 ， 自动生成")
	private String transBatch;

	/**
	 * 公司id
	 */
	@Mapping
	@ApiModelProperty("公司id")
	private Long companyId;

	/**
	 * 运单id
	 */
	@Mapping
	@ApiModelProperty("运单id")
	private Long waybillId;

	/**
	 * 备注信息
	 */
	@Mapping
	@ApiModelProperty("备注信息")
	private String remark;

	/**
	 * 未出库、已出库、已入库
	 */
	@Mapping
	@ApiModelProperty("未出库、已出库、已入库")
	private Integer status;

	/**
	 * 仓库id
	 */
	@Mapping
	@ApiModelProperty("仓库ID")
	private Integer warehouseId;

	/**
	 * 库区id
	 */
	@Mapping
	@ApiModelProperty("库区ID")
	private Integer warehouseAreaId;

	/**
	 * 库位id
	 */
	@Mapping
	@ApiModelProperty("库位ID")
	private Integer warehousePositionId;

	/**
	 * 目标仓库id
	 */
	@Mapping
	@ApiModelProperty("目标仓库ID")
	private Integer destWarehouseId;

	/**
	 * 目标库区id
	 */
	@Mapping
	@ApiModelProperty("目标库区ID")
	private Integer destWarehouseAreaId;

	/**
	 * 目标库位id
	 */
	@Mapping
	@ApiModelProperty("目标库位ID")
	private Integer destWarehousePositionId;

	/**
	 * 创建人
	 */
	@Mapping
	@ApiModelProperty("创建人")
	private Integer createUser;

	/**
	 * 创建时间
	 */
	@Mapping
	@ApiModelProperty("创建时间")
	private Long createTime;

	/**
	 * 修改人
	 */
	@Mapping
	@ApiModelProperty("修改人")
	private Integer updateUser;

	/**
	 * 修改时间
	 */
	@Mapping
	@ApiModelProperty("修改时间")
	private Long updateTime;
}