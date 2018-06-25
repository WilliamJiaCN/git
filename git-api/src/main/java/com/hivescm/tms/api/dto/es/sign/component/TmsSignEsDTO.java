package com.hivescm.tms.api.dto.es.sign.component;


import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.tms.api.dto.es.sign.SignDetailsEsDTO;
import com.hivescm.tms.api.dto.es.sign.SignEsDTO;
import com.hivescm.tms.api.dto.es.sign.SignFeeEsDTO;
import com.hivescm.tms.api.enums.biz.sign.SignStatusEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;
@Data
@ToString
public class TmsSignEsDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	@ApiModelProperty("签收主表信息")
	private SignEsDTO signEsDTO;
	
	@ApiModelProperty("签收明细信息")
	private List<SignDetailsEsDTO> signDetailsEsDTO;
	
	@ApiModelProperty("费用信息")
	private SignFeeEsDTO signFeeEsDTO;
	
	@ApiModelProperty("签收确认,拒签")
	private SignStatusEnum signMode;
	
	@ApiModelProperty("拒签原因")
	private String refuseCause;
	
	@ApiModelProperty("集团id")
	private Integer groupId;

	@ApiModelProperty("网点")
	private Integer orgId;

	@ApiModelProperty("网点名")
	private String orgName;

	/***************************************运单信息 ************************************/

    private @Mapping @ApiModelProperty("累计拒签件数") Integer refuseNumber;
	
    private @Mapping @ApiModelProperty("未签件数") Integer unsignedNumber;

    private @Mapping @ApiModelProperty("开单件数") Integer createNumber;
    
    private @Mapping @ApiModelProperty("累计签收件数") Integer signNumber;
    
    private @Mapping @ApiModelProperty("图片路径") String signPic;

    private Long getIdempotencyKey(){

    	if (signEsDTO!=null&&signEsDTO.getDispatcherDetailId()!=null){

    		return signEsDTO.getDispatcherDetailId();
		}
		return 0L;
	}
}
