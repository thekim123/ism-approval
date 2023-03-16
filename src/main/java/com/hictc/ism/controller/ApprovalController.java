package com.hictc.ism.controller;

import com.hictc.ism.service.ApprovalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/approval")
@RequiredArgsConstructor
public class ApprovalController {

    private final ApprovalService approvalService;

    /**
     * 방문요청 상신 API
     *
     * @param reserveId 방문예약 Id
     */
    @PostMapping("/request/{reserveId}")
    public ResponseEntity<?> requestReserve(@PathVariable Long reserveId) {
        approvalService.requestReserve(reserveId);
        return ResponseEntity.status(HttpStatus.CREATED).body("방문예약 상신이 완료되었습니다.");
    }

    /**
     * 결재라인 상세 조회
     *
     * @param approvalId 결재 id
     * @return 결재라인 목록
     */
    @GetMapping("/detail/{approvalId}")
    public ResponseEntity<?> getApprovalLine(@PathVariable Long approvalId) {
        return ResponseEntity.status(HttpStatus.OK).body(approvalService.getApprovalLine(approvalId));
    }
}
