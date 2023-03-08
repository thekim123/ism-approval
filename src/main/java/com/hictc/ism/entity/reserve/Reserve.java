package com.hictc.ism.entity.reserve;

import com.hictc.ism.dto.reserve.ReserveDto;
import com.hictc.ism.entity.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@ToString
public class Reserve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String leaderName;

    @JoinColumn(name = "staffUserId")
    @ManyToOne(fetch = FetchType.LAZY)
    private User staffUser;

    @OneToMany(mappedBy = "reserve",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Visitor> visitorList = new ArrayList<>();

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public void withStaffUser(User user) {
        this.staffUser = user;
    }

    public void dtoToEntityWhenSave(ReserveDto dto) {
        this.leaderName = dto.getLeaderName();
    }


    public void setVisitorList(List<Visitor> visitorList) {
        this.visitorList = visitorList;
    }
}
