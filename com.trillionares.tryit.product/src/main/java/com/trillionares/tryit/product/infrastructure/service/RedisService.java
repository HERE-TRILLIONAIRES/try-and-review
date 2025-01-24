package com.trillionares.tryit.product.infrastructure.service;


import java.time.Duration;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisService {
    private final RedisTemplate<String, Object> redisTemplate;

    public void setDataExpire(String key, String value, Long expiredTime) {
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value, Duration.ofMillis(expiredTime));
    }

    public void deleteData(String key) {
        try {
            Boolean result = redisTemplate.delete(key);
            if (Boolean.TRUE.equals(result)) {
                log.info("Successfully deleted key : {}", key);
            } else {
                log.info("Failed to delete key : {}", key);
            }
        } catch (Exception e) {
            log.error("Error occurred while deleting key : {}", key, e);
        }
    }


    public List<String> getKeysByPattern(String pattern) {
        try {
            return Objects.requireNonNull(redisTemplate.keys(pattern)).stream().toList();
        } catch (Exception e) {
            log.error("Error fetching keys by pattern: {}", e.getMessage());
            return Collections.emptyList();
        }
    }

    public long getTTL(String key) {
        try {
            Long ttl = redisTemplate.getExpire(key, TimeUnit.SECONDS);
            if (ttl == null) {
                log.warn("TTL is null for key: {}", key);
                return -1;
            }
            return ttl;
        } catch (Exception e) {
            log.error("Error fetching TTL for key {}: {}", key, e.getMessage());
            return -1;
        }
    }

}
