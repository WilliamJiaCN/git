package com.hivescm.tms.api.dto.es.stock;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ToString
public class WaybillStockDetailEsDTO implements Serializable, Cloneable {
    //克隆对象
    @Override
    public Object clone() {
        WaybillStockDetailEsDTO stockDetailEsDTO = null;
        try {
            stockDetailEsDTO = (WaybillStockDetailEsDTO) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return stockDetailEsDTO;
    }

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    
    public Long groupByStorkId() {
    	return this.stockId;
    }

    public Long groupById(){
        return this.getId();
    }

    @Mapping
    @ApiModelProperty("ID")
    private Long id;

    /**
     * 目前没用
     */
    @Mapping
    @ApiModelProperty("运单ID")
    private Long waybillId;

    /**
     * 公司ID
     */
    @Mapping
    @ApiModelProperty("公司ID")
    private Integer companyId;
    /**
     * 公司名称
     */
    @Mapping
    @ApiModelProperty("公司名称")
    private String companyName;

    /**
     * 库存主表ID
     */
    @Mapping
    @ApiModelProperty("库存主表ID")
    private Long stockId;

    /**
     * 运单明细Id
     */
    @Mapping
    @ApiModelProperty("运单明细Id")
    private Long goodsId;

    /**
     * 已发件数
     */
    @Mapping
    @ApiModelProperty("明细已发件数")
    private Integer sendNum;

    /**
     * 明细已发体积
     */
    @Mapping
    @ApiModelProperty("明细已发体积")
    private BigDecimal sendVolume;
    /**
     * 明细已发重量
     */
    @Mapping
    @ApiModelProperty("明细已发重量")
    private BigDecimal sendWeight;

    /**
     * 货物名称
     */
    @Mapping
    @ApiModelProperty("货物名称")
    private String goodsName;
    /**
     * 货物类型
     */
    @Mapping
    @ApiModelProperty("货物类型")
    private Integer goodsType;

    /**
     * 商品类型
     */
    @Mapping
    @ApiModelProperty("商品类型")
    private Integer prodType;

    /**
     * 规格
     */
    @Mapping
    @ApiModelProperty("规格")
    private String specifications;

    /**
     * 编码
     */
    @Mapping
    @ApiModelProperty("编码")
    private String goodsCode;

    /**
     * 包装类型
     */
    @Mapping
    @ApiModelProperty("包装类型")
    private Integer packingType;
    
    /**
     * 包装名称
     */
    private @Mapping  @ApiModelProperty("包装名称") String packingName;

    /**
     * 长（m）
     */
    @Mapping
    @ApiModelProperty("长")
    private BigDecimal lasting;

    /**
     * 宽（m）
     */
    @Mapping
    @ApiModelProperty("宽")
    private BigDecimal width;

    /**
     * 高（m）
     */
    @Mapping
    @ApiModelProperty("高")
    private BigDecimal height;

    /**
     * 包装件数
     */
    @Mapping
    @ApiModelProperty("包装件数")
    private Integer packingNum;

    /**
     * 整箱数
     */
    @Mapping
    @ApiModelProperty("整箱数")
    private Integer boxNum;

    /**
     * 散件数
     */
    @Mapping
    @ApiModelProperty("散件数")
    private Integer sparePartNum;

    /**
    /**
     * 单间重量
     */
    @Mapping
    @ApiModelProperty("单件重量")
    private BigDecimal oneWeight;
    /**
     * 单间体积
     */
    @Mapping
    @ApiModelProperty("单件体积")
    private BigDecimal oneVolume;
    

    /**
     * 单价
     */
    @Mapping
    @ApiModelProperty("单价")
    private BigDecimal unitPrice;
    /**
     * 单价类型
     */
    @Mapping
    @ApiModelProperty("单价类型")
    private Integer unitPriceType;
    /**
     * 运费
     */
    @Mapping
    @ApiModelProperty("运费")
    private BigDecimal freight;

    /**
     * 创建人
     */
    @Mapping
    @ApiModelProperty("创建人")
    private Integer createUser;
    /**
     * 创建人姓名
     */
    @Mapping
    @ApiModelProperty("创建人姓名")
    private String createUserName;

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
     * 修改人姓名
     */
    @Mapping
    @ApiModelProperty("修改人姓名")
    private String updateUserName;

    /**
     * 修改时间
     */
    @Mapping
    @ApiModelProperty("修改时间")
    private Long updateTime;

    /**
     * 锁定件数
     */
    @Mapping
    @ApiModelProperty("明细锁定件数")
    private Integer lockNum;
    @Mapping
    @ApiModelProperty("明细锁定重量")
    private BigDecimal lockWeight;
    @Mapping
    @ApiModelProperty("明细锁定体积")
    private BigDecimal lockVolume;


    @Mapping
    @ApiModelProperty("商品开单件数")
    private Integer totalNum;
    @Mapping
    @ApiModelProperty("商品开单重量")
    private BigDecimal totalWeight;
    @Mapping
    @ApiModelProperty("商品开单体积")
    private BigDecimal totalVolume;


    @Mapping
    @ApiModelProperty("当前可用件数")
    private Integer packageNum;
    @Mapping
    @ApiModelProperty("当前可用体积")
    private BigDecimal volume;
    @Mapping
    @ApiModelProperty("当前可用重量")
    private BigDecimal weight;


    @Mapping
    @ApiModelProperty("签收返回入库数量")
    private Integer signStockNum;
    @Mapping
    @ApiModelProperty("签收返回入库体积")
    private BigDecimal signStockVolume;
    @Mapping
    @ApiModelProperty("签收返回入库重量")
    private BigDecimal signStockWeight;

    @Mapping
    @ApiModelProperty("在库数量=开单数量")
    private Integer inWarehouseNum;
    @Mapping
    @ApiModelProperty("在库数量体积=开单体积")
    private BigDecimal inWarehouseVolume;
    @Mapping
    @ApiModelProperty("在库数量重量=开单重量")
    private BigDecimal inWarehouseWeight;

    @Mapping
    @ApiModelProperty("目的网点")
    private Integer destOrgId;
    @Mapping
    @ApiModelProperty("类型字段")
    private String stockType;
    @Mapping
    @ApiModelProperty("业务日期")
    private Long businessDate;
    @Mapping
    @ApiModelProperty("录单时间")
	private Long entryTime;
    @Mapping
    @ApiModelProperty("预计到达日期")
    private Long extimatedTime;
    @Mapping
    @ApiModelProperty("录单员")
    private String recordStaff;
    @Mapping
    @ApiModelProperty("转移说明")
    private String transferExplanation;
    @Mapping
    @ApiModelProperty("转移人")
    private String transferStaff;
    @Mapping
    @ApiModelProperty("转移时间")
    private Long transferTime;

    @Mapping
    @ApiModelProperty("是否删除 默认1 删除2")
    private Integer stockFlag;

}
