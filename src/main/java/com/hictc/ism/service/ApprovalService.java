package com.hictc.ism.service;

import com.hictc.ism.entity.approval.*;
import com.hictc.ism.entity.reserve.Reserve;
import com.hictc.ism.entity.user.User;
import com.hictc.ism.handler.exception.CustomApiException;
import com.hictc.ism.repository.ApprovalLineConfigRepository;
import com.hictc.ism.repository.ApprovalRepository;
import com.hictc.ism.repository.ReserveRepository;
import com.hictc.ism.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ApprovalService {
    private final ApprovalRepository approvalRepository;
    private final ReserveRepository reserveRepository;
    private final UserRepository userRepository;
    private final ApprovalLineConfigRepository approvalLineConfigRepository;


    /**
     * 방문요청 상신
     *
     * @param reserveId 방문예약 Id를 가져온다.
     */
    @Transactional
    public void requestReserve(Long reserveId) {
        Reserve reserveEntity = reserveRepository.findById(reserveId).orElseThrow(() -> {
            throw new CustomApiException("예약이 존재하지 않습니다.");
        });

        List<ApprovalLineConfig> lineConfig = approvalLineConfigRepository.findByApprovalType(ApprovalType.RESERVE);
        List<ApprovalLine> approvalLineList = buildApprovalLine(lineConfig);

        Approval approval = Approval.builder()
                .user(reserveEntity.getStaffUser())
                .reserve(reserveEntity)
                .approvalStatus(ApprovalStatus.REQUEST)
                .approvalLine(approvalLineList)
                .build();

        approvalRepository.save(approval);
    }


    public List<ApprovalLine> buildApprovalLine(List<ApprovalLineConfig> lineConfig) {

        return null;
    }
}
