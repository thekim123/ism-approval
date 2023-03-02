package com.hictc.ism.entity.approval;


import com.hictc.ism.entity.reserve.Visitor;
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

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "approval", fetch = FetchType.LAZY)
    private List<ApprovalLine> approvalLine;

    @OneToMany
    private List<Visitor> input;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
