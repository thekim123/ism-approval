package com.hictc.ism.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hictc.ism.entity.approval.Approval;
import com.hictc.ism.entity.reserve.Reserve;
import com.hictc.ism.entity.reserve.Visitor;
import com.hictc.ism.dto.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
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
    private List<Approval> approvals = new ArrayList<>();

    @JsonIgnoreProperties({"staffUser"})
    @OneToMany(mappedBy = "staffUser", fetch = FetchType.LAZY)
    private List<Reserve> reserves = new ArrayList<>();

    @ManyToOne
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

    public User dtoToEntity(UserDto.Create dto) {
        this.username = dto.getUsername() != null ? dto.getUsername() : this.username;
        this.name = dto.getName() != null ? dto.getName() : this.name;
        this.password = dto.getPassword() != null ? dto.getPassword() : this.password;
        this.email = dto.getEmail() != null ? dto.getEmail() : this.email;
        this.birthDay = dto.getBirthDay() != null ? dto.getBirthDay() : this.birthDay;
        return this;
    }

}
