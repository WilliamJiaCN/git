package com.hivescm.tms.api.dto.es.outbill.req;

import com.hivescm.tms.api.dto.es.outbill.OutBillCompanyEsDTO;
import com.hivescm.tms.api.dto.es.outbill.OutBillFeeEsDTO;
import com.hivescm.tms.api.dto.es.outbill.OutBillInfoEsDTO;
import com.hivescm.tms.api.dto.es.outbill.resp.OutBillWaybillDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class OutBillCompontDTO {

	@ApiModelProperty("外发主表信息")
	private OutBillInfoEsDTO OutBillInfoEsDTO;
	
	@ApiModelProperty("外发公司信息")
	private List<OutBillCompanyEsDTO> outBillCompanys;
	
	@ApiModelProperty("外发明细信息")
	private List<OutBillWaybillDTO> outBillDetails;
	
	@ApiModelProperty("外发费用信息")
	private List<OutBillFeeEsDTO> outBillFees;
}
