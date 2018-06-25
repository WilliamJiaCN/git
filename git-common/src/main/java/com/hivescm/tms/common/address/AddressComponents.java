package com.hivescm.tms.common.address;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 地址组件
 *
 * @author LiuQiang
 * @company 蜂网供应链管理（上海）有限公司
 * @since 2017/8/11
 */
@Data
@ToString
public class AddressComponents implements Serializable {


    private static final long serialVersionUID = -789404560433965047L;
    /**
     * 省份
     */
    private String province;
    /**
     * 城市
     */
    private String city;
    /**
     * 区、镇
     */
    private String district;
    /**
     * 街道
     */
    private String street;
    /**
     * 解析结果的地址级别，省、城市、区县，如果解析到POI或门牌号，则level为POI类型、道路等
     */
    private String level;
}
