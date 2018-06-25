package com.hivescm.tms.api.dto.es.dispatcher;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 派车单货物
 *
 * @author 李洪春
 * @since 2017/8/18 8:25
 */
@Data
@ToString
public class DispatcherGoodsEsDTO implements Serializable {

    private static final long serialVersionUID = 3637379250896280526L;
    /**
     * 根据dispatcherId分组
     * @return
     */
    public Long groupByDispatcherId() {
		return this.dispatcherId;
    	
    }
    public Long groupByWaybillId() {
  		return this.waybillId;
      	
      }
    /**
     * 主键
     */
    @Logger
    @Mapping
    @ApiModelProperty("主键")
    private Long id;

    /**
     * 派车单ID
     */
    @Logger
    @Mapping
    @ApiModelProperty("派车单ID")
    private Long dispatcherId;
    @Mapping("isDelete")
	@ApiModelProperty("是否删除")
    private Boolean idelete;
    /**
     * 公司id
     */
    @Mapping
    @ApiModelProperty("公司id")
    private Long companyId;

    /**
     * 运单货物明细ID
     */
    @Mapping
    @ApiModelProperty("运单货物明细ID")
    private Long waybillGoodsId;

    /**
     * 货物名称
     */
    @Mapping
    @ApiModelProperty("货物名称")
    private String goodsName;

    /**
     * 包装
     */
    @Mapping({"packages", "packingName"})
    @ApiModelProperty("包装")
    private String packages;

    /**
     * 件数
     */
    @Mapping("DispatcherGoodsDO.packageNum")
    @ApiModelProperty("件数")
    private Integer packageNum;

    /**
     * 重量
     */
    @Mapping("DispatcherGoodsDO.weight")
    @ApiModelProperty("重量")
    private BigDecimal weight;

    /**
     * 体积
     */
    @Mapping("DispatcherGoodsDO.volume")
    @ApiModelProperty("体积")
    private BigDecimal volume;



    @ApiModelProperty("件数--只用于后台赋值")
    private Integer packageNums;

    /**
     * 重量
     */
    @ApiModelProperty("重量--只用于后台赋值")
    private BigDecimal packageWeight;

    /**
     * 体积
     */
    @ApiModelProperty("体积--只用于后台赋值")
    private BigDecimal packageVolume;

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

    /**
     * 派车单明细ID
     */
    @Mapping
    @ApiModelProperty("派车单明细ID")
    private Long dispatcherDetailId;

    /**
     * 货物库存ID
     */
    @Mapping
    @ApiModelProperty("货物库存ID")
    private Long waybillStockDetailId;

    /**
     * 运单ID
     */
    @ApiModelProperty("运单ID")
    private Long waybillId;

    private @Mapping({"goodsId","WaybillGoodsDO.id","CalculateGoodsDetailDTO.detailId"}) @ApiModelProperty("商品明细ID") Long goodsId;
    /**
     * 库存明细ID
     */
    @Mapping({"stockDetailId","WaybillStockDetailDO.id"})
    private @ApiModelProperty("库存明细ID") Long stockDetailId;
    /**
     * 库存主表ID
     */
    private @Mapping  @ApiModelProperty("库存主表ID") Long stockId;
    /**
     * 入库件数
     */
    private @Mapping  @ApiModelProperty("入库件数") Integer storageNum;

    /**
     * 入库锁定件数
     */
    @Mapping
    @ApiModelProperty("入库锁定件数")
    private Integer storageLockNum;

    /**
     * 指派发车件数
     */
    @Mapping
    @ApiModelProperty("指派发车件数")
    private Integer dispatcherNum;
    /**
     * 库存件数
     */
    private @Mapping  @ApiModelProperty("库存件数") Integer stockNum;


    /**
     * 库存体积
     */
    @Mapping
    @ApiModelProperty("库存体积")
    private BigDecimal stockVolume;
    /**
     * 库存重量
     */
    @Mapping
    @ApiModelProperty("库存重量")
    private BigDecimal stockWeight;
    /**
     * 已发件数
     */
    private @Mapping  @ApiModelProperty("已发件数") Integer sendNum;
    /**
     * 锁定件数
     */
    private @Mapping  @ApiModelProperty("锁定件数") Integer lockNum;
    /**
     * 公司名称
     */
    private @Mapping  @ApiModelProperty("公司名称") String companyName;
    /**
     * 货物类型
     */
    private @Mapping  @ApiModelProperty("货物类型") Integer goodsType;
    
    /**
     * 货物类型
     */
    private @Mapping  @ApiModelProperty("货物类型名称") String goodsTypeName;
    /**
     * 商品类型
     */
    private @Mapping  @ApiModelProperty("商品类型") Integer prodType;
    /**
     * 商品类型
     */
    private @Mapping  @ApiModelProperty("商品类型名称") String prodTypeName;
    /**
     * 规格
     */
    private @Mapping  @ApiModelProperty("规格") String specifications;
    /**
     * 编码
     */
    private @Mapping  @ApiModelProperty("编码") String skuid;
    /**
     * 包装类型
     */
    private @Mapping  @ApiModelProperty("包装类型") Integer packingType;
    /**
     * 包装名称
     */
    private @Mapping  @ApiModelProperty("包装名称") String packingName;
    /**
     * 长（m）
     */
    private @Mapping  @ApiModelProperty("长（m）") BigDecimal lasting;
    /**
     * 宽（m）
     */
    private @Mapping  @ApiModelProperty("宽（m）") BigDecimal width;
    /**
     * 高（m）
     */
    private @Mapping  @ApiModelProperty("高（m）") BigDecimal height;
    /**
     * 包装件数
     */
    private @Mapping  @ApiModelProperty("包装件数") Integer packingNum;
    /**     * 整箱数
     */
    private @Mapping  @ApiModelProperty("整箱数") Integer boxNum;
    /**
     * 散件数
     */
    private @Mapping  @ApiModelProperty("散件数") Integer sparePartNum;

    /**
     * 件数(总件数)
     */
    private @Mapping  @ApiModelProperty("总件数") Integer totalNum;
    
    /**
     * 运力分配接单时自动赋值（非必填）
     */
	private @Mapping @ApiModelProperty("最大取货量") Integer maxDelivery;
	/**
     * 用于司机APP上门提货（销退单类型等于“普通销退”）时装货确认时更新的数据
     */
    private @Mapping @ApiModelProperty("取货数量") Integer deliveryNum;
    /**
     * 单位重量  = 总重量/件数
     */
    private @Mapping  @ApiModelProperty("单位重量") BigDecimal oneWeight;
    /**
     * 单位体积 = 总体积/件数
     */
    private @Mapping  @ApiModelProperty("单位体积") BigDecimal oneVolume;
    
    /**
     * 用于司机APP上门提货（销退单类型等于“普通销退”）时装货确认时更新的数据
     */
    private @Mapping @ApiModelProperty("取货重量") BigDecimal deliveryWeight;
    
    /**
     * 用于司机APP上门提货（销退单类型等于“普通销退”）时装货确认时更新的数据
     */
    private @Mapping  @ApiModelProperty("取货体积") BigDecimal deliveryVolume;
    
    /**
     * 单价
     */
    private @Mapping  @ApiModelProperty("单价") BigDecimal unitPrice;
    
    private @Mapping @ApiModelProperty("单位") String unit;
    
    private @Mapping @ApiModelProperty("重量单位") String weightUnit;
    
    private @Mapping @ApiModelProperty("体积单位") String volumeUnit;
    /**
     * 单价类型
     */
    private @Mapping  @ApiModelProperty("单价类型") Integer unitPriceType;
    /**
     * 运费
     */
    private @Mapping  @ApiModelProperty("运费") BigDecimal freight;
    /**
     * 未税运费
     */
    private @Mapping  @ApiModelProperty("未税运费") BigDecimal freightNoTax;
    
    /**
     * 税费
     */
    private @Mapping  @ApiModelProperty("税费") BigDecimal tax;
    
    @Mapping
	@ApiModelProperty("商品备注，售货机货位")
	private String remark;
}
