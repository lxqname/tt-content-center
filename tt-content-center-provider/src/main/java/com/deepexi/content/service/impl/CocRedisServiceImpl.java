package com.deepexi.content.service.impl;

import com.deepexi.content.service.CocRedisService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
/**
 * @Author:LuFeng
 */
@Component
public class CocRedisServiceImpl implements CocRedisService {
    @Resource
    private com.deepexi.redis.service.RedisService redisService;


    @Override
    public Boolean setRedisZset(String key, String value, Integer seconds) {

        Long result;
        if (seconds == null){
            result =  redisService.zadd(key,1L,value);
        }else{
            result =  redisService.zadd(key,1L,value, seconds);
        }
        return result != null;
    }

    @Override
    public Double getRedisZsetScore(String key, String value) {

        return redisService.zscore(key,value);
    }

    @Override
    public void deleteRedisZsetAllKey(String key) {
        redisService.del(key);
    }
}
