package com.hivescm.tms.common.address;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * description
 *
 * @author LiuQiang
 * @company 蜂网供应链管理（上海）有限公司
 * @since 2017/8/16
 */
@Data
@ToString
public class CloudGCResult implements Serializable {
	private static final long serialVersionUID = -3368538016846512564L;
	/**
     * 解析结果的来源，百度（baidu）、用户自定义（custom）
     */
    private String source;
    /**
     * Lat（纬度）&lng（经度）
     */
    private Location location;
    /**
     * 百位置坐标误差范围[左下角纬度经度];[右上角纬度度]，如："29.595089,105.048917;29.595874,105.049815"
     */
    private String bound;
    /**
     * 百度地址结构化字符，格式：省市区道路
     */
    private String formatted_address;
    /**
     * 用户自定义的地址
     */
    private String custom_address;

    private AddressComponents address_components;

    private String precise;


}
