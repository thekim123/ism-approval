package com.hictc.ism.repository;

import com.hictc.ism.entity.approval.ApprovalLineConfig;
import com.hictc.ism.entity.approval.ApprovalType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApprovalLineConfigRepository extends JpaRepository<ApprovalLineConfig, Integer> {
    List<ApprovalLineConfig> findByApprovalType(ApprovalType approvalType);
}
