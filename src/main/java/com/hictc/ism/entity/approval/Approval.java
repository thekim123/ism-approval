package com.hictc.ism.entity.approval;


import com.hictc.ism.entity.reserve.Reserve;
import com.hictc.ism.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Approval {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private ApprovalStatus approvalStatus;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "approval", fetch = FetchType.LAZY)
    private List<ApprovalLine> approvalLine;

    @OneToOne
    private Reserve reserve;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
