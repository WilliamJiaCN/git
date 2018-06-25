package com.hivescm.tms.common.address;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * Geometry接口结果对象
 *
 * @author qsk
 * @date 2017年9月12日
 * @company 蜂网供应链管理（上海）有限公司
 */
@Data
@ToString
public class GeometryReq implements Serializable {

    private Double[] lngs;
    private Double[] lats;
    private Double lon;

    private Double lat;
}
