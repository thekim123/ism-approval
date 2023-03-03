package com.hictc.ism.controller;

import com.hictc.ism.dto.reserve.ReserveCreateDto;
import com.hictc.ism.service.ReserveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reserve")
@RequiredArgsConstructor
public class ReserveController {
    private final ReserveService reserveService;

    @PostMapping
    public ResponseEntity<?> submitReserve(@RequestBody ReserveCreateDto dto) {
        reserveService.submitReserve(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("방문 예약이 완료되었습니다.");
    }

    @PutMapping
    public ResponseEntity<?> updateReserve(@RequestBody ReserveCreateDto dto) {
        reserveService.updateReserve(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("방문 예약이 수정이 완료되었습니다.");
    }

    @DeleteMapping("/{reserveId}")
    public ResponseEntity<?> deleteReserve(@PathVariable Long reserveId) {
        reserveService.deleteReserve(reserveId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("방문 예약 삭제가 완료되었습니다.");
    }


}