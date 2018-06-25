package com.hivescm.tms.api.dto.es.sign.request;
import java.io.Serializable;

import com.hivescm.framework.entitymapping.annotation.Mapping;
import com.hivescm.framework.logger.api.annotation.Logger;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 签收图片DTO
 * @author lhf
 * @date 2017年11月14日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class SignPictureReqDTO implements Serializable {
	private static final long serialVersionUID = 4169729285135683482L;
	private @Mapping @ApiModelProperty(value="签收单ID",required=true) @Logger Long id;
	private @Mapping @ApiModelProperty("签收图片路径") String signPic;
	private @Mapping @ApiModelProperty("公司id") Long companyId;
    public Boolean isValid(){
    	return null != this.id && null != this.signPic && null != this.companyId;
    }
	
}