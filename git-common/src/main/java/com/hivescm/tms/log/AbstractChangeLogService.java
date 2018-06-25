package com.hivescm.tms.log;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

/**
 * 业务对象变更日志服务类
 *
 * @author 李洪春
 * @since 2017/7/27 17:04
 */
public abstract class AbstractChangeLogService<T> {

    /**
     * 缓存
     */
    private static final Map<Class, List<AttrLog>> CACHE = Maps.newConcurrentMap();


    /**
     * 生成业务变更操作日志
     *
     * @param oldObj 修改前业务对象
     * @param newObj 修改后业务对象
     * @return 变更日志
     */
    public abstract String generateChangeLog(T oldObj, T newObj);

    /**
     * 根据注解生成基础变更日志信息
     *
     * @param oldObj 修改前
     * @param newObj 修改后
     * @return 修改日志信息
     */
    protected String generateBaseLog(T oldObj, T newObj) {
        StringBuilder builder = new StringBuilder();
        List<AttrLog> list;
        Class clazz = oldObj.getClass();
        if (!CACHE.containsKey(clazz)) {
            list = Lists.newArrayList();
            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                ChangeLog changeLog = method.getAnnotation(ChangeLog.class);
                if (null != changeLog) {
                    list.add(new AttrLog(method, changeLog));
                }
            }
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                ChangeLog changeLog = field.getAnnotation(ChangeLog.class);
                if (null != changeLog) {
                    field.setAccessible(true);
                    list.add(new AttrLog(field, changeLog));
                }
            }
            CACHE.put(clazz, list);
        }
        list = CACHE.get(clazz);
        list.forEach(attrLog -> {
            try {
                Object oldValue = (null != attrLog.getMethod() ? attrLog.getMethod().invoke(oldObj) : attrLog.getField().get(oldObj));
                Object newValue = (null != attrLog.getMethod() ? attrLog.getMethod().invoke(newObj) : attrLog.getField().get(newObj));
                if (!isEquals(oldValue, newValue)) {
                    Map dict = getDict(attrLog.getChangeLog().dictKey());
                    if (null != dict) {
                        builder.append(MessageFormat.format(attrLog.getChangeLog().message(), dict.getOrDefault(oldValue, oldValue), dict.getOrDefault(newValue, newValue))).append("；");
                    } else {
                        builder.append(MessageFormat.format(attrLog.getChangeLog().message(), oldValue, newValue)).append("；");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();

            }
        });
        return builder.toString();
    }

    /**
     * 判断值是否相同
     *
     * @param obj1 参数1
     * @param obj2 参数2
     * @return 是否相等
     */
    protected boolean isEquals(Object obj1, Object obj2) {
        if (obj1 instanceof BigDecimal && obj2 instanceof BigDecimal) {
            return ((BigDecimal) obj1).compareTo((BigDecimal) obj2) == 0;
        }
        return (obj1 == obj2) || (obj1 != null && obj1.equals(obj2));
    }

    /**
     * 获取数据转换方式
     *
     * @param key 字典key值
     * @return 字典信息
     */
    public abstract Map getDict(String key);


    private static class AttrLog {
        /**
         * 方法
         */
        private Method method;

        /**
         * 属性
         */
        private Field field;

        /**
         * 修改日志信息
         */
        private ChangeLog changeLog;


        public AttrLog(Method method, ChangeLog changeLog) {
            this.method = method;
            this.changeLog = changeLog;
        }


        public AttrLog(Field field, ChangeLog changeLog) {
            this.field = field;
            this.changeLog = changeLog;
        }

        public Method getMethod() {
            return method;
        }


        public ChangeLog getChangeLog() {
            return changeLog;
        }


        public Field getField() {
            return field;
        }
    }
}
