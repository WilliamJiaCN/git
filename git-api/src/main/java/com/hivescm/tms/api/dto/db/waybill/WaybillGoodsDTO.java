package com.hivescm.tms.api.dto.db.waybill;


import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 运单明细
 * @author ke.huang
 * @date 2017年7月17日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class WaybillGoodsDTO implements Serializable{
	private static final long serialVersionUID = -4914027578351552211L;
	/**
	 * id
	 */
    private @ApiModelProperty("主键") Long id;
    /**
     * 公司id
     */
    private @ApiModelProperty("公司id") Integer companyId;
    /**
     * 运单id
     */
    private @ApiModelProperty("运单id") Long waybillId;
    /**
     * 货物名称
     */
    private @ApiModelProperty("货物名称") String goodsName;
    /**
     * 货物类型
     */
    private @ApiModelProperty("货物类型") Integer goodsType;
    /**
     * 商品类型
     */
    private @ApiModelProperty("商品类型") String prodType;
    /**
     * 规格
     */
    private @ApiModelProperty("规格") String specifications;
    /**
     * 编码
     */
    private @ApiModelProperty("编码") String goodsCode;
    /**
     * 包装类型
     */
    private @ApiModelProperty("包装类型") Integer packingType;
    /**
     * 长（m）
     */
    private @ApiModelProperty("长（m）") BigDecimal lasting;
    /**
     * 宽（m）
     */
    private @ApiModelProperty("宽（m）") BigDecimal width;
    /**
     * 高（m）
     */
    private @ApiModelProperty("高（m）") BigDecimal height;
    /**
     * 包装件数
     */
    private @ApiModelProperty("包装件数") Integer packingNum;
    /**
     * 整箱数
     */
    private @ApiModelProperty("整箱数") Integer boxNum;
    /**
     * 散件数
     */
    private @ApiModelProperty("散件数") Integer sparePartNum;
    /**
     * 件数
     */
    private @ApiModelProperty("件数") Integer packageNum;
    /**
     * 单件重量
     */
    private @ApiModelProperty("单件重量") BigDecimal oneWeight;
    /**
     * 单件体积
     */
    private @ApiModelProperty("单件体积") BigDecimal oneVolume;
    /**
     * 总体积
     */
    private @ApiModelProperty("总体积") BigDecimal volume;
    /**
     * 总重量
     */
    private @ApiModelProperty("总重量") BigDecimal weight;
    /**
     * 单价
     */
    private @ApiModelProperty("单价") BigDecimal unitPrice;
    /**
     * 单价类型
     */
    private @ApiModelProperty("单价类型") Integer unitPriceType;
    /**
     * 运费
     */
    private @ApiModelProperty("运费") BigDecimal freight;
    /**
     * 创建人
     */
    private @ApiModelProperty("创建人") Integer createUser;
    /**
     * 创建时间
     */
    private @ApiModelProperty("创建时间") Long createTime;
    /**
     * 修改人
     */
    private @ApiModelProperty("修改人") Integer updateUser;
    /**
     * 修改时间
     */
    private @ApiModelProperty("修改时间") Long updateTime;
    
    private @ApiModelProperty("商品编码") String skuid;
    
    private @ApiModelProperty("取货数量") Integer editGoodsAmount;

}