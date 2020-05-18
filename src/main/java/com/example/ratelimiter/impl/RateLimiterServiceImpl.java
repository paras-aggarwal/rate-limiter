package com.example.ratelimiter.impl;

import com.example.ratelimiter.common.RateLimitExecutor;
import com.example.ratelimiter.common.RateLimitManager;
import com.example.ratelimiter.common.RateLimitThrottleListener;
import com.example.ratelimiter.model.response.LimitInvokeResponse;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class RateLimiterServiceImpl {

  private static long iterations = 1000;
  private static long sleepTime = 100;
  private String instance_id;
  RateLimitExecutor rateLimiter;

  @PostConstruct
  protected void init() {
    instance_id = UUID.randomUUID().toString();
    rateLimiter = new RateLimitExecutor();
    rateLimiter.build(TimeUnit.SECONDS, 100);
    rateLimiter.setInstance_id(instance_id);
    RateLimitManager._instance.provisionRateLimit(rateLimiter, instance_id, new RateLimitThrottleListener());
  }

  public LimitInvokeResponse invokeLimit() throws InterruptedException {
    boolean success = false;
    for (int i = 0; i < iterations; i++) {
      RateLimitManager._instance.pegTraffic(instance_id);
      Thread.sleep(sleepTime);
    }

    try {
      RateLimitManager._instance.getThreadPool().awaitTermination(2, TimeUnit.SECONDS);
      RateLimitManager._instance.deProvisionRateLimit(instance_id);
    } catch (InterruptedException e) {
      System.exit(0);
    }
    System.out.println("\n Ended \n");
    success = true;
    return new LimitInvokeResponse("NO ERRORS", success);
  }
}
