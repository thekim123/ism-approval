package com.hictc.ism.service;

import com.hictc.ism.dto.approval.ApprovalLineDto;
import com.hictc.ism.entity.approval.*;
import com.hictc.ism.entity.reserve.Reserve;
import com.hictc.ism.entity.user.User;
import com.hictc.ism.handler.exception.CustomApiException;
import com.hictc.ism.repository.approval.ApprovalLineConfigRepository;
import com.hictc.ism.repository.approval.ApprovalLineRepository;
import com.hictc.ism.repository.approval.ApprovalRepository;
import com.hictc.ism.repository.reserve.ReserveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApprovalService {
    private final ApprovalRepository approvalRepository;
    private final ApprovalLineRepository approvalLineRepository;
    private final ReserveRepository reserveRepository;
    private final ApprovalLineConfigRepository approvalLineConfigRepository;


    /**
     * 방문요청 상신
     *
     * @param reserveId 방문예약 Id
     */
    @Transactional
    public void requestReserve(Long reserveId) {
        Reserve reserveEntity = reserveRepository.findById(reserveId).orElseThrow(() -> {
            throw new CustomApiException("예약이 존재하지 않습니다.");
        });

        List<ApprovalLine> approvalLineList = new ArrayList<>();
        Approval approval = Approval.builder()
                .user(reserveEntity.getStaffUser())
                .reserve(reserveEntity)
                .approvalStatus(ApprovalStatus.REQUEST)
                .approvalLine(approvalLineList)
                .build();

        List<ApprovalLineConfig> lineConfig = approvalLineConfigRepository.findByApprovalType(ApprovalType.RESERVE);
        approvalLineList = buildApprovalLine(lineConfig, reserveEntity.getStaffUser(), approval);

        approvalRepository.save(approval);
        approvalLineRepository.saveAll(approvalLineList);
    }

    /**
     * 결재선을 만드는 메서드
     *
     * @param lineConfig  결재선 설정 엔티티
     * @param approval    결재 Entity
     * @param requestUser 상신 사원 Entity
     * @return 결재선 리스트
     */
    public List<ApprovalLine> buildApprovalLine(List<ApprovalLineConfig> lineConfig, User requestUser, Approval approval) {
        List<ApprovalLine> lineList = new ArrayList<>();
        lineConfig.forEach(l -> {
            ApprovalLine line;
            if (l.getStep() == 1) {
                line = ApprovalLine.builder()
                        .step(l.getStep())
                        .approvalStatus(ApprovalStatus.APPROVED)
                        .requestUser(requestUser)
                        .organization(requestUser.getOrganization())
                        .approval(approval)
                        .build();
            } else {
                line = ApprovalLine.builder()
                        .step(l.getStep())
                        .approvalStatus(ApprovalStatus.APPROVAL)
                        .requestUser(requestUser)
                        .organization(requestUser.getOrganization())
                        .approval(approval)
                        .build();
            }
            lineList.add(line);
        });
        return lineList;
    }

    /**
     * 결재 라인을 조회하는 매서드
     *
     * @param approvalId 결재 아이디
     * @return 결재라인 리스트
     */
    @Transactional(readOnly = true)
    public List<ApprovalLineDto> getApprovalLine(Long approvalId) {
        Approval approval = approvalRepository.findById(approvalId).orElseThrow(() -> {
            throw new CustomApiException("해당 결제를 찾을 수 없습니다.");
        });

        List<ApprovalLineDto> dtoList = new ArrayList<>();
        approval.getApprovalLine().forEach(line -> {
            ApprovalLineDto dto = new ApprovalLineDto();
            dto.entityToDto(line);
            dtoList.add(dto);
        });
        return dtoList;
    }
}
