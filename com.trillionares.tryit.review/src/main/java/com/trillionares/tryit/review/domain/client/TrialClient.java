package com.trillionares.tryit.review.domain.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name="recruitment-service")
public interface TrialClient {

}
