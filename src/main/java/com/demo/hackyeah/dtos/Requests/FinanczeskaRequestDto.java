package com.demo.hackyeah.dtos.Requests;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FinanczeskaRequestDto {
    String sessionId;
    String prompt;
}
