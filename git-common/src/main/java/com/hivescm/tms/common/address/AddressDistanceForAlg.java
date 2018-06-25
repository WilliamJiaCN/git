package com.hivescm.tms.common.address;

import lombok.Data;
import lombok.ToString;

/**
 *  计算最小路径时，两个点的距离
 * @author m5itao
 * @company 蜂网供应链管理（上海）有限公司
 * @since 2017/11/20
 */
@Data
@ToString
public class AddressDistanceForAlg {

    private String start;

    private String end;

    private long value;

}
