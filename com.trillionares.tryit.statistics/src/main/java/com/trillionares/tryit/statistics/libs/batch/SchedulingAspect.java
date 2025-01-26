package com.trillionares.tryit.statistics.libs.batch;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Slf4j
@Aspect
@Component
public class SchedulingAspect {

    @Around("@annotation(org.springframework.scheduling.annotation.Scheduled)")
    public Object measureExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object proceed = joinPoint.proceed();
        String executionTime = converterMsToReadableTime(System.currentTimeMillis()-start);

        log.info("{} : {}",joinPoint.getSignature(),executionTime);
        return proceed;
    }

    private String converterMsToReadableTime(long milliSeconds) {

        StringBuilder readableTime = new StringBuilder();
        Duration duration = Duration.ofMillis(milliSeconds);
        long hours = duration.toHoursPart();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();
        return String.format("%d시간 %d분 %d초",hours, minutes, seconds);
    }
}