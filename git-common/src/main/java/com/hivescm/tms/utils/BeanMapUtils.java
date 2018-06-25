package com.hivescm.tms.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 对象和map互转工具类
 * @author 杨彭伟
 * @date 2017-12-05 15:21
 */
public class BeanMapUtils {
    /**
     * map转对象
     * @param map
     * @param beanClass
     * @return
     * @throws Exception
     */
    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
        if (map == null) {
            return null;
        }

        Object obj = beanClass.newInstance();

        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            Method setter = property.getWriteMethod();
            if (setter != null) {
                setter.invoke(obj, map.get(property.getName()));
            }
        }

        return obj;
    }

    /**
     * 对象转map
     * @param obj
     * @return
     * @throws Exception
     */
    public static Map<String, Object> objectToMap(Object obj) throws Exception {
        if (obj == null) {
            return null;
        }

        Map<String, Object> map = new HashMap<>();

        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            if (key.compareToIgnoreCase("class") == 0) {
                continue;
            }
            Method getter = property.getReadMethod();
            Object value = getter != null ? getter.invoke(obj) : null;
            map.put(key, value);
        }

        return map;
    }

    /**
     * 对象转map，忽略对象中的空值
     * @param obj
     * @return
     * @throws Exception
     */
    public static Map<String, Object> objectToMapIgnoreNullValue(Object obj) throws Exception {
        if (obj == null) {
            return null;
        }

        Map<String, Object> map = new HashMap<>();

        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            if (key.compareToIgnoreCase("class") == 0) {
                continue;
            }
            Method getter = property.getReadMethod();
            Object value = getter != null ? getter.invoke(obj) : null;
            if (value != null) {
                map.put(key, value);
            }
        }

        return map;
    }

    /**
     * map转对象，忽略Map中的空值
     * @param map
     * @param beanClass
     * @return
     * @throws Exception
     */
    public static Object mapToObjectIgnoreNullValue(Map<String, Object> map, Class<?> beanClass) throws Exception {
        if (map == null) {
            return null;
        }

        Object obj = beanClass.newInstance();

        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            Method setter = property.getWriteMethod();
            if (setter != null && map.get(property.getName()) != null) {
                setter.invoke(obj, map.get(property.getName()));
            }
        }

        return obj;
    }
}
