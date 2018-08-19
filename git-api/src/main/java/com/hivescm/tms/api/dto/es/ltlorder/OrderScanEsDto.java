package com.hivescm.tms.api.dto.es.ltlorder;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OrderScanEsDto {
	
	@ApiModelProperty("货主ID")
	private Integer goodsOwnerId;
	@ApiModelProperty("货主手机号")
	private String phone;
	//1-进行中，2-已完成，3-已取消
	@ApiModelProperty("订单类型:1-进行中，2-已完成，3-已取消")
	private Integer orderType;
	//1-订单详情，2-下单成功，3-取消订单
	@ApiModelProperty("业务类型:1-订单详情，2-下单成功，3-取消订单")
	private Integer bussType;
	@ApiModelProperty("运单ID")
	private Long waybillId;
	@ApiModelProperty("运单号")
	private String waybillCode;
	@ApiModelProperty("订单ID")
	private Long orderId;
	@ApiModelProperty("订单号")
	private String orderCode;
	@ApiModelProperty("分页 - 每页显示数")
	private  Integer pageSize = 30;
	@ApiModelProperty("分页 - 当前页数")
	private  Integer currentPage = 1;
	@Mapping  
	@ApiModelProperty("公司id")
	private Integer companyId ;
	
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
