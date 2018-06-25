package com.hivescm.tms.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 浮点精度格式化
 * @author ke.huang
 * @date 2017年9月22日
 * @company 蜂网供应链管理（上海）有限公司
 */
public class AccuracyUtils {
	private static DecimalFormat df = new DecimalFormat("######0.00"); 
	/**
	 * 格式化两位小数位
	 * @param val
	 * @return
	 */
	public static BigDecimal formatToBigDecimal(Object val){
		return new BigDecimal(formatToString(val));
	}
	
	public static String formatToString(Object val){
		return df.format(val);
	}
}
