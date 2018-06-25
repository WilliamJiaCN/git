package com.hivescm.tms.api.dto.es.bill;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.tms.api.enums.biz.bill.BillTypeEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 单据库存
 * @author ke.huang
 * @date 2017年9月25日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class BillStockEsDTO implements Serializable{
	private static final long serialVersionUID = 2583738648196443460L;
	@Mapping
	@ApiModelProperty("主键")
	private Long id;
	@Mapping
	@ApiModelProperty("公司ID")
    private Long companyId;
	@Mapping
	@ApiModelProperty("入库批次号")
    private String batchCode;
	@ApiModelProperty("单据类型")
    private BillTypeEnum billType;
	@Mapping
	@ApiModelProperty("开始号码")
    private String startNum;
	@Mapping
	@ApiModelProperty("结束号码")
    private String endNum;
	@Mapping
	@ApiModelProperty("份数")
    private Integer num;
	@Mapping
	@ApiModelProperty("入库时间")
    private Long instockTime;
	@Mapping
	@ApiModelProperty("备注")
    private String remark;
	@Mapping
	@ApiModelProperty("制单人ID")
    private Integer touchingUserId;
	@Mapping
	@ApiModelProperty("制单时间")
    private Long touchingTime;
	@Mapping
	@ApiModelProperty("已领用份数")
    private Integer receivedNum;
	@Mapping
	@ApiModelProperty("未领用份数")
    private Integer notReceiveNum;
	@Mapping
	@ApiModelProperty("已使用份数")
    private Integer usedNum;
	@Mapping
	@ApiModelProperty("已作废份数")
    private Integer discardNum;
	@Mapping
	@ApiModelProperty("创建人Id")
    private Integer createUser;
	@Mapping
	@ApiModelProperty("创建时间")
    private Long createTime;
	@Mapping
	@ApiModelProperty("更新人ID")
    private Integer updateUser;
	@Mapping
	@ApiModelProperty("更新时间")
    private Long updateTime;
	/********************************冗余字段*********************************/
	@Mapping
	@ApiModelProperty("公司名称")
	private String companyName;
	@Mapping
	@ApiModelProperty("制单人姓名")
    private String touchingUserName;
	@Mapping
	@ApiModelProperty("创建人姓名")
	private String createUserName;
	@Mapping
	@ApiModelProperty("修改人姓名")
	private String updateUserName;
	/***********仅作为单据入库时为单据明细使用网点赋值****************************/
	@Mapping
	@ApiModelProperty("单据使用网点ID")
	private Integer requestOrgId;
	@Mapping
	@ApiModelProperty("单据使用网点名称")
	private String requestOrgName;
}