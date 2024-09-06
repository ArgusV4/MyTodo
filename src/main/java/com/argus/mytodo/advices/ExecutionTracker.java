package com.argus.mytodo.advices;

import com.argus.mytodo.advices.responses.CustomResponse;
import com.argus.mytodo.exceptionhandler.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

@Aspect
@Configuration
@Slf4j
public class ExecutionTracker {

    @Around("@annotation(com.argus.mytodo.advices.annotations.TrackExecutionTime) || execution(* com.argus.mytodo.exceptionhandler.GlobalExceptionHandler.*(..))")
    public Object ExecutionTimeTracker(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = pjp.proceed();
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        String executionTimeStr = executionTime + " ms";


        if (result instanceof ResponseEntity<?>) {
            ResponseEntity<?> responseEntity = (ResponseEntity<?>) result;
            Object originalBody = responseEntity.getBody();
            if (originalBody instanceof ErrorResponse) {
                ErrorResponse errorResponse = (ErrorResponse) originalBody;
                errorResponse.setExecutionTime(executionTimeStr);

                return ResponseEntity.status(responseEntity.getStatusCode())
                        .body(errorResponse);
            }else {
                CustomResponse customResponse = new CustomResponse(originalBody,executionTimeStr);
                ResponseEntity<CustomResponse> newResponseEntity = new ResponseEntity<>(
                        customResponse,
                        responseEntity.getHeaders(),
                        responseEntity.getStatusCode()
                );
                return newResponseEntity;
            }
        }
        else {
            return result;
        }
    }
}
