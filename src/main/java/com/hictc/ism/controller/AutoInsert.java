package com.hictc.ism.controller;

import com.hictc.ism.entity.user.Company;
import com.hictc.ism.entity.user.Organization;
import com.hictc.ism.entity.user.User;
import com.hictc.ism.repository.user.CompanyRepository;
import com.hictc.ism.repository.user.OrganizationRepository;
import com.hictc.ism.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class AutoInsert {

    private final CompanyRepository companyRepository;
    private final OrganizationRepository organizationRepository;
    private final UserRepository userRepository;

    /**
     * 앱 시작시 테스트용 데이터를 insert 하는 메소드
     */
    @PostConstruct
    public void insertOrganization() {
        Company HICT = Company.builder()
                .companyCode("HICT")
                .companyName("HICT")
                .build();

        Integer code = 1001;
        String[] orgNameArr = {"보안관리팀", "경영지원팀", "재무팀", "비즈니스컨설팅", "AI팀"};

        List<Organization> organizations = Arrays.stream(orgNameArr)
                .map(name -> Organization.builder()
                        .name(name)
                        .code(UUID.randomUUID().toString())
                        .company(HICT)
                        .build())
                .collect(Collectors.toList());

        System.out.println(organizations);

        companyRepository.save(HICT);
        organizationRepository.saveAll(organizations);
    }
}
