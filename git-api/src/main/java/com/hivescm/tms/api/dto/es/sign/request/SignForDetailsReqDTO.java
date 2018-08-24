package com.hivescm.tms.api.dto.es.sign.request;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 
 * <p>
 * Title:SignForDetailsReqDTO
 * </p>
 * <p>
 * Description:签收详情
 * </p>
 * <p>
 * Company: 蜂网供应链（上海）有限公司
 * </p>
 * 
 * @author 王小雪
 * @date 下午6:21:04
 */
@Data
@ToString
public class SignForDetailsReqDTO implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty("userId")
	private Integer userId;

	@ApiModelProperty("运单id") @Logger
	private Long waybillId;
	@ApiModelProperty("公司id")
	private Long companyId;
	@ApiModelProperty("派单id") @Logger
	private Long dispatcherId;
	@ApiModelProperty("收款方式(1=现金支付,2=二维码支付)")
	private Integer settlementMethod;
	@ApiModelProperty("签收类型(1= 全部签收,2 = 部分签收,3=全部拒收)")
	private Integer signType;
	@Mapping
	@ApiModelProperty("是否装车")
	private Boolean loaded;

}
