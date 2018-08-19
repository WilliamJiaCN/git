package com.hivescm.tms.api.dto.es.stock;


import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 运单明细 ES DTO
 * @author ke.huang
 * @date 2017年7月17日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class WaybillGoodsEsDTO implements Serializable{

	/**
	 *
	 */
	private @Mapping  static final long serialVersionUID = -6722187689707934496L;
	/**
	 * id
	 */
    private @ApiModelProperty("ES主键") Long id;


    private @Mapping({"goodsId","WaybillGoodsDO.id","CalculateGoodsDetailDTO.detailId"}) @ApiModelProperty("商品明细ID") Long goodsId;
    /**
     * 库存明细ID
     */
    @Mapping({"stockDetailId","WaybillStockDetailDO.id","DispatcherGoodsEsDTO.waybillStockDetailId"})
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
     * 公司id
     */
    private @Mapping  @ApiModelProperty("公司id") Integer companyId;
    /**
     * 公司名称
     */
    private @Mapping  @ApiModelProperty("公司名称") String companyName;
    /**
     * 运单id
     */
    private @Mapping  @ApiModelProperty("运单id") Long waybillId;
    /**
     * 货物名称
     */
    private @Mapping  @ApiModelProperty("货物名称") String goodsName;
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
     * 件数(应该取货量)
     */
    private @Mapping  @ApiModelProperty("件数") Integer packageNum;
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
     * 总重量
     */
    private @Mapping  @ApiModelProperty("总重量") BigDecimal weight;
    /**
     * 单位体积 = 总体积/件数
     */
    private @Mapping  @ApiModelProperty("单位体积") BigDecimal oneVolume;
    /**
     * 总体积
     */
    private @Mapping  @ApiModelProperty("总体积") BigDecimal volume;
    
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
    
    private @Mapping @ApiModelProperty("二级单位") String unit2;
    
    private @Mapping @ApiModelProperty("三级单位") String unit3;
    
    //一级单位转二级单位的规则
    private @Mapping @ApiModelProperty("单位转换规则1") Integer unitRule;
    
    //一级单位转三级单位的规则
    private @Mapping @ApiModelProperty("单位转换规则2") Integer unitRule2;
    
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
    
    /**
     * 计费价格
     */
    private @Mapping  @ApiModelProperty("计费价格") BigDecimal price;

    /**
     * 税率
     */
    private @Mapping  @ApiModelProperty("税率") String taxRate;

    /**
     * 税码
     */
    private @Mapping  @ApiModelProperty("税码") String taxCode;

    /**
     * 服务项
     */
    private @Mapping  @ApiModelProperty("服务项") String serviceName;

    /**
     * 是否含税
     */
    private @Mapping  @ApiModelProperty("是否含税") Boolean withTax;

    /**
     * 计量依据
     */
    private @Mapping  @ApiModelProperty("计量依据") String meteringBase;

    /**
     * 计量单位
     */
    private @Mapping  @ApiModelProperty("计量单位") Integer meteringUnit;

    /**
     * 货值
     */
    private @Mapping  @ApiModelProperty("货值") BigDecimal amout;




    @Mapping
	@ApiModelProperty("商品备注，售货机货位")
	private String remark;
    
    /**
     * 创建人
     */
    private @Mapping  @ApiModelProperty("创建人") Integer createUser;
    /**
     * 创建人姓名
     */
    private @Mapping  @ApiModelProperty("创建人姓名") String createUserName;
    /**
     * 创建时间
     */
    private @Mapping  @ApiModelProperty("创建时间") Long createTime;
    /**
     * 修改人
     */
    private @Mapping  @ApiModelProperty("修改人") Integer updateUser;
    /**
     * 修改人姓名
     */
    private @Mapping  @ApiModelProperty("修改人姓名") String updateUserName;
    /**
     * 修改时间
     */
    private @Mapping  @ApiModelProperty("修改时间") Long updateTime;

    private @Mapping  @ApiModelProperty("排序值") Integer sortNum;
}