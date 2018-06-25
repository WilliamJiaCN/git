package com.hivescm.tms.api.dto.es.bill.request;

import com.hivescm.tms.utils.StringUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

/**
 * 单据取消领用请求DTO(根据号段)
 * @author ke.huang
 * @date 2017年10月13日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class BillNumberReceivedCancelReqDTO implements Serializable{
	private static final long serialVersionUID = 1764161417057495189L;
	@ApiModelProperty("公司ID")
	private Long companyId;
	@ApiModelProperty("开始号段")
	private String startNum;
	@ApiModelProperty("结束号段")
	private String endNum;

	@Autowired
	private Integer operateUserId;
	@Autowired
	private String operateUserName;
	
	public Boolean isValid(){
		return null != this.companyId && null != this.startNum && null != this.endNum && StringUtil.isNumber(startNum) && StringUtil.isNumber(endNum);
	}
}
