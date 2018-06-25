package com.hivescm.tms.api.dto.es.delivery.req;

import com.hivescm.framework.validation.annotation.Required;
import com.hivescm.tms.api.dto.es.delivery.DeliveryGlobalDealerCarrierConfigEsDTO;
import com.hivescm.tms.api.dto.es.delivery.component.TmsDeliveryCompanyEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 快递经销商选取承运商规则请求类
 * @author qsk
 * @date 2018年1月23日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class DeliveryGlobalDealerCarrierConfigReqEsDTO implements Serializable{
	private static final long serialVersionUID = -1826025187089086829L;
	@Required
	@ApiModelProperty("经销商配置规则")
	private DeliveryGlobalDealerCarrierConfigEsDTO deliveryGlobalDealerCarrierConfigEsDTO;
	@ApiModelProperty("快递公司")
	private List<TmsDeliveryCompanyEsDTO> deliveryCompanyEsDTOS;

}