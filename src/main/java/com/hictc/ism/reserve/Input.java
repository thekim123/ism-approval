package com.hictc.ism.reserve;

import com.hictc.ism.approval.Approval;
import com.hictc.ism.user.entity.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type")
//이름을 뭘로할까?
public abstract class Input {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column
    private String name;

    @Column(nullable = false)
    private VIsitPurpose purpose;

    @ManyToOne
    private Approval approval;

    @Column
    private boolean report;

    @ManyToOne
    private User reporter;

    private LocalDateTime createdAt;
    private LocalDateTime reportedAt;


}
