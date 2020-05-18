package com.example.ratelimiter.model.response;

import java.io.Serializable;

public class LimitInvokeResponse implements Serializable {
  private String message;
  private boolean status;

  public LimitInvokeResponse() {
  }

  public LimitInvokeResponse(String message, boolean status) {
    this.message = message;
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public boolean getStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }
}
