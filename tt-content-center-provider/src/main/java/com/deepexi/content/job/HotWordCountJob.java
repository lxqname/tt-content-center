package com.deepexi.content.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.deepexi.content.service.HotWordCountService;
import com.deepexi.content.service.HotWordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ：wujie
 * @date ：Created in 2019/10/12 10:16
 * @description：HotWordCountJob
 * @version: 1.0.0
 */
@Component
public class HotWordCountJob implements SimpleJob {

    private static final Logger logger = LoggerFactory.getLogger(HotWordCountJob.class);

    @Autowired
    private HotWordCountService hotWordCountService;


    @Override
    public void execute(ShardingContext shardingContext) {
        logger.info("HotWordCountJob.execute：开始记录热词统计数据-启动......");
        hotWordCountService.task();
        logger.info("HotWordCountJob.execute：记录热词统计数据-结束......");
    }
}

