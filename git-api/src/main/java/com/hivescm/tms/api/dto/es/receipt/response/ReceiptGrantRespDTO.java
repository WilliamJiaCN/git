package com.hivescm.tms.api.dto.es.receipt.response;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.hivescm.framework.common.exception.ExceptionFactory;
import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import com.hivescm.tms.api.dto.es.receipt.ReceiptStockEsDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
/**
 * 回单发放批次
 * @author ke.huang
 * @date 2018年3月23日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class ReceiptGrantRespDTO implements Serializable,Cloneable{
	private static final long serialVersionUID = 1326144276061544603L;
	
	@ApiModelProperty("发放批次明细")
	private List<ReceiptStockEsDTO> details;
	
	@Mapping
	@ApiModelProperty("主键")
	private Long id;
	@Mapping
	@ApiModelProperty("公司ID")
	private Long companyId;
	@Logger @Mapping
	@ApiModelProperty("发放批次")
    private String batchCode;
	@Mapping
	@ApiModelProperty("客户接收人")
    private Long customReceiptUserId;
	@Mapping
	@ApiModelProperty("快递号/车牌号")
    private String expressVehicleNum;
	@Mapping
	@ApiModelProperty("快递费用")
    private BigDecimal fee;
	@Mapping
	@ApiModelProperty("备注")
    private String remark;
	@Mapping
	@ApiModelProperty(" 创建人")
	private Integer createUser;
	@Mapping
	@ApiModelProperty("创建时间")
    private Long createTime;
	@Mapping
	@ApiModelProperty("修改人")
    private Integer updateUser;
	@Mapping
	@ApiModelProperty("修改时间")
    private Long updateTime;
	@Mapping("isDelete")
	@ApiModelProperty("是否删除")
    private Boolean idelete;
	@Mapping
	@ApiModelProperty("是否同发货公司")
	private Boolean sameInvoiceCompany;
	
	/**冗余名称*/
	@Mapping
	@ApiModelProperty("创建人名称") 
	private String createUserName;
	@Mapping
	@ApiModelProperty("修改人名称") 
	private String updateUserName;
	@Mapping
	@ApiModelProperty("客户接收人姓名")
	private String customReceiptUserName;
	@Mapping 
	@ApiModelProperty("发放网点ID")
	private Long grantOrgId;
	@Mapping 
	@ApiModelProperty("发放网点名称")
	private String grantOrgName;
	
    
}