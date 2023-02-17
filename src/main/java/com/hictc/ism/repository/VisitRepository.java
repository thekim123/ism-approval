package com.hictc.ism.repository;

import com.hictc.ism.domain.user.User;
import com.hictc.ism.domain.visit.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitRepository extends JpaRepository<Visit, Long> {
}
