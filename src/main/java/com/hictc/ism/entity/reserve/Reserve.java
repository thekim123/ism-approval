package com.hictc.ism.entity.reserve;

import com.hictc.ism.dto.reserve.ReserveCreateDto;
import com.hictc.ism.entity.asset.Asset;
import com.hictc.ism.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Reserve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String leaderName;

    @ManyToOne
    private User staffUser;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Visitor> visitors = new ArrayList<>();

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Reserve dtoToEntityWhenSave(ReserveCreateDto dto, User staffUser) {
        this.leaderName = dto.getLeaderName();
        this.staffUser = staffUser;
        return this;
    }

}
