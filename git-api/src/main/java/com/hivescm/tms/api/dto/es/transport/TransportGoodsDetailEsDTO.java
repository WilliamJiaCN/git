package com.hivescm.tms.api.dto.es.transport;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 运输批次货物信息
 *
 * @author 李洪春
 */
@Data
@ToString
public class TransportGoodsDetailEsDTO implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;


    @Mapping
    @ApiModelProperty("主键")
    private Long id;

    /**
     * 公司id
     */
    @Mapping
    @ApiModelProperty("公司id")
    private Long companyId;

    /**
     * 运输单ID
     */
    @Mapping
    @ApiModelProperty("运输批次ID")
    private Long transportId;
    
    @Mapping
    @ApiModelProperty("运输单明细ID")
    private Long transportWaybillDetailId;
    

    /**
     * 货物ID
     */
    @Mapping
    @ApiModelProperty("货物ID")
    private Long goodsId;

    /**
     * 装车件数(在途件数)
     */
    @Mapping
    @ApiModelProperty("装车件数")
    private Integer loadAmount;

    /**
     * 装车重量(在途重量)
     */
    @Mapping
    @ApiModelProperty(value = "装车重量", example = "111.11")
    private BigDecimal loadWeight;

    /**
     * 装车体积(在途体积)
     */
    @Mapping
    @ApiModelProperty(value = "装车体积", example = "111.11")
    private BigDecimal loadVolume;

    /**
     * 到货件数
     */
    @Mapping
    @ApiModelProperty("到货件数")
    private Integer unloadAmount;

    /**
     * 到货重量
     */
    @Mapping
    @ApiModelProperty(value = "到货重量", example = "111.11")

    private BigDecimal unloadWeight;

    /**
     * 到货体积
     */
    @Mapping
    @ApiModelProperty(value = "到货体积", example = "111.11")
    private BigDecimal unloadVolume;

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
     * 运单ID
     */
    @Mapping
    @ApiModelProperty("运单ID")
    private Long waybillId;

    /**
     * 库存明细ID
     */
    @Mapping
    @ApiModelProperty("库存明细ID")
    private Long waybillStockDetailId;


    // >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>冗余字段<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    /**
     * 装车网点ID
     */
    @Mapping
    @ApiModelProperty("装车网点ID")
    private Long branchId;

    /**
     * 货物名称
     */
    @ApiModelProperty("货物名称")
    private String goodsName;


    /**
     * 创建人姓名
     */
    @ApiModelProperty("创建人姓名")
    private String createUserName;

    /**
     * 修改人姓名
     */
    @ApiModelProperty("修改人姓名")
    private String updateUserName;

    /** ------------------------------2018年3月28日新增字段 Jie.Yang ----------------------------------- **/

    @ApiModelProperty("包装")
    private String goodsPackage;

    /**
     * 是否已经删除
     */
    @ApiModelProperty(value = "是否已经删除",notes = "前端调用时不用传")
    private Integer flag;


}