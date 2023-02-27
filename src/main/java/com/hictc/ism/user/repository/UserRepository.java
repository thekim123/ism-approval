package com.hictc.ism.user.repository;

import com.hictc.ism.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
