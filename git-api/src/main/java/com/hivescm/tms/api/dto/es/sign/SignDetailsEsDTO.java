package com.hivescm.tms.api.dto.es.sign;

import java.io.Serializable;
import java.math.BigDecimal;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SignDetailsEsDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	public Long groupBySignId() {
		return this.signId;
	}
	public Long groupByWaybillStockDetailId() {
		return this.waybillStockDetailId;
	}
	@Mapping
	@ApiModelProperty("主键")
	private Long id;

	@Mapping
	@ApiModelProperty("公司id")
	private Long companyId;

	@Mapping
	@ApiModelProperty("签收主表id")
	private Long signId;

	@Mapping
	@ApiModelProperty("运单id")
	private Long waybillId;

	@Mapping
	@ApiModelProperty("货物名称")
	private String goodsName;

	@Mapping
	@ApiModelProperty("派车单明细id")
	private Long dispatcherDetailId;

	@Mapping
	@ApiModelProperty("运单货物明细id")
	private Long waybillGoodsId;

	@Mapping
	@ApiModelProperty("运单库存明细id")
	private Long waybillStockDetailId;

	@Mapping
	@ApiModelProperty("签收件数")
	private Integer signNumber;

	@Mapping
	@ApiModelProperty("拒签件数")
	private Integer refuseNumber;

	@Mapping
	@ApiModelProperty("未签件数")
	private Integer unsignedNumber;

	@Mapping
	@ApiModelProperty("开单件数")
	private Integer createNumber;
	@Mapping
	@ApiModelProperty("派送件数")
	private Integer dispatcherNumber;

	@Mapping
	@ApiModelProperty("重量")
	private BigDecimal weight;

	@Mapping
	@ApiModelProperty("体积")
	private BigDecimal volume;

	@Mapping
	@ApiModelProperty("件数")
	private Integer packageNum;

	@Mapping
	@ApiModelProperty("包装")
	private String packages;

	@Mapping
	@ApiModelProperty("包裹号")
	private String boxNumber;

	@Mapping
	@ApiModelProperty("备注")
	private String remark;
	/**
	 * 单位
	 */
	@Mapping
	@ApiModelProperty("单位")
	private String unit;
	/**
	 * skuid
	 */
	@Mapping
	@ApiModelProperty("skuid")
	private String skuid;
	 /**
     * 拒收原因
     */
    @Mapping
    @ApiModelProperty("拒收原因")
    private String refuseCause;
	/**
	 * 数量
	 */
	@Mapping
	@ApiModelProperty("数量")
	private Integer amount;
	@Mapping
	@ApiModelProperty("创建人")
	private Integer createUser;

	@Mapping
	@ApiModelProperty("创建时间")
	private Long createTime;

	@Mapping
	@ApiModelProperty("修改时间")
	private Long updateTime;

	@Mapping
	@ApiModelProperty("修改人")
	private Integer updateUser;
	
	@Mapping
	@ApiModelProperty("创建人名称")
	private  String createUserName;
	@Mapping
	@ApiModelProperty("修改人名称")
	private  String updateUserName;
	/********************************** 冗余运单库存明细字段 **********************************/
	 /**
     * 商品类型
     */
    private @Mapping  @ApiModelProperty("商品类型") Integer prodType;
    /**
     * 商品类型
     */
    private @Mapping  @ApiModelProperty("商品类型名称") String prodTypeName;

	/**
	 * 总件数 (运单)
	 */
	private @Mapping @ApiModelProperty("总件数") Integer totalNum;




}
