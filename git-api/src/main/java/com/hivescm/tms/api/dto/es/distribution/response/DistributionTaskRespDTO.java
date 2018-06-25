package com.hivescm.tms.api.dto.es.distribution.response;

import java.io.Serializable;

import com.hivescm.framework.entitymapping.annotation.Mapping;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author  boqiang.deng
 * @company 蜂网供应链管理（上海）有限公司
 * @since   2017/8/11
*/
@Data
@ToString
public class DistributionTaskRespDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	@Mapping
	@ApiModelProperty("收货地址")
	private String receiptAddress;
	
	/**
	 * 派车单状态( 2=已接单 3=已装货  5=已发车 6=已签收)
	 */
	@Mapping
	@ApiModelProperty("派车单状态( 2=已接单 3=已装货  5=已发车 6=已签收 7 = 已撤单) ")
	private Integer status;
	/**
	 * 派车网点ID
	 */
	@Mapping
	@ApiModelProperty("派车网点ID")
	private Integer dotId;
	/**
	 * 派车网点名称
	 */
	@Mapping
	@ApiModelProperty("派车网点名称")
	private String dotName;
	 /**
     * 创建时间跟发布时间相等
     */
    @Mapping
    @ApiModelProperty("发布时间")
    private Long createTime;
    
    @Mapping
	@ApiModelProperty("派车单成本")
	private String cost;
    /**
     * 公司id
     */
    @Mapping
    @ApiModelProperty("公司id")
    private Long companyId;

    /**
     * 派车批次
     */
    @Mapping
    @ApiModelProperty("派车批次")
    private String batchCode;
    
    @Mapping
    @ApiModelProperty("派车单ID")
    private Long dispatcherId;
    /**
	 * 总体积
	 */
	@Mapping
	@ApiModelProperty("总体积")
	private String volume;
	/**
	 * 总重量
	 */
	@Mapping
	@ApiModelProperty("总重量")
	private String weight;
	/**
	 * 总单数
	 */
	@Mapping
	@ApiModelProperty("总单数")
	private Integer totalNum;
	/**
	 * 单据类型
	 */
	@Mapping
	@ApiModelProperty("单据类型 1=指派单 2=平台单 3=智能调度单")
	private Integer billType;
	 /**
     * 运单ID
     */
	@Mapping
	@ApiModelProperty("运单ID")
    private  Long waybillId;
	/**
	 *总件数
	 */
	@Mapping
	@ApiModelProperty("总件数")
	private Integer totalPackage;
	
	@Mapping
	@ApiModelProperty("指派网点")
    private Integer billOrgId;
	/**
     * 发布状态
     */
    @Mapping
    @ApiModelProperty(value = "已撤单标志",notes = "在已完成中此字段为false时标记为已撤单")
    private Boolean releaseStatus;
}

