package com.hivescm.tms.common.address;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 百度地图相应对象
 *
 * @author LiuQiang
 * @company 蜂网供应链管理（上海）有限公司
 * @since 2017/8/10
 */
@Data
@ToString
public  class BDRes implements Serializable{


    private static final long serialVersionUID = 7291353644179813072L;

    /**
     * 返回状态
     */
    private int status;
    /**
     * 相应描述
     */
    private String message;



}
