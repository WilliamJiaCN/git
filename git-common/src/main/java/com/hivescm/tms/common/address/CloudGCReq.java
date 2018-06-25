package com.hivescm.tms.common.address;


import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * 地理编码请求
 *
 * @author LiuQiang
 * @company 蜂网供应链管理（上海）有限公司
 * @since 2017/8/10
 */
@Data
@ToString
public class CloudGCReq extends BDReq implements Serializable {
	private static final long serialVersionUID = 5139954438539203946L;

	/**
     * 格式化后的地址字符串
     */
	@ApiModelProperty("详细地址")
    private String address;

    /**
     * 城市
     */
	@ApiModelProperty("城市名称")
    private String city;

}
