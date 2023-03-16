package com.hictc.ism.service;

import com.hictc.ism.entity.user.Organization;
import com.hictc.ism.entity.user.User;
import com.hictc.ism.handler.exception.CustomApiException;
import com.hictc.ism.repository.user.OrganizationRepository;
import com.hictc.ism.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrganizationService {

    private final UserRepository userRepository;
    private final OrganizationRepository organizationRepository;

    /**
     * 팀장을 변경하는 서비스 로직
     *
     * @param headId 팀장 아이디
     * @param orgId  조직 아이디
     */
    @Transactional
    public void updateTeamHead(Long headId, Long orgId) {
        User teamHd = userRepository.findById(headId).orElseThrow(() -> {
            throw new CustomApiException("존재하지 않는 회원입니다.");
        });

        Organization org = organizationRepository.findById(orgId).orElseThrow(() -> {
            throw new CustomApiException("존재하지 않는 조직입니다.");
        });

        org.withTeamHead(teamHd);
    }
}
