package com.argus.mytodo.advices.annotations.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomResponse<T> {
    T data;
    String executionTime;
}
