package com.example.ratelimiter;

import com.example.ratelimiter.impl.RateLimiterServiceImpl;
import com.example.ratelimiter.model.response.LimitInvokeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "rate-limiter")
public class RateLimiterInvocationRestServiceImpl {

  @Autowired
  private RateLimiterServiceImpl rateLimiterService;

  @GetMapping(value = "invoke-method")
  public LimitInvokeResponse invokeLimit() throws InterruptedException {
    return rateLimiterService.invokeLimit();
  }
}
