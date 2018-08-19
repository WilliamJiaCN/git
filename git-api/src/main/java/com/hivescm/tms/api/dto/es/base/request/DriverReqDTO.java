package com.hivescm.tms.api.dto.es.base.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class DriverReqDTO extends PageQurey{
	
		private static final long serialVersionUID = -545289375306400263L;

		//开始时间
		@ApiModelProperty("开始时间")
		private String startTime;
		//结束时间
		@ApiModelProperty("结束时间")
		private String endTime;
		//司机编号
		@ApiModelProperty("司机编号")
	  	private String driverCode;
		//关键词
		@ApiModelProperty("关键词")
	  	private String keyWord;
		//司机姓名
		@ApiModelProperty("司机姓名")
	  	private String driverName;
		
		@ApiModelProperty("身份证号")
	  	private String identityCard;
		@ApiModelProperty("驾驶证号")
	  	private String driverNo;
		//手机号
		@ApiModelProperty("手机号")
	  	private String phoneNo;
		//承运商
		@ApiModelProperty("承运商")
	  	private String belongCarry;
		//状态
		@ApiModelProperty("状态")
	  	private Boolean status;
		//公司id
		@ApiModelProperty("公司id")
	  	private Long companyId;
		  /**
	     * 派车网点ID
	     */
	    @ApiModelProperty("网点ID")
	    private List<Long> branchId;
		@ApiModelProperty("当前记录id")
	  	private Integer selfId;
		
}
