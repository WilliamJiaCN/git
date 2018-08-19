package com.hivescm.tms.api.dto.db.pre;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * Boss登陆用户城市权限
 * @author ke.huang
 * @date 2017年11月13日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class BossCityDTO implements Serializable{
	private static final long serialVersionUID = 2987843328352607187L;
	@ApiModelProperty("城市Code")
	private String cityCode;
	@ApiModelProperty("城市名称")
	private String cityName;
}
