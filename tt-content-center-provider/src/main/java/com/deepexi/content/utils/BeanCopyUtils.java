package com.deepexi.content.utils;

import net.sf.cglib.beans.BeanCopier;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：wujie
 * @date ：Created in 2019/9/27 17:15
 * @description：类转换工具
 * @version: 1.0.0
 */
public class BeanCopyUtils {
    //    创建过的BeanCopier实例放到缓存中，下次可以直接获取，提升性能
    static final Map<String, BeanCopier> BEAN_COPIERS = new HashMap<String, BeanCopier>();

    public static void copy(Object srcObj, Object destObj) {
        String key = genKey(srcObj.getClass(), destObj.getClass());
        BeanCopier copier = null;
        if (!BEAN_COPIERS.containsKey(key)) {
            copier = BeanCopier.create(srcObj.getClass(), destObj.getClass(), false);
            BEAN_COPIERS.put(key, copier);
        } else {
            copier = BEAN_COPIERS.get(key);
        }
        copier.copy(srcObj, destObj, null);
    }

    private static String genKey(Class<?> srcClazz, Class<?> destClazz) {
        return srcClazz.getName() + destClazz.getName();
    }
}
