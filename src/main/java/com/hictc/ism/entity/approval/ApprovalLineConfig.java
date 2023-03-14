package com.hictc.ism.entity.approval;

import com.hictc.ism.entity.user.Organization;
import com.hictc.ism.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class ApprovalLineConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer step;

    @Enumerated(EnumType.STRING)
    private ApprovalType approvalType;

    @ManyToOne
    private User requestUser;

    @ManyToOne
    private Organization organization;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
