package com.hivescm.tms.api.dto.es.stock;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 运单库存主表
 *
 * @date 2017年7月17日
 * @company 蜂网供应链
 */
@Data
@ToString
public class WaybillStockEsDTO implements Serializable, Cloneable {
	//克隆对象
	@Override
	public Object clone() {
		WaybillStockEsDTO stockEsDTO = null;
		try {
			stockEsDTO = (WaybillStockEsDTO) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return stockEsDTO;
	}
	/**
	 * id
	 */
	private @Mapping Long id;

	/**
	 * 公司id
	 */
	@ApiModelProperty("公司id")
	private @Mapping Long companyId;
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
	 * 运单类型
	 */
	@ApiModelProperty("运单类型")
	private @Mapping Integer waybillStatus;

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
	 * 商品名称 ,“/”间隔
	 */
	private @Mapping @ApiModelProperty("商品名称") String goodsName;

	@Mapping
	@ApiModelProperty("货物类型") // 冗余商品表类型，以"/"间隔
	private String goodsTypeName;
	
	/**
	 * 包装名称 ,“/”间隔
	 */
    private @Mapping  @ApiModelProperty("包装名称") String packingName;
    /**
     * 商品类型
     */
    private @Mapping  @ApiModelProperty("商品类型名称") String prodTypeName;

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

	/** 新增字段 */

	/**
	 * 已发件数
	 */
	private @Mapping @ApiModelProperty("已发件数") Integer sendNum;

	/**
	 * 锁定件数
	 */
	private @Mapping @ApiModelProperty("锁定件数") Integer lockNum;
	private @Mapping @ApiModelProperty("锁定体积") BigDecimal stockLockWeight;
	private @Mapping @ApiModelProperty("锁定重量") BigDecimal stockLockVolume;

	private @Mapping @ApiModelProperty("拒签件数") Integer signStockNum;
	private @Mapping @ApiModelProperty("拒签体积") BigDecimal signStockVolume;
	private @Mapping @ApiModelProperty("拒签重量") BigDecimal signStockWeight;

	private @Mapping @ApiModelProperty("可用件数") Integer packageNum;
	private @Mapping @ApiModelProperty("可用重量") BigDecimal weight;
	private @Mapping @ApiModelProperty("可用体积") BigDecimal volume;

	@Mapping
	@ApiModelProperty("在库数量=可用+锁定+拒签")
	private Integer inWarehouseNum;

	@Mapping
	@ApiModelProperty("在库数量体积=可用+锁定+拒签")
	private BigDecimal inWarehouseVolume;
	@Mapping
	@ApiModelProperty("在库数量重量=可用+锁定+拒签")
	private BigDecimal inWarehouseWeight;

	//>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> 冗余运单信息(以下字段在库存表中没有 只是为了捎带前端界面需求的数据)<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
	private @Mapping @ApiModelProperty("发货网点ID") Integer invoiceOrgId;
	private @Mapping  @ApiModelProperty("发货网点名称") String invoiceOrgName;
	private @Mapping  @ApiModelProperty("到达网点id")Integer destOrgId;
	private @Mapping  @ApiModelProperty("到达网点名称	")String destOrgName;
	private @Mapping  @ApiModelProperty("配送方式")Integer distributionType;
	private @Mapping  @ApiModelProperty("目的地id")Long destId;
	private @Mapping  @ApiModelProperty("目的地名称")String destName;
	private @Mapping  @ApiModelProperty("备注")String remark;
	private @Mapping  @ApiModelProperty("总运费")BigDecimal totalFee;
	private @ApiModelProperty("运单业务费") BigDecimal totalBusiness;
	private @Mapping @ApiModelProperty("付款方式") Integer payWay;
	private @Mapping @ApiModelProperty("付款方式名称") String payWayName;
	private @Mapping  @ApiModelProperty("运单状态")Integer status;
	private @Mapping @ApiModelProperty("订单创建时间") Long waybillCreateTime;
    private @Mapping  @ApiModelProperty("总体积") BigDecimal waybillVolume;
    private @Mapping  @ApiModelProperty("总重量") BigDecimal waybillWeight;
    private @Mapping  @ApiModelProperty("总件数") Integer totalNum;
	private @Mapping  @ApiModelProperty("到付")BigDecimal cod;
    private @Mapping @ApiModelProperty("代收货款")BigDecimal orderGoodsPayment;
	private @Mapping @ApiModelProperty("入库时间") Long storageTime;
	private @Mapping @ApiModelProperty("在库时间") String stockTime;
	private @ApiModelProperty("是否删除 默认1 删除2") Integer stockFlag;
	private @ApiModelProperty("1表示发货仓 2表示到货仓") Integer stockStatus;
	private @Mapping  @ApiModelProperty("录单员姓名")String userName;
	private @Mapping  @ApiModelProperty("预计到达日期") Date arrivalTime;
	private @Mapping  @ApiModelProperty("业务日期") Long bussTime;
	private @Mapping  @ApiModelProperty("发货人详细地址")String invoiceAddress;
	private @Mapping  @ApiModelProperty("发货人")String invoiceUser;
	private @Mapping  @ApiModelProperty("发货人手机号码")String invoiceMobleNo;
	private @Mapping  @ApiModelProperty("收货人")String receiptUser;
	private @Mapping  @ApiModelProperty("收货人详细地址")String receiptAddress;
	private @Mapping  @ApiModelProperty("收货人手机号码")String receiptConsignorMobleNo;
	private @Mapping  @ApiModelProperty("收货人电话")String receiptConsignorTelNo;
	private @Mapping  @ApiModelProperty("发货人电话")String invoiceTelNo;
	private @ApiModelProperty("回单要求") @Mapping String backType;
	private @ApiModelProperty("回单要求") @Mapping Integer backTypeValue;
	private @Mapping  @ApiModelProperty("回单份数") Integer backNum;
	private @Mapping  @ApiModelProperty("回单编号")String backCode;
	private @Mapping({"DispatcherDetailPrintDTO.waybillRemark","TmsWaybillEsDTO.remark","DispatcherWaybillEsDTO.waybillRemark"}) @ApiModelProperty("运单备注")String waybillRemark;
	private @Mapping  @ApiModelProperty("运输线路id")Long lineId;
	private @Mapping  @ApiModelProperty("运输线路名称	")String lineName;
	private @ApiModelProperty("运费") BigDecimal freightFee;
	private @ApiModelProperty("产值") BigDecimal productFee;
	private @ApiModelProperty("业务费") BigDecimal business;
	private @ApiModelProperty("转移说明") String transferRemark;
	private @ApiModelProperty("转移人") String transferName;
	private @ApiModelProperty("转移时间") Long transferTime;
	private @ApiModelProperty("是否等通知")Boolean iwaitNotice;
	private @ApiModelProperty("是否加急")Boolean iemergency;
	private @ApiModelProperty("是否VIP客户") Boolean vip;
	private @ApiModelProperty("是否为异常单") Boolean exception;
	private @Mapping  @ApiModelProperty("是否修改") Boolean isUpdate;
	private @Mapping  @ApiModelProperty("是否拆单") Boolean isDismantling;
    private @ApiModelProperty("运单状态名称") @Mapping String statusName;
}