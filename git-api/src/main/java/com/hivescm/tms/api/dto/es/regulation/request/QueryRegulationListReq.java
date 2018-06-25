package com.hivescm.tms.api.dto.es.regulation.request;

import com.hivescm.framework.common.utils.StringUtils;
import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class QueryRegulationListReq {

	/**
     * 公司Id
     */
    @ApiModelProperty(value = "公司Id", notes = "前端调用时不传")
    private Integer companyId;

    /**
     * 操作用户ID
     */
    @ApiModelProperty(value = "操作用户ID", notes = "前端调用时不传")
    private Integer opUserId;

    /**
     * 操作用户名称
     */
    @ApiModelProperty(value = "操作用户名称", notes = "前端调用时不传")
    private String opUserName;

    /**
     * 网点ID
     */
    @ApiModelProperty(value = "网点ID", notes = "前端调用时不传")
    private Integer branchId;
    
    /**
     * 网点名称
     */
    @ApiModelProperty(value = "网点名称", notes = "前端调用时不传")
    private String branchName;
    
    /**
     * 制单时间开始时间
     */
    @ApiModelProperty(value = "制单时间开始时间")
    private Long createTimeStart;
    /**
     * 制单时间结束时间
     */
    @ApiModelProperty(value = "制单时间结束时间")
    private Long createTimeEnd;
    
    /**
     * 出库时间开始时间
     */
    @ApiModelProperty(value = "出库时间开始时间")
    private Long leaveWarehouseConfirmTimeStart;
    /**
     * 出库时间结束时间
     */
    @ApiModelProperty(value = "出库时间结束时间")
    private Long leaveWarehouseConfirmTimeEnd;
    
    /**
     * 入库时间开始时间
     */
    @ApiModelProperty(value = "入库时间开始时间")
    private Long enterWarehouseConfirmTimeStart;
    /**
     * 入库时间结束时间
     */
    @ApiModelProperty(value = "入库时间结束时间")
    private Long enterWarehouseConfirmTimeEnd;
    
    /**
     * 调出网点Id
     */
    @ApiModelProperty(value = "调出网点")
    private String departBranchId;
    
    /**
     * 调入网点Id
     */
    @ApiModelProperty(value = "调入网点")
    private String arrivalBranchId;


    @ApiModelProperty(value = "网点查询类型 1 调入 2 调出 ")
    private Integer branchType;
    
    /**
     * 调拨批次状态
     */
    @ApiModelProperty(value = "调拨批次状态")
    private Integer status;
    
    /**
     * 调拨批次Code
     */
    @ApiModelProperty(value = "调拨批次Code")
    private String regulationBatch;

    /**
     * 默认为当前用户网点,可多选
     */
    @ApiModelProperty(value = "默认为当前用户网点,可多选")
    private List<Integer> transportId;

    /**
     * 运单号查询条件
     */
    @ApiModelProperty(value = "运单号")
    private String code;

    /**
     * 仓库名称
     */
    @ApiModelProperty(value = "仓库ID")
    private Integer warehouseCode;
    
    
    @ApiModelProperty(value = "时间类型 0：制单时间 1：出库时间 2：入库时间")
    private Integer timeType;
    
    @ApiModelProperty(value = "开始时间")
    private Long timeStart;
    @ApiModelProperty(value = "结束时间")
    private Long timeEnd;

    /**
     * 入库
     * 录单
     */
    @ApiModelProperty("入库日期查询类型为1,录单日期查询类型为2")
    private Integer dateType = 1;
    
	/**
	 * 分页 - 每页显示数
	 */
	private @ApiModelProperty("分页 - 每页显示数") Integer pageSize;
	/**
	 * 分页 - 当前页数
	 */
	private @ApiModelProperty("分页 - 当前页数") Integer currentPage;

	@ApiModelProperty("已加入列表运单")
	private List<String> waybillCodeList;


    /**
     * 到达网点id
     */
    private @Mapping
    @ApiModelProperty("目的网点网点id")
    Integer destOrgId;
    /**
     * 目的地名称
     */
    private @Mapping
    @ApiModelProperty("目的地名称")
    String destName;

    @Mapping
    @ApiModelProperty("运输线路名称	")
    private  String lineName;

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

	public Boolean checkRegulationBatch(){
        if(StringUtils.isBlank(this.regulationBatch)){
            return false;
        }
        return true;
    }
}
