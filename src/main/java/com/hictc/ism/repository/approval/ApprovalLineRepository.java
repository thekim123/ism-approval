package com.hictc.ism.repository.approval;

import com.hictc.ism.entity.approval.ApprovalLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApprovalLineRepository extends JpaRepository<ApprovalLine, Long> {
}
