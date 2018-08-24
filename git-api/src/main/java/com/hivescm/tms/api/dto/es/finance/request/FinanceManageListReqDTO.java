package com.hivescm.tms.api.dto.es.finance.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
/**
 * 应付列表查询条件
 * @author wangqianqian
 *
 */
@Data
@ToString
public class FinanceManageListReqDTO implements Serializable{

	private static final long serialVersionUID = 6932410963088700455L;

	@ApiModelProperty(value = "日期类型 0:业务日期 1：过账日期 2：审核日期")
	private Integer dateType;
	
	@ApiModelProperty(value = "开始时间")
	private Long dateStart;
	
	@ApiModelProperty(value = "结束时间")
	private Long dateEnd;
	
	@ApiModelProperty(value = "单据类型 1.运单 2.派车单 3.发车配载单 4.外发中转单 5 装货单 6 卸货单 7 到货确认单")
	private List<Integer> sheetType;
	
	@ApiModelProperty(value = "费用类型")
	private List<Integer> feeType;
	
	@ApiModelProperty(value = "付款状态 1、未审核 2、未付款 3、部分付款 4、已付款")
	private Integer paymentStatus;
	
	@ApiModelProperty(value = "来源单号")
	private String dataSourceSheetId;

	@ApiModelProperty(value = "来源单号集合")
	private List<String> dataSourceSheetIdList;
	
	@ApiModelProperty(value = "业务网点ID")
	private List<Integer> businessNetworkIds;
	
	@ApiModelProperty(value = "付款网点ID")
	private List<Integer> paymentNetworkIds;
	
	@ApiModelProperty(value = "收款方")
	private String payee;
	
	@ApiModelProperty(value = "发货公司名称")
	private String invoiceCompanyName;
	
	@ApiModelProperty(value = "发货人姓名")
	private String invoiceUserName;
	
	@ApiModelProperty(value = "发货人手机号")
	private String invoiceUserMobile;
	
	@ApiModelProperty(value = "收货公司名称")
	private String receiptCompanyName;
	
	@ApiModelProperty(value = "收货人姓名")
	private String receiptUserName;
	
	@ApiModelProperty(value = "收货人手机号")
	private String receiptUserMobile;
	
	@ApiModelProperty(value = "车牌号码")
	private String vehicleNo;
	
	@ApiModelProperty(value = "司机姓名")
	private String driverName;
	@Mapping
	@ApiModelProperty(value = "发货人电话")
	private String invoiceUserTel;
	@Mapping
	@ApiModelProperty(value = "收货人电话")
	private String receiptUserTel;
	@Mapping
	@ApiModelProperty(value = "公司ID")
	private Long companyId;
	
	/**
	 * 分页 - 每页显示数
	 */
	private @ApiModelProperty("分页 - 每页显示数") Integer pageSize = 20;
	/**
	 * 分页 - 当前页数
	 */
	private @ApiModelProperty("分页 - 当前页数") Integer currentPage = 1;
	
	/**
	 * 初始化分页信息
	 */
	public void initPage() {
		if (null == pageSize || pageSize == 0) {
			pageSize = 20;
		}
		if (null == currentPage || currentPage == 0) {
			currentPage = 1;
		}
	}
}
