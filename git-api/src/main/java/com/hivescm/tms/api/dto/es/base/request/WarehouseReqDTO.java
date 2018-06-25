package com.hivescm.tms.api.dto.es.base.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author Administrator
 *
 */
@Data
@ToString
public class WarehouseReqDTO extends PageQurey{

	@ApiModelProperty("ID")
	private Integer id;
	@ApiModelProperty("公司Id")
	private Long companyId;
	@ApiModelProperty("仓库编码")
	private String warehouseCode;
	@ApiModelProperty("仓库名称")
	private String warehouseName;
	@ApiModelProperty("所属网点")
	private Integer belongOrg;
	@ApiModelProperty("所属网点名称")
	private String belongOrgName;
	@ApiModelProperty("联系人")
	private String contact;
	@ApiModelProperty("手机号")
	private String phoneNo;
	@ApiModelProperty("电话号码")
	private String telNo;
	@ApiModelProperty("备注")
	private String remarks;
	@ApiModelProperty("仓库面积m2")
	private Long warehouseArea;
	@ApiModelProperty("国家")
	private String country;
	@ApiModelProperty("省")
	private String province;
	@ApiModelProperty("市")
	private String city;
	@ApiModelProperty("区")
	private String county;
	@ApiModelProperty("街道")
	private String street;
	@ApiModelProperty("详细地址")
	private String address;
	@ApiModelProperty("时间开始范围")
	private Integer createUser;
	@ApiModelProperty("时间开始范围")
	private Long createTime;
	@ApiModelProperty("时间开始范围")
	private Integer updateUser;
	@ApiModelProperty("时间开始范围")
	private Long updateTime;
	@ApiModelProperty("仓库类型")
	private Integer type;
	@ApiModelProperty("制单人名称")
	private String createUserName;
	@ApiModelProperty("修改人名称")
	private String UpdateUserName;

}
