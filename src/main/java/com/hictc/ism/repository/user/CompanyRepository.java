package com.hictc.ism.repository.user;

import com.hictc.ism.entity.user.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
