package com.trillionares.tryit.product.domain.repository;

import com.trillionares.tryit.product.domain.model.recruitment.RecruitmentItem;
import org.springframework.data.repository.CrudRepository;

public interface RecruitmentItemRepository extends CrudRepository<RecruitmentItem, String> {
}
