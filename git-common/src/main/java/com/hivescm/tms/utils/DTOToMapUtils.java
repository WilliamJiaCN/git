package com.hivescm.tms.utils;

import java.lang.reflect.Field;
import java.util.HashMap;

import com.hivescm.common.exception.SystemException;
import com.hivescm.tms.constants.ExceptionCodeConstants;

import lombok.extern.slf4j.Slf4j;
@Slf4j
public class DTOToMapUtils {
	
	public static HashMap<String,Object> getMap(Object object){
		Field[] fields=object.getClass().getDeclaredFields();
		HashMap<String,Object> map=new HashMap<String,Object>();
		if(fields.length==0){
			throw new SystemException(ExceptionCodeConstants.ERROR_CREATE_REGULATION_INFO, "请确定传入的对象中有字段");
		}
		for(Field field:fields){
			field.setAccessible(true);
			try {
				map.put(field.getName(), field.get(object));
			} catch (Exception e) {
				//log.error("对象转map错误", e);
	            throw new SystemException(ExceptionCodeConstants.ERROR_CREATE_REGULATION_INFO, "对象转map错误"+e);
			}
		}
		return map;
	}
}
