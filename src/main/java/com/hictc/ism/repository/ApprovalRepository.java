package com.hictc.ism.repository;

import com.hictc.ism.entity.reserve.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApprovalRepository extends JpaRepository<Reserve, Long> {
}
