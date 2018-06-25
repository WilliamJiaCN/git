package com.hivescm.tms.api.dto.es.sign.response;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 
 * <p>
 * Title:SignBillEsRespDTO
 * </p>
 * <p>
 * Description: 返回app数据
 * </p>
 * <p>
 * Company: 蜂网供应链（上海）有限公司
 * </p>
 * 
 * @author 王小雪
 * @date 下午7:05:15
 */
@Data
@ToString
public class SignBillEsRespDTO implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Mapping
	@ApiModelProperty("返回数据")
	private int flag;

	@Mapping
	@ApiModelProperty("运单id")
	private Long wayBillId;
	@Mapping
	@ApiModelProperty("销退单运单id")
	private Long refuseWaybillId;
	@Mapping
	@ApiModelProperty("签收id")
	private Long signId;

	@Mapping
	@ApiModelProperty("签收单单号")
	private String signCode;

	@ApiModelProperty("收款方式(1=现金支付,2=二维码支付)")
	private Integer settlementMethod;
	@ApiModelProperty("签收类型(1= 全部签收,2 = 部分签收,3=全部拒收)")
	private Integer signType;
	@Mapping
	@ApiModelProperty("是否装车")
	private boolean loaded;

}
