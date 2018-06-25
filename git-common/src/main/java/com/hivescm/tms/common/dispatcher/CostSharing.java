package com.hivescm.tms.common.dispatcher;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 分摊成本对象
 *
 * @author m5itao
 * @company 蜂网供应链管理（上海）有限公司
 * @since 2017/8/10
 */
@Data
@ToString
public class CostSharing {

    /**
     * 编号，运单号
     */
    private String id;

    /**
     * 重量
     */
    private BigDecimal weight;

    /**
     * 体积
     */
    private BigDecimal volume;

    /**
     * 件数
     */
    private Integer number;
    /**
     * 产值
     */
    private BigDecimal output;

    /**
     * 成本
     */
    private BigDecimal cost;


    public CostSharing(){

    }

    public CostSharing(String id, BigDecimal weight, BigDecimal volume, Integer number){
        this.id = id;
        this.weight = weight;
        this.volume = volume;
        this.number = number;
    }

	public CostSharing(String id, BigDecimal weight, BigDecimal volume, Integer number, BigDecimal output) {
		super();
		this.id = id;
		this.weight = weight;
		this.volume = volume;
		this.number = number;
		this.output = output;
	}

}
