package com.hivescm.tms.utils;

import java.util.ArrayList;
import java.util.List;

import net.sf.cglib.beans.BeanCopier;

public class BeanCopyUtils {
	/**
     * 对象转换
     *
     * @param source 原对象
     * @param target 目标对象
     */
    public static void copyProperties(Object source, Object target) {

        final BeanCopier copier = BeanCopier.create(source.getClass(), target.getClass(), false);
        copier.copy(source, target, null);
    }

    /**
     * 以BeanCopier方式将给定集合转换为指定类型的集合
     *
     * @param sourceList 要转换的集合
     * @param clazz      要转换为的类型
     * @param <S>        源集合元素对象类型
     * @param <T>        目标集合元素对象类型
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <S, T> List<T> copyList(List<S> sourceList, Class<T> clazz) throws IllegalAccessException, InstantiationException {
        if (sourceList == null) {
            throw new IllegalArgumentException("sourceList can not be empty");
        }
        List<T> targetList;
        if (sourceList.size() > 0) {
            targetList = new ArrayList<>(sourceList.size());
            final BeanCopier copier = BeanCopier.create(sourceList.get(0).getClass(), clazz, false);
            for (S item : sourceList) {
                T target = clazz.newInstance();
                copier.copy(item, target, null);
                targetList.add(target);
            }
        } else {
            targetList = new ArrayList<>(0);
        }
        return targetList;
    }
}
