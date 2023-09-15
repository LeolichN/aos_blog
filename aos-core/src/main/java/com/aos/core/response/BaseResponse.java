package com.aos.core.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BaseResponse {
  private boolean success;

  public BaseResponse(boolean success) {
    this.success = success;
  }
}
