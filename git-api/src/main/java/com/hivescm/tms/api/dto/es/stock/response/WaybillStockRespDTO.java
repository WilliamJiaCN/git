package com.hivescm.tms.api.dto.es.stock.response;

import java.io.Serializable;
import java.math.BigDecimal;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 运单库存主表
 * @date 2017年7月17日
 * @company 蜂网供应链
 */
@Data
@ToString
public class WaybillStockRespDTO implements Serializable {
	
	/**
	 * id
	 */
	private @Mapping Long id;

	/**
	 * 公司id
	 */
	@ApiModelProperty("公司id")
	private @Mapping Integer companyId;
	/**
	 * 公司名称
	 */
	@ApiModelProperty("公司名称")
	private @Mapping String companyName;

	/**
	 * 运单ID
	 */
	@ApiModelProperty("运单ID")
	private @Mapping Long waybillId;

    /**
     * 运单编码
     */
    @ApiModelProperty("运单编码")
    private @Mapping String code;

	/**
	 * 库存网点id
	 */
	@ApiModelProperty("库存网点id")
	private @Mapping Integer orgId;
	/**
	 * 库存网点名称
	 */
	@ApiModelProperty("库存网点名称")
	private @Mapping String orgName;
	/**
	 * 仓库id
	 */
	@ApiModelProperty("仓库id")
	private @Mapping Integer warehouseId;
	/**
	 * 仓库名称
	 */
	@ApiModelProperty("仓库名称")
	private @Mapping String warehouseName;

	/**
	 * 库区id
	 */
	@ApiModelProperty("库区id")
	private @Mapping Integer warehouseAreaId;
	/**
	 * 库区名称
	 */
	@ApiModelProperty("库区名称")
	private @Mapping String warehouseAreaName;

	/**
	 * 库位id
	 */
	@ApiModelProperty("库位id")
	private @Mapping Integer warehousePositionId;
	/**
	 * 库位名称
	 */
	@ApiModelProperty("库位名称")
	private @Mapping String warehousePositionName;

	/**
	 * 创建人
	 */
	private @Mapping Integer createUser;
	/**
	 * 创建人名称
	 */
	private @Mapping String createUserName;

	/**
	 * 创建时间
	 */
	private @Mapping Long createTime;

	/**
	 * 修改人
	 */
	private @Mapping Integer updateUser;
	/**
	 * 修改人名称
	 */
	private @Mapping String updateUserName;

	/**
	 * 修改时间
	 */
	private @Mapping Long updateTime;

	private static final long serialVersionUID = 1L;
	
	/**  新增字段               */
	
	/**
     * 已发件数
     */
    private @Mapping @ApiModelProperty("已发件数") Integer sendNum;
    
    /**
     * 锁定件数
     */
    private @Mapping @ApiModelProperty("锁定件数") Integer lockNum;

	/**
     * 件数
     */
    private @Mapping @ApiModelProperty("可用件数") Integer packageNum;
    
    /**
     * 重量
     */
    private @Mapping @ApiModelProperty("可用重量") BigDecimal weight;
    
    /**
     * 体积
     */
    private @Mapping @ApiModelProperty("可用体积") BigDecimal volume;
	
	
	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 冗余运单信息<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	/**
	 * 发货网点ID	
	 */
	@ApiModelProperty("发货网点ID	")
	private @Mapping   Integer invoiceOrgId;
	
	/**
	 * 发货网点名称	
	 */
	@ApiModelProperty("发货网点名称")
	private @Mapping  String invoiceOrgName;
	
	/**
	 * 到达网点id	
	 */
	private @Mapping  @ApiModelProperty("到达网点id")Integer destOrgId;
	/**
	 * 到达网点名称	
	 */
	private @Mapping  @ApiModelProperty("到达网点名称	")String destOrgName;
	
	/**
	 * 配送方式	
	 */
	private @Mapping  @ApiModelProperty("配送方式")Integer distributionType;
	
	/**
	 * 目的地id
	 */
	private @Mapping  @ApiModelProperty("目的地id")Long destId;
	/**
	 * 目的地名称	
	 */
	private @Mapping  @ApiModelProperty("目的地名称")String destName;
	
	/**
	 * 备注	
	 */
	private @Mapping  @ApiModelProperty("备注")String remark;
	
	/**
	 * 总运费	
	 */
	private @Mapping  @ApiModelProperty("总运费")String totalFee;
	
	/**
	 * 付款方式
	 */
	
	private @Mapping @ApiModelProperty("付款方式") Integer payWay;
	/**
	 * 运单状态	
	 */
	private @Mapping  @ApiModelProperty("运单状态")Integer status;
	
	/**
	 * 订单创建时间(录单时间)		
	 */
	private @Mapping @ApiModelProperty("订单创建时间") Long waybillCreateTime;

	///////////////////////////////增加字段////////////////////////////////
	private @Mapping @ApiModelProperty("入库时间") Long storageTime;

	private @Mapping @ApiModelProperty("仓库信息") Integer stockFalg;
	
	
    /**
     * 货物名称
     */
    @Mapping
    @ApiModelProperty("货物名称")
    private String goodsName; 
    
    /**
     * 包装名称
     */
    private @Mapping  @ApiModelProperty("包装名称") String packingName;
    
    @Mapping
    @ApiModelProperty("在库数量=可用+锁定+拒签")
    private Integer inWarehouseNum;

    @Mapping
    @ApiModelProperty("在库数量体积=可用+锁定+拒签")
    private BigDecimal inWarehouseVolume;
    @Mapping
    @ApiModelProperty("在库数量数量=可用+锁定+拒签")
    private BigDecimal inWarehouseWeight;

}