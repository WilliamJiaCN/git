package com.hivescm.tms.api.dto.es.order;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 订单商品明细
 * @author ke.huang
 * @date 2017年10月27日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class OrderGoodsEsDTO implements Serializable{
	private static final long serialVersionUID = -1362559098612486678L;
	@Mapping
	@ApiModelProperty("主键")
	private Long id;
	@Mapping
	@ApiModelProperty("订单ID")
    private Long orderId;
	@Mapping
	@ApiModelProperty("仓库ID")
    private Long warehouseId;
	@Mapping
	@ApiModelProperty("仓库名称")
	private String warehouseName;//冗余
	@Mapping
	@ApiModelProperty("商品名称")
    private String goodsName;
	@Mapping
	@ApiModelProperty("商品类型")
    private String goodsType;
	@Mapping
	@ApiModelProperty("商品编码")
    private String goodsCode;
	@Mapping
	@ApiModelProperty("商品单价")
    private BigDecimal unitPrice;
	@Mapping
	@ApiModelProperty("包装")
    private String packing;
	@Mapping
	@ApiModelProperty("数量(件数)")
    private Integer amount;
	@Mapping
	@ApiModelProperty("最大提货量")
    private Integer maxDelivery;
	@Mapping
	@ApiModelProperty("重量")
    private BigDecimal weight;
	@Mapping
	@ApiModelProperty("体积")
    private BigDecimal volume;
	@Mapping
	@ApiModelProperty("长")
    private BigDecimal goodsLength;
	@Mapping
	@ApiModelProperty("宽")
    private BigDecimal goodsWidth;
	@Mapping
	@ApiModelProperty("高")
    private BigDecimal goodsHeight;
	@Mapping
	@ApiModelProperty("订单明细ID")
    private Long orderDetailId;
	@Mapping
	@ApiModelProperty("单位重量")
    private BigDecimal unitWeight;
	@Mapping
	@ApiModelProperty("单位体积")
    private BigDecimal unitVolume;
	@Mapping
	@ApiModelProperty("规格")
    private String specifications;
	@Mapping
	@ApiModelProperty("单位")
    private String unit;
	@Mapping
	@ApiModelProperty("创建人")
    private Integer createUser;
	@Mapping
	@ApiModelProperty("创建人姓名")
	private String createUserName;//冗余
	@Mapping
	@ApiModelProperty("创建时间")
    private Long createTime;
	@Mapping
	@ApiModelProperty("修改人")
    private Integer updateUser;
	@Mapping
	@ApiModelProperty("修改人姓名")
	private String updateUserName;//冗余
	@Mapping
	@ApiModelProperty("修改时间")
    private Long updateTime;
	@Mapping
	@ApiModelProperty("SKUID")
	private String skuId;
	@Mapping
	@ApiModelProperty("商品备注，售货机货位")
	private String remark;
	/**********************************额外冗余信息**************************************/
	@ApiModelProperty("商品类型ID")
    private Integer goodsTypeId;
	
}