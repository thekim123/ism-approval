package com.hictc.ism.controller;

import com.hictc.ism.service.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/org")
public class OrganizationController {
    private final OrganizationService organizationService;

    /**
     * 팀장을 변경 API
     *
     * @param headId 팀장 아이디
     * @param orgId  조직 아이디
     */
    @PutMapping("/teamHd/{headId}/orgId/{orgId}")
    public ResponseEntity<?> updateTeamHead(@PathVariable Long headId, @PathVariable Long orgId) {
        organizationService.updateTeamHead(headId, orgId);
        return ResponseEntity.status(HttpStatus.OK).body("팀장 설정이 완료되었습니다.");
    }
}
