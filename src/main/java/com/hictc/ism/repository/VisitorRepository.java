package com.hictc.ism.repository;

import com.hictc.ism.entity.reserve.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface VisitorRepository extends JpaRepository<Visitor, Long> {

}
