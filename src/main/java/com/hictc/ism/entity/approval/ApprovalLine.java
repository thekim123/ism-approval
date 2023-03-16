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
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApprovalLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer step;
    private ApprovalStatus approvalStatus;

    @ManyToOne
    private User requestUser;

    @ManyToOne
    private Organization organization;

    @ManyToOne
    @JoinColumn(name = "approvalId")
    private Approval approval;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
