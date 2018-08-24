/**
 * 
 */
package com.hivescm.tms.api.dto.es.sign.component;

import com.hivescm.tms.api.dto.es.sign.SignDetailsEsDTO;
import com.hivescm.tms.api.dto.es.sign.SignEsDTO;
import com.hivescm.tms.api.dto.es.sign.SignRefuseEsDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 外发签收入参dto
 * @author  boqiang.deng
 * @date    2018年4月17日
 * @company 蜂网供应链管理（上海）有限公司
 * 
 */
@Data
@ToString
public class OutboundSignReqDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty("签收主表信息")
	private SignEsDTO signEsDTO;
	
	@ApiModelProperty("签收明细信息")
	private List<SignDetailsEsDTO> signDetailsEsDTO;
	
	@ApiModelProperty("拒签信息表")
	private SignRefuseEsDTO signRefuseEsDTO;

}
