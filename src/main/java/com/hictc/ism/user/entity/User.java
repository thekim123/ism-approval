package com.hictc.ism.user.entity;

import com.hictc.ism.approval.Approval;
import com.hictc.ism.user.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;

    @Column(nullable = false)
    private String name;
    private String empNo;

    @Email(message = "email 양식에 맞게 입력해주세요.")
    @Column(nullable = false, unique = true)
    @Size(min = 2, message = "email은 최소 4자 이상입니다.")
    private String email;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Approval> approvals;

    @OneToOne
    private Company company;

    @ManyToOne
    private Organization organization;

    @Column(nullable = false)
    private LocalDate birthDay;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    public User encodingPassword(String encodedPassword) {
        this.password = encodedPassword;
        return this;
    }

    public User dtoToEntity(UserDto dto) {
        this.username = dto.getUsername();
        this.name = dto.getName();
        this.password = dto.getPassword();
        this.email = dto.getEmail();
        this.birthDay = dto.getBirthDay();
        return this;
    }

}
