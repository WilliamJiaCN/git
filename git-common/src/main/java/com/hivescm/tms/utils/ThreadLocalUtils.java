package com.hivescm.tms.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author  boqiang.deng
 * @date    2018年3月30日
 * @company 蜂网供应链管理（上海）有限公司
 * 
 */
public class ThreadLocalUtils<T> {
	/**
	 * 判断ThreadLocal是否为空
	 * @param dtos
	 * @return
	 */
	public static<T> ThreadLocal<List<T>> isNotNull(ThreadLocal<List<T>> dtos){
		if(null != dtos.get() && dtos.get().size()>0){
			return dtos;
		}else{
			List<T> list = new ArrayList<>();
			dtos.set(list);
			return dtos;
		}
		
	}
}
