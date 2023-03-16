package com.hictc.ism.repository.approval;

import com.hictc.ism.entity.approval.ApprovalLineConfig;
import com.hictc.ism.entity.approval.ApprovalType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApprovalLineConfigRepository extends JpaRepository<ApprovalLineConfig, Integer> {

    /**
     * 해당 결재 타입 결재선 설정을 조회하는 매서드
     * @param approvalType
     * @return 결재선 설정 리스트
     */
    List<ApprovalLineConfig> findByApprovalType(ApprovalType approvalType);
}
