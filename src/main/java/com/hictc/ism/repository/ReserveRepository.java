package com.hictc.ism.repository;

import com.hictc.ism.domain.reserve.Reserve;
import com.hictc.ism.domain.visit.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReserveRepository extends JpaRepository<Reserve, Long> {
}
