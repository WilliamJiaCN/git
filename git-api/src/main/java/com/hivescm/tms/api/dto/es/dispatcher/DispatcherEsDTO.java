package com.hivescm.tms.api.dto.es.dispatcher;

import java.io.Serializable;
import java.math.BigDecimal;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 派车单
 *
 * @author 李洪春
 * @since 2017/8/18 7:58
 */
@Data
@ToString
public class DispatcherEsDTO implements Serializable ,Cloneable{
	@Override  
    public DispatcherEsDTO clone() {  
		DispatcherEsDTO order = null;  
        try{  
        	order = (DispatcherEsDTO)super.clone();  
        }catch(CloneNotSupportedException e) {  
            e.printStackTrace();  
        }  
        return order;  
    }  
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1244274286695080984L;

	/**
	 * 主键
	 */
	@Logger
	@Mapping
	@ApiModelProperty("主键")
	private Long id;

	/**
	 * 公司id
	 */
	@Mapping
	@ApiModelProperty("公司id")
	private Long companyId;
	
	@Mapping
	@ApiModelProperty("公司名称")
	private String companyName;

	/**
	 * 派车批次
	 */
	@Logger
	@Mapping
	@ApiModelProperty("派车批次")
	private String batchCode;

	/**
	 * 派车类型( 1=提货、2=送货、3=提送)
	 */
	@Mapping
	@ApiModelProperty("派车类型( 1=提货、2=送货、3=提送)")
	private Integer dispatcherType;
	
	@Mapping
	@ApiModelProperty("派车类型( 1=提货、2=送货、3=提送)")
	private String dispatcherTypeName;

	/**
	 * 单据类型(1=派车单、2=城配单)
	 */
	@Mapping
	@ApiModelProperty("单据类型(1=派车单、2=城配单)")
	private Integer billType;
	@Mapping("isDelete")
	@ApiModelProperty("是否删除")
    private Boolean idelete;
	/**
	 * 派车网点ID
	 */
	@Mapping
	@ApiModelProperty("派车网点ID")
	private Integer branchId;
	/**
	 * 派车网点名称
	 */
	@Mapping
	@ApiModelProperty("派车网点名称")
	private String branchName;

	/**
	 * 发车时间
	 */
	@Mapping
	@ApiModelProperty("发车时间")
	private Long dispatcherTime;

	/**
	 * 车辆ID
	 */
	@Mapping
	@ApiModelProperty("车辆ID")
	private Integer vehicleId;

	/**
	 * 司机ID
	 *
	 * @{link com.hivescm.tms.api.dto.base.BaseDriverDTO#id}
	 */
	@Mapping
	@ApiModelProperty("司机ID")
	private Integer driverId;

	/**
	 * 提货人ID对应业务员
	 */
	@Mapping
	@ApiModelProperty("业务员ID")
	private Integer consigneeId;

	/**
	 * 提货人姓名
	 */
	@Mapping
	@ApiModelProperty("业务员姓名")
	private String consigneeName;
	
	@Mapping
	@ApiModelProperty("业务员手机号")
	private String consigneePhone;

	/**
	 * 派车单状态(1=已指派 2=已接单 3=已装货 4=未发车 5=已发车) 状态不为5时派车单未发车
	 */
	@Mapping
	@ApiModelProperty("派车单状态(1=已指派 2=已接单 3=已装货 4=未发车 5=已发车) 状态不为5时派车单未发车")
	private Integer status;
	/**
	 * 备注
	 */
	@Mapping
	@ApiModelProperty("备注")
	private String remark;

	/**
	 * 派车成本
	 */
	@Mapping
	@ApiModelProperty("派车成本")
	private BigDecimal cost;

	/**
	 * 成本分摊方式ID(基础字典)
	 */
	@Mapping
	@ApiModelProperty("成本分摊方式ID(基础字典)")
	private Integer costTypeId;

	@Mapping
	@ApiModelProperty("成本分摊方式ID(基础字典)")
	private String costTypeName;
	/**
	 * 总里程
	 */
	@Mapping
	@ApiModelProperty("总里程")
	private BigDecimal totalMileage;


	/**
	 * 装载率(体积)
	 */
	@Mapping
	@ApiModelProperty("装载率(体积)")
	private BigDecimal volumeLoading;

	/**
	 * 装载率(重量)
	 */
	@ApiModelProperty("装载率(重量)")
	@Mapping
	private BigDecimal weightLoading;

	/**
	 * 发车确认人 ID
	 */
	@Mapping
	@ApiModelProperty("发车确认人 ID")
	private Integer confirmUserId;

	/**
	 * 发车确认人
	 */
	@Mapping
	@ApiModelProperty("发车确认人")
	private String confirmUserName;

	/**
	 * 发车确认时间
	 */
	@Mapping
	@ApiModelProperty("发车确认时间")
	private Long confirmTime;

	/**
	 * 取消发车人ID
	 */
	@Mapping
	@ApiModelProperty("取消发车人ID")
	private Integer cancelConfirmUserId;

	/**
	 * 取消发车人姓名
	 */
	@Mapping
	@ApiModelProperty("取消方发车人姓名")
	private String cancelConfirmUserName;

	/**
	 * 取消发车时间
	 */
	@Mapping
	@ApiModelProperty("取消发车时间")
	private Long cancelConfirmTime;

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
	 * 发布状态
	 */
	@Mapping
	@ApiModelProperty("发布状态")
	private Boolean releaseStatus;

	/**
	 * 发布时间
	 */
	@Mapping
	@ApiModelProperty("发布时间")
	private Long releaseTime;

	/**
	 * 发布人
	 */
	@Mapping
	@ApiModelProperty("发布人")
	private Integer releaseUser;

	/**
	 * 取消发布人姓名
	 */
	@Mapping
	@ApiModelProperty("发布人姓名")
	private String releaseUserName;

	/**
	 * 取消发布时间
	 */
	@Mapping
	@ApiModelProperty("取消发布时间")
	private Long cancelReleaseTime;

	/**
	 * 取消发布人
	 */
	@Mapping
	@ApiModelProperty("取消发布人")
	private Integer cancelReleaseUser;

	/**
	 * 取消发布人姓名
	 */
	@Mapping
	@ApiModelProperty("取消发布人姓名")
	private String cancelReleaseUserName;

	/**
	 * 预计倒库时间
	 */
	@Mapping
	@ApiModelProperty("预计倒仓时间")
	private Long estimatedStorageTime;

	/**
	 * 总票数
	 */
	@Mapping
	@ApiModelProperty("总票数")
	private Integer totalWaybillNum;

	/**
	 * 总件数
	 */
	@Mapping
	@ApiModelProperty("总件数")
	private Integer totalGoodsNum;
	
	@Mapping
	@ApiModelProperty("总品种数")
	private Integer totalSkuid;
	

	/**
	 * 已装票数
	 */
	@Mapping
	@ApiModelProperty("已装票数")
	private Integer loadWaybillNum;

	/**
	 * 已卸票数
	 */
	@Mapping
	@ApiModelProperty("已卸票数")
	private Integer unloadWaybillNum;

	/**
	 * 签收票数
	 */
	@Mapping
	@ApiModelProperty("签收票数")
	private Integer signNum;

	/**
	 * 未签收票数
	 */
	@Mapping
	@ApiModelProperty("未签收票数")
	private Integer unSignNum;

	/**
	 * 总运费
	 */
	@Mapping
	@ApiModelProperty("总运费")
	private BigDecimal totalFreight;

	/**
	 * 总产值
	 */
	@Mapping
	@ApiModelProperty("总产值")
	private BigDecimal totalOutputValue;

	/**
	 * 总业务费
	 */
	@Mapping
	@ApiModelProperty("总代收货款")
	private BigDecimal totalBusFee;

	/**
	 * 总体积
	 */
	@Mapping
	@ApiModelProperty("总体积")
	private BigDecimal totalVolume;

	/**
	 * 总重量
	 */
	@Mapping
	@ApiModelProperty("总重量")
	private BigDecimal totalWeight;

	/*********************************************************** 冗余基本信息 *****************************************/

	/**
	 * 创建者姓名
	 */
	@Mapping
	@ApiModelProperty("创建者姓名")
	private String createUserName;

	/**
	 * 修改者姓名
	 */
	@Mapping
	@ApiModelProperty("修改者姓名")
	private String updateUserName;

	/*********************************************************** 冗余司机信息 *****************************************/

	/**
	 * 司机姓名
	 */
	@Mapping
	@ApiModelProperty("司机姓名")
	private String driverName;

	/**
	 * 手机号码
	 */
	@Mapping
	@ApiModelProperty("手机号码")
	private String phoneNo;

	/*********************************************************** 冗余车辆信息 *****************************************/

	/**
	 * 承运商ID
	 */
	@Mapping
	@ApiModelProperty("承运商ID")
	private Integer carrierId;

	/**
	 * 承运商名称
	 */
	@Mapping
	@ApiModelProperty("承运商名称")
	private String carrierName;

	/**
	 * 车牌号码
	 */
	@Mapping
	@ApiModelProperty("车牌号码")
	private String vehicleNo;

	/**
	 * 车辆皮重（KG）
	 */
	@Mapping
	@ApiModelProperty("车辆皮重")
	private BigDecimal vehicleWeight;

	/**
	 * 核载质量（KG）
	 */
	@Mapping
	@ApiModelProperty("核载质量")
	private BigDecimal checkQuality;

	/**
	 * 核载体积（M3）
	 */
	@Mapping
	@ApiModelProperty("核载体积")
	private BigDecimal checkVolume;

	/**
	 * 车型
	 */
	@Mapping
	@ApiModelProperty("车型ID")
	private Integer vehicleModels;

	/**
	 * 车型名称
	 */
	@Mapping
	@ApiModelProperty("车型名称")
	private String vehicleModelName;

	/**
	 * 车辆长度(M)
	 */
	@Mapping
	@ApiModelProperty("车辆长度")
	private BigDecimal vehicleLength;

	/**
	 * 车辆宽度(M)
	 */
	@Mapping
	@ApiModelProperty("车辆宽度")
	private BigDecimal vehicleWidth;

	/**
	 * 车辆高度(M)
	 */
	@Mapping
	@ApiModelProperty("车辆高度")
	private BigDecimal vehicleHeight;

	/**
	 * 车辆类型
	 */
	@Mapping
	@ApiModelProperty("车辆类型")
	private Integer vehicleType;
	
	/**
	 * 车辆类型
	 */
	@ApiModelProperty("车辆类型")
	private String vehicleTypeName;
	////////////////// 新增字段////////////////
	@Mapping
	@ApiModelProperty("仓库ID")
	private Long warehouseId;
	@Mapping
	@ApiModelProperty("仓库名称")
	private String warehouseName;//冗余

	@Mapping
	@ApiModelProperty("仓库交接单ID")
	private Integer warehouseReceiptId;
	
	  @ApiModelProperty("仓库交接单号")
	 private String warehouseReceiptNo;

	@Mapping
	@ApiModelProperty("仓库交接单状态")
	private Integer warehouseReceiptStatus;
	
	@Mapping
	@ApiModelProperty("交接人ID")
	private Integer warehouseHandoverId;
	
	@Mapping
	@ApiModelProperty("交接人")
	private String warehouseHandoverName;
	
	
	@Mapping
	@ApiModelProperty("交接时间")
	private Long warehouseHandoverTime;
	
	@Mapping
	@ApiModelProperty("出库确认人ID")
	private Integer warehouseOutstockId;
	
	@Mapping
	@ApiModelProperty("出库确认人")
	private String warehouseOutstockName;
	
	@Mapping
	@ApiModelProperty("出库确认时间")
	private Long warehouseOutstockTime;
	
	@Mapping
	@ApiModelProperty("作废人ID")
	private Integer discardUserId;
	
	@Mapping
	@ApiModelProperty("作废人")
	private String discardUserName;
	
	@Mapping
	@ApiModelProperty("作废人时间")
	private Long discardTime;
	
	@Mapping
	@ApiModelProperty("配送人ID")
	private Integer sendUserId;
	
	@Mapping
	@ApiModelProperty("配送人")
	private String sendUserName;
	
	@Mapping
	@ApiModelProperty("配送时间")
	private Long sendTime;
	
	@Mapping
	@ApiModelProperty("来源类型")
	private Integer sourceType;
	
	@Mapping
	@ApiModelProperty("是否全部装车标识")
	private Boolean allLoadingFlag;
	
	@Mapping
	@ApiModelProperty("是否允许生成装卸单")
	private Boolean allowedAdd;
	
	@Mapping
	@ApiModelProperty("零担--城配")
	private Integer businesstype;
	
	private @Mapping  @ApiModelProperty("关键字搜索（司机姓名、派车网点、派车批次）") String  keyword;
}
