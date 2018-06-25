package com.hivescm.tms.api.enums.biz.stock;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * <p>Title: StockValueCalculation</p>
 * <p>Description: </p>
 * <p>Email: syenging@gmail.com</p>
 *
 * @author Jie.Yang
 * @version 1.0
 * @create 2018-05-12-19:48
 */
@Data
@ToString
public class StockValueCalculation {



    private BigDecimal operatingWeight; // 计算所需重量
    private BigDecimal operatingVolume; // 计算所需体积



}
