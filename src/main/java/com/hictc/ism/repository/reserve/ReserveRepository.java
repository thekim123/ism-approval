package com.hictc.ism.repository.reserve;

import com.hictc.ism.entity.reserve.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReserveRepository extends JpaRepository<Reserve, Long> {
}
