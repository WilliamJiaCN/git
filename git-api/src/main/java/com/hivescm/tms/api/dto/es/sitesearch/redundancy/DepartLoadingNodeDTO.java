package com.hivescm.tms.api.dto.es.sitesearch.redundancy;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 发车配载信息
 * 提取关联该运单的发车配载批次以及其信息， 按照发车时间先后顺序排序
 * @author ke.huang
 * @date 2017年9月19日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class DepartLoadingNodeDTO implements Serializable{
	private static final long serialVersionUID = -2884104921220318412L;
	@ApiModelProperty("发车批次")
	private String departBatch;//发车配载的发车批次
	@ApiModelProperty("发车网点")
	private String departBranchName;//发车配载的网点
	@ApiModelProperty("装车件数")
    private Integer loadAmount;//发车配载中的装车件数
	@ApiModelProperty("运输批次发车时间")
    private Long batchDepartTime;//发车配载的发车时间
	@ApiModelProperty("到货时间")
    private Long loadTime;//发车配载操作到达后的到货时间
	@ApiModelProperty("到达网点")
    private String destOrgName;//发车配载的到达网点
	@ApiModelProperty("车牌号码")
    private String vehicleNo;//发车配载的车牌号码
	@ApiModelProperty("司机姓名")
    private String driverName;//发车配载的司机姓名
	@ApiModelProperty("司机手机号码")
    private String driverPhoneNo;//发车配载的手机号码
	@ApiModelProperty("配载员、制单员")
    private String createUserName;//发车配载的配载员
	@ApiModelProperty("备注信息")
    private String remark;//发车配载单的备注
	
}
