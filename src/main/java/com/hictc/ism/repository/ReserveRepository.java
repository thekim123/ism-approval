package com.hictc.ism.repository;

import com.hictc.ism.reserve.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReserveRepository extends JpaRepository<Reserve, Long> {
}
