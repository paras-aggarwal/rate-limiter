package com.example.ratelimiter.impl;

import com.example.ratelimiter.common.RateLimitExecutor;
import com.example.ratelimiter.common.RateLimitListener;
import com.example.ratelimiter.common.RateLimitManager;
import com.example.ratelimiter.common.RateLimitThrottleListener;

import java.util.concurrent.TimeUnit;

public class RateLimiterTest implements RateLimitListener {

  private static long iterations = 1000;
  private static long threshold_to_be_enforced = 100;
  private static long sleepTime = 0;

  public static void main(String... args) throws InterruptedException {
    new RateLimiterTest().test();
  }

  private void test() throws InterruptedException {
    String instance_id = "SAMPLE INSTANCE ID";
    RateLimitExecutor rateLimiter = new RateLimitExecutor();
    rateLimiter.build(TimeUnit.SECONDS, threshold_to_be_enforced);
    rateLimiter.setInstance_id(instance_id);
    RateLimitManager._instance.provisionRateLimit(rateLimiter, instance_id, new RateLimitThrottleListener());
    for (int i = 0; i < iterations; i++) {
      RateLimitManager._instance.pegTraffic(instance_id);
      Thread.sleep(sleepTime);
    }
    try {
      RateLimitManager._instance.getThreadPool().awaitTermination(2, TimeUnit.SECONDS);
      RateLimitManager._instance.deProvisionRateLimit("SAMPLE INSTANCE ID");
    } catch (InterruptedException e) {
      System.exit(1);
    }
    System.out.println("\n Test Ended \n");
    System.exit(0);
  }

  @Override
  public void rateLimitThresholdBreached() {
    System.out.println("Rate Limit has been breached for: ");
  }
}
