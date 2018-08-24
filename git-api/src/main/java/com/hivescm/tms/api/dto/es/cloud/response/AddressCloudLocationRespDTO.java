package com.hivescm.tms.api.dto.es.cloud.response;

import com.hivescm.tms.common.address.FullLocation;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 百度云地址解析resp DTO
 * @author ke.huang
 * @date 2017年11月24日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class AddressCloudLocationRespDTO implements Serializable{
	private static final long serialVersionUID = -1747811378995669531L;
	@ApiModelProperty("转换成功的经纬度")
	private List<FullLocation> lonlats;
	@ApiModelProperty("转换失败的地址")
	private List<String> address;
	
}
