package com.hivescm.tms.common.address;

import com.hivescm.tms.mockframe.annotation.DefaultRule;
import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 百度地图请求对象
 *
 * @author LiuQiang
 * @company 蜂网供应链管理（上海）有限公司
 * @since 2017/8/10
 */
@Data
@ToString
@Component
public class BDReq implements Serializable{
    @DefaultRule("-6135924338746512275")
    private static final   long serialVersionUID = -6135924338746512277L;
    @DefaultRule("this is a key")
    private String ak;
    private String accessUrl;

}
