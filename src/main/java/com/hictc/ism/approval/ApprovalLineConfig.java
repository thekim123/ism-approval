package com.hictc.ism.approval;

import com.hictc.ism.user.entity.Organization;
import com.hictc.ism.user.entity.User;
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
    private Long id;

    private Integer step;

    @ManyToOne
    private User requestUser;

    @ManyToOne
    private Organization organization;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
