package com.hictc.ism.controller;

import com.hictc.ism.dto.reserve.ReserveDto;
import com.hictc.ism.service.ApprovalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/approval")
@RequiredArgsConstructor
public class ApprovalController {

    private final ApprovalService approvalService;

    @GetMapping("/request")
    public void requestReserve(Long reserveId) {
        approvalService.requestReserve(reserveId);
    }


}
