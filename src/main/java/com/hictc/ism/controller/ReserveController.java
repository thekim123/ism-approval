package com.hictc.ism.controller;

import com.hictc.ism.dto.reserve.ReserveCreateDto;
import com.hictc.ism.service.ReserveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reserve")
@RequiredArgsConstructor
public class ReserveController {
    private final ReserveService reserveService;

    @PostMapping
    public ResponseEntity<?> submitReserve(@RequestBody ReserveCreateDto dto) {
        reserveService.submitResult(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("방문 예약이 완료되었습니다.");
    }

}