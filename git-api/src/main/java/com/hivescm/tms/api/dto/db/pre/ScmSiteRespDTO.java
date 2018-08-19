package com.hivescm.tms.api.dto.db.pre;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
* @author  boqiang.deng
* @company 蜂网供应链(上海)管理有限公司
* @version 2017年11月20日 下午1:48:09
* 站点信息
*/
@Data
@ToString
public class ScmSiteRespDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
     * 站点id
     */
	@ApiModelProperty("站点id")
    private Long siteId;
    /**
     * 站点id
     */
    private String siteName;
    
}
