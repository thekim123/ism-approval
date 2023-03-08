package com.hictc.ism.controller;

import com.hictc.ism.service.ApprovalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class ApprovalController {

    private final ApprovalService approvalService;


}
