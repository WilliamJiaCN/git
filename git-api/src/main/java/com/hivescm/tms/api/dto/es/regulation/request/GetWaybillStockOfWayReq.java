package com.hivescm.tms.api.dto.es.regulation.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class GetWaybillStockOfWayReq implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2299532236100480838L;

	/**
     * 公司Id
     */
    @ApiModelProperty(value = "公司Id", notes = "前端调用时不传")
    private Long companyId;

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
    @ApiModelProperty(value = "网点ID")
    private Integer branchId;
    
    /**
     * 网点名称
     */
    @ApiModelProperty(value = "网点名称", notes = "前端调用时不传")
    private String branchName;
    
    /**
     * 运输批次发车日期开始
     */
    @ApiModelProperty("运输批次发车日期开始")
    private Long batchDepartTimeStart;
    /**
     * 运输批次发车日期结束
     */
    @ApiModelProperty("运输批次发车日期结束")
    private Long batchDepartTimeEnd;
    /**
     * 日期类型
     */
    @ApiModelProperty(value = "日期类型 0:发车日期 1:录单日期 2:业务日期")
    private Integer busstype;
    
    
    /**
	 * 日期开始
	 */
	private @Mapping  @ApiModelProperty("日期开始") Long timeStart;
	
	/**
	 * 日期结束
	 */
	private @Mapping  @ApiModelProperty("日期结束") Long timeEnd;
	


    /**
     * 录单日期开始
     */
    private @Mapping  @ApiModelProperty("录单日期开始") Long createTimeStart;

    /**
     * 录单日期结束
     */
    private @Mapping  @ApiModelProperty("录单日期结束") Long createTimeEnd;
	
	/**
	 * 业务日期开始	
	 */
	private @Mapping  @ApiModelProperty("业务日期开始") Long bussTimeStart;
	
	/**
	 * 业务日期结束	
	 */
	private @Mapping  @ApiModelProperty("业务日期结束") Long bussTimeEnd;
    /**
     * 发车网点Id
     */
    @ApiModelProperty(value = "发车网点Id")
    private Integer departBranchId;
    
    /**
     * 目的网点Id
     */
    @ApiModelProperty(value = "目的网点Id")
    private Integer arrivalBranchId;
    
    /**
     * 车牌号码
     */
    @ApiModelProperty("车牌号码")
    private String vehicleNo;
    /**
     * 发车网点 改为批量查询 qsk
     */
    @ApiModelProperty(value = "发车网点Id")
    private List<Integer> departBranchIds;

    /**
     * 分页 - 每页显示数
     */
    private @ApiModelProperty("分页 - 每页显示数") Integer pageSize = 30;
    /**
     * 分页 - 当前页数
     */
    private @ApiModelProperty("分页 - 当前页数") Integer currentPage = 1;


    /**
     * 初始化分页信息
     */
    public void initPage() {
        if (null == pageSize || pageSize == 0) {
            pageSize = 30;
        }
        if (null == currentPage || currentPage == 0) {
            currentPage = 1;
        }
    }
}
