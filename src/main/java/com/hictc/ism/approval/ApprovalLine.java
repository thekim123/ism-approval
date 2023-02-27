package com.hictc.ism.approval;

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

    private Integer sequence;

    @ManyToOne
    @JoinColumn(name = "approvalId")
    private Approval approval;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
