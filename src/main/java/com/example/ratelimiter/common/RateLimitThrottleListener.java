package com.example.ratelimiter.common;

/**
 * // TODO Comment
 */
public class RateLimitThrottleListener implements RateLimitListener {

  public RateLimitThrottleListener() {
  }

  public void rateLimitThresholdBreached() {
    System.out.println("TOO MANY REQUESTS");
  }

}
