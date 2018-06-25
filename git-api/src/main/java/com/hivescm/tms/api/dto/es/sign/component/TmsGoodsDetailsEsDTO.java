package com.hivescm.tms.api.dto.es.sign.component;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @王小雪
 * 拒收商品詳情 
 */
@Data
@ToString
public class TmsGoodsDetailsEsDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Mapping
    @ApiModelProperty("主键")
    private Long id;

    /**
     * 运单id
     */
    @Mapping
    @ApiModelProperty("运单id")
    private Long waybillId;
    /**
     * 拒收单id
     */
    @Mapping
    @ApiModelProperty("拒收单id")
    private Long refuseId;

    /**
     * 货物类型(1 拒收 2 签收)
     */
    @Mapping
    @ApiModelProperty("货物类型(1 拒收 2 签收)")
    private Integer goodsType;

    /**
     * 运单派送货物总件数
     */
    @Mapping
    @ApiModelProperty("运单派送货物总件数")
    private Integer dispacherNum;

    /**
     * 运单签收的总件数
     */
    @Mapping
    @ApiModelProperty("运单签收的总件数")
    private Integer signNum;

    /**
     * 货物名称
     */
    @Mapping
    @ApiModelProperty("货物名称")
    private String goodsName;

    /**
     * 派车单明细ID
     */
    @Mapping
    @ApiModelProperty("派车单明细ID")
    private Long dispatcherDetailId;

    /**
     * 运单货物明细ID
     */
    @Mapping
    @ApiModelProperty("运单货物明细ID")
    private Long waybillGoodsId;

    /**
     * 包装
     */
    @Mapping
    @ApiModelProperty("包装")
    private String packages;
    /**
     * 单位
     */
    @Mapping
    @ApiModelProperty("unit")
    private String unit;
    /**
     * skuid
     */
    @Mapping
    @ApiModelProperty("skuid")
    private String skuid;

    /**
     * 包裹号
     */
    @Mapping
    @ApiModelProperty("包裹号")
    private String boxNum;

    /**
     * 开单件数
     */
    @Mapping
    @ApiModelProperty("开单件数")
    private Integer createNum;

    /**
     * 运单拒收货物总件数
     */
    @Mapping
    @ApiModelProperty("单拒收货物总件数")
    private Integer refuseNum;

    /**
     * 拒收原因
     */
    @Mapping
    @ApiModelProperty("拒收原因")
    private String refuseCause;

    /**
     * 运单拒收部分货物总重量
     */
    @Mapping
    @ApiModelProperty("运单拒收部分货物总重量")
    private BigDecimal refuseWeight;

    /**
     * 运单拒收部分货物总方量
     */
    @Mapping
    @ApiModelProperty("运单拒收部分货物总方量")
    private BigDecimal refuseVolume;

    /****************************运单货物明细冗余字段******************************************/
    @Mapping
    @ApiModelProperty("商品备注，售货机货位")
    private String remark;
    /**
     * 公司id
     */
    @Mapping
    @ApiModelProperty("公司id")
    private Long companyId;

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
    /*****************************冗余的字段*********************************/
    /**
     * 商品类型
     */
    private @Mapping  @ApiModelProperty("商品类型") Integer prodType;
    /**
     * 商品类型
     */
    private @Mapping  @ApiModelProperty("商品类型名称") String prodTypeName;
    /**
	 * 运单号
	 */
	@Mapping
	private @ApiModelProperty("运单号") String code;

}