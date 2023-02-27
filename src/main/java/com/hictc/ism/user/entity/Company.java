package com.hictc.ism.user.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String companyCode;

    @Column(unique = true, nullable = false)
    private String companyName;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
