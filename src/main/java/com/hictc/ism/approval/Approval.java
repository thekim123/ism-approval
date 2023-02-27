package com.hictc.ism.approval;


import com.hictc.ism.reserve.Input;
import com.hictc.ism.user.entity.User;
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
    private List<Input> input;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
