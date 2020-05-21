package com.deepexi.content.service;

/**
 * @Author:LuFeng
 */
public interface CocRedisService {

    /**
     * redis存放有序集合
     * @param key key
     * @param value 值
     * @param seconds 秒
     * @return
     */
    Boolean setRedisZset(String key, String value, Integer seconds);

    /**
     * 获得redis有序集合的key值score大小
     * @param key
     * @param value
     * @return 不存在返回NULL
     */
    Double getRedisZsetScore(String key, String value);

    /**
     * 删除所有Key
     * @param key
     * @return
     */
    void deleteRedisZsetAllKey(String key);
}
