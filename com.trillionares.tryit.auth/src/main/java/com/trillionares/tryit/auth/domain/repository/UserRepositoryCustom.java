package com.trillionares.tryit.auth.domain.repository;

import com.querydsl.core.types.Predicate;
import com.trillionares.tryit.auth.presentation.dto.responseDto.UserResponseDto;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserRepositoryCustom {

  Page<UserResponseDto> findAllByConditions(
      List<UUID> userIdList, Predicate predicate, Pageable pageable);

}
