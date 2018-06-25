package com.hivescm.tms.finance.server.utils;

import com.hivescm.framework.common.consts.FrameworkExceptionConstants;
import com.hivescm.framework.common.exception.FrameworkException;
import com.hivescm.framework.common.utils.StringUtils;
import com.hivescm.framework.entitymapping.annotation.Mapping;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EtityCopy {
	private static final String SERIAL_VERSION_UID = "serialVersionUID";
	private static final String BIGDECIMAL_FIELD = "BigDecimal";
	private static final String LONG_FIELD = "Long";
	private static final String INTEGER_FIELD = "Integer";

	public static void copyProperties(Object source, Object... target) throws FrameworkException {
		try {
			Object name;
			Field[] fields = source.getClass().getDeclaredFields();
			HashMap<String, Object> map = new HashMap<String, Object>();
			Mapping annotation = null;
			String[] annotationValue = null;
			for (Field field : fields) {
				field.setAccessible(true);
				System.out.println("=============> 1"+field);
				annotation = field.getAnnotation(Mapping.class);
				if (null == annotation)
					continue;
				annotationValue = annotation.value();
				if (annotationValue.length == 0) {
					map.put(field.getName().toUpperCase(), field.get(source));
					continue;
				}
				String[] arrstring = annotationValue;
				int n = arrstring.length;
				for (int i = 0; i < n; ++i) {

					map.put(arrstring[i].toUpperCase(), field.get(source));
				}
			}
			Field[] targetFields = null;
			Object targetValue = null;
			for (Object obj : target) {
				Field[] arrfield = targetFields = obj.getClass().getDeclaredFields();
				for (int i = 0; i < arrfield.length; ++i) {
					Field field = arrfield[i];
					System.out.println("=============> 2"+field);
					if ("serialVersionUID".equals(field.getName()))
						continue;
					field.setAccessible(true);
					System.out.println("=============> 3"+field);
					Object v = map.get(field.getName().toUpperCase());
					targetValue = v;
					if (v != null) {
						EtityCopy.setValue(field, obj, targetValue);
						continue;
					}
					System.out.println("=============> 4"+field);
					EtityCopy.setValue(field, obj, map
							.get(obj.getClass().getSimpleName().toUpperCase() + "." + field.getName().toUpperCase()));
				}
			}
		} catch (Exception e) {
			throw new FrameworkException(Integer.valueOf(FrameworkExceptionConstants.ERROR_ENTITY_CONVERT_ERROR),
					"属性拷贝异常", (Throwable) e);
		}
	}

	public static <T> T copyProperties(Object source, Class<T> clazz) throws FrameworkException {
		T t = null;
		try {
			t = clazz.newInstance();
			EtityCopy.copyProperties(source, t);
		} catch (FrameworkException e) {
			throw e;
		} catch (Exception e) {
			throw new FrameworkException(Integer.valueOf(FrameworkExceptionConstants.ERROR_ENTITY_CONVERT_ERROR),
					"属性拷贝异常", (Throwable) e);
		}
		return t;
	}

	private static void setValue(Field f, Object obj, Object value) throws Exception {
		
		try {
			if (null != value && "BigDecimal".equals(f.getType().getSimpleName())) {
				if (StringUtils.isNotBlank((String) value.toString())) {
					f.set(obj, new BigDecimal(value.toString()));
				}
			} else if (null != value && "Long".equals(f.getType().getSimpleName())) {
				if (StringUtils.isNotBlank((String) value.toString())) {
					f.set(obj, Long.parseLong(value.toString()));
				}
			} else if (null != value && "Integer".equals(f.getType().getSimpleName())) {
				if (StringUtils.isNotBlank((String) value.toString())) {
					f.set(obj, Integer.parseInt(value.toString()));
				}
			} else if (null != value) {
				f.set(obj, value);
			}
		} catch (Exception e) {
			throw new FrameworkException(Integer.valueOf(FrameworkExceptionConstants.ERROR_ENTITY_CONVERT_ERROR),
					"属性拷贝异常", (Throwable) e);
		}
	}

	public static <T> List<T> doBatchClone(List<? extends Object> src, Class<T> c) throws Exception {
		if (src == null || src.size() == 0) {
			return null;
		}
		ArrayList<T> list = new ArrayList<T>(src.size());
		for (Object obj : src) {
			T doClone = EtityCopy.copyProperties(obj, c);
			list.add(doClone);
		}
		return list;
	}
}
