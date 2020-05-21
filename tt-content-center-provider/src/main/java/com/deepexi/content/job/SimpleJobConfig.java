/*
 * Copyright 1999-2015 dangdang.com.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package com.deepexi.content.job;

import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.dangdang.ddframe.job.config.JobCoreConfiguration;
import com.dangdang.ddframe.job.config.simple.SimpleJobConfiguration;
import com.dangdang.ddframe.job.event.JobEventConfiguration;
import com.dangdang.ddframe.job.lite.api.JobScheduler;
import com.dangdang.ddframe.job.lite.config.LiteJobConfiguration;
import com.dangdang.ddframe.job.lite.spring.api.SpringJobScheduler;
import com.dangdang.ddframe.job.reg.zookeeper.ZookeeperRegistryCenter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.Resource;

/**
 * @author ：wujie
 * @date ：Created in 2019/10/12 10:16
 * @description：SimpleJobConfig
 * @version: 1.0.0
 */
@Configuration
@PropertySource("job.properties")
public class SimpleJobConfig {
    
    @Resource
    private ZookeeperRegistryCenter regCenter;

    @Resource
    private JobEventConfiguration jobEventConfiguration;

    private LiteJobConfiguration getLiteJobConfiguration(final Class<? extends SimpleJob> jobClass, final String cron, final int shardingTotalCount, final String shardingItemParameters) {
        return LiteJobConfiguration.newBuilder(new SimpleJobConfiguration(JobCoreConfiguration.newBuilder(
                jobClass.getName(), cron, shardingTotalCount).shardingItemParameters(shardingItemParameters).build(), jobClass.getCanonicalName())).overwrite(true).build();
    }

    @Resource
    private SimpleJob hotWordCountJob;



    @Bean(initMethod = "init")
    public JobScheduler wechatAttentionNumSchedulerJob( @Value("${elasticJob.hotWordCountJob.cron}") final String cron,
                                                   @Value("${elasticJob.hotWordCountJob.shardingTotalCount}") final int shardingTotalCount,
                                                   @Value("${elasticJob.hotWordCountJob.shardingItemParameters}") final String shardingItemParameters) {
        return new SpringJobScheduler(hotWordCountJob, regCenter, getLiteJobConfiguration(hotWordCountJob.getClass(), cron, shardingTotalCount, shardingItemParameters),jobEventConfiguration);
    }



}
