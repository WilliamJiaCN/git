package com.hivescm.tms.api.dto.es.bill.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 单据发放请求DTO
 * @author ke.huang
 * @date 2017年10月11日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class BillReceivedReqDTO implements Serializable{
	private static final long serialVersionUID = 6264101058103709861L;
	@Mapping
	@ApiModelProperty("主键")
	private Long id;
	@Mapping
	@ApiModelProperty("公司ID")
    private Long companyId;
	@Mapping
	@ApiModelProperty("发放单据起始号")
    private String grantStartNum;
	@Mapping
	@ApiModelProperty("发放单据结束号")
    private String grantEndNum;
	@Mapping
	@ApiModelProperty("发放份数")
    private Integer grantNum;
	@Mapping
	@ApiModelProperty("发放备注")
    private String grantRemark;
	@Mapping
	@ApiModelProperty("发放时间")
    private Long grantTime;
	@Mapping
	@ApiModelProperty("发放人")
    private Integer grantUserId;
	@Mapping
	@ApiModelProperty("单价")
    private BigDecimal price;
	@Mapping	
	@ApiModelProperty("总价")
    private BigDecimal totalPrice;
	@Mapping
	@ApiModelProperty("领取人ID")
    private Integer receivedUserId;
	@Mapping
	@ApiModelProperty("领取时间")
    private Long receivedTime;
	@Mapping
	@ApiModelProperty("经办人ID")
    private Integer handlerUserId;
	@Mapping
	@ApiModelProperty("发放人姓名")
    private String grantUserName;
	@Mapping
	@ApiModelProperty("领取人姓名")
    private String receivedUserName;
	@Mapping
	@ApiModelProperty("经办人姓名")
    private String handlerUserName;
	
	public Boolean isValid(){
		long start = Long.valueOf(this.grantStartNum),end = Long.valueOf(this.grantEndNum);
		if (null == this.id || null == this.companyId) {
			return false;
		}
		if ( start > end ) {
			return false;
		}
		if (end - start + 1 != this.grantNum) {
			return false;
		}
		return true;
	}
}
