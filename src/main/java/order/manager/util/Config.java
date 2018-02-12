/*
 * @(#) Config.java 2017年11月27日
 *
 * Copyright 2010 NetEase.com, Inc. All rights reserved.
 */
package order.manager.util;

import lombok.Data;
import lombok.Getter;

@Data
public class Config {

    @Getter
    private static Config config = new Config();

    private int maxTotal = 20;

    private int maxPerRoute = 20;

    private int socketTimeout = 30000;

    private int connectTimeout = 5000;

    private int connectionRequestTimeout = 3000;

    private int maxTimePreUser = 100;

    /**
     * 平台级别的网络异常时，最大的尝试次数
     */
    private int maxRetryTimes = 5;

    private int minInterval = 10;

    private int maxInterval = 500;

    private int frequencyLimitedWaitTime = 500;
}
