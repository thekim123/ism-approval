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

    @PostMapping("/request/{reserveId}")
    public void requestReserve(@PathVariable Long reserveId) {
        approvalService.requestReserve(reserveId);
    }

    @GetMapping("/detail/{approvalId}")
    public ResponseEntity<?> getApprovalLine(@PathVariable Long approvalId) {
        return ResponseEntity.status(HttpStatus.OK).body(approvalService.getApprovalLine(approvalId));
    }
}
