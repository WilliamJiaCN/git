package com.hivescm.tms.common.address;

import lombok.Data;

/**
 * description
 *
 * @author LiuQiang
 * @company 蜂网供应链管理（上海）有限公司
 * @since 2017/8/15
 */
@Data
public class Location {

    /**
     * 纬度
     */
    private Double lat;
    /**
     * 经度
     */
    private Double lng;
	public Location(Double lat, Double lng) {
		super();
		this.lat = lat;
		this.lng = lng;
	}
	public Location() {
		super();
	}

}
