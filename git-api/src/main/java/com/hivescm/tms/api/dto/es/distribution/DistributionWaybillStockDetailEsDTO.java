package com.hivescm.tms.api.dto.es.distribution;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class DistributionWaybillStockDetailEsDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */

	@ApiModelProperty("ID")
	private Long id;

	/**
	 * 公司ID
	 */
	@ApiModelProperty("公司ID")
	private Integer companyId;
	/**
	 * 公司名称
	 */
	@ApiModelProperty("公司名称")
	private String companyName;

	/**
	 * 库存主表ID
	 */
	@ApiModelProperty("库存主表ID")
	private Long stockId;

	/**
	 * 运单明细Id
	 */
	@ApiModelProperty("运单明细Id")
	private Long goodsId;

	/**
	 * 库存件数
	 */
	@ApiModelProperty("库存件数")
	private Integer stockNum;
	/**
	 * 已发件数
	 */
	@ApiModelProperty("已发件数")
	private Integer sendNum;

	/**
	 * 锁定件数
	 */
	@ApiModelProperty("锁定件数")
	private Integer lockNum;

	/**
	 * 货物名称
	 */
	@ApiModelProperty("货物名称")
	private String goodsName;
	/**
	 * 货物类型
	 */
	@ApiModelProperty("货物类型")
	private Integer goodsType;

	/**
	 * 商品类型
	 */
	@ApiModelProperty("商品类型")
	private String prodType;

	/**
	 * 规格
	 */
	@ApiModelProperty("规格")
	private String specifications;

	/**
	 * 编码
	 */
	@ApiModelProperty("编码")
	private String goodsCode;

	/**
	 * 包装类型
	 */
	@ApiModelProperty("包装类型")
	private Integer packing;

	/**
	 * 长（m）
	 */
	@ApiModelProperty("长")
	private BigDecimal lasting;

	/**
	 * 宽（m）
	 */
	@ApiModelProperty("宽")
	private BigDecimal width;

	/**
	 * 高（m）
	 */
	@ApiModelProperty("高")
	private BigDecimal height;

	/**
	 * 包装件数
	 */
	@ApiModelProperty("包装件数")
	private Integer packingNum;

	/**
	 * 整箱数
	 */
	@ApiModelProperty("整箱数")
	private Integer boxNum;

	/**
	 * 散件数
	 */
	@ApiModelProperty("散件数")
	private Integer sparePartNum;

	/**
	 * 件数
	 */
	@ApiModelProperty("件数")
	private Integer packageNum;
	/**
	 * 单间重量
	 */
	@ApiModelProperty("单件重量")
	private Integer oneWeight;
	/**
	 * 单间体积
	 */
	@ApiModelProperty("单件体积")
	private Integer oneVolume;

	/**
	 * 总体积
	 */
	@ApiModelProperty("总体积")
	private BigDecimal volume;

	/**
	 * 总重量
	 */
	@ApiModelProperty("总重量")
	private BigDecimal weight;
	/**
	 * 单价
	 */
	@ApiModelProperty("单价")
	private BigDecimal unitPrice;
	/**
	 * 单价类型
	 */
	@ApiModelProperty("单价类型")
	private Integer unitPriceType;
	/**
	 * 运费
	 */
	@ApiModelProperty("运费")
	private Integer freight;

	/**
	 * 创建人
	 */
	@ApiModelProperty("创建人")
	private Integer createUser;
	/**
	 * 创建人姓名
	 */
	@ApiModelProperty("创建人姓名")
	private String createUserName;

	/**
	 * 创建时间
	 */
	@ApiModelProperty("创建时间")
	private Long createTime;

	/**
	 * 修改人
	 */
	@ApiModelProperty("修改人")
	private Integer updateUser;
	/**
	 * 修改人姓名
	 */
	@ApiModelProperty("修改人姓名")
	private String updateUserName;

	/**
	 * 修改时间
	 */
	@ApiModelProperty("修改时间")
	private Long updateTime;


}
