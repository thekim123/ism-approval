package com.hictc.ism.entity.reserve;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hictc.ism.dto.reserve.ReserveDto;
import com.hictc.ism.dto.reserve.VisitorDto;
import com.hictc.ism.entity.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    @Column(unique = true)
    private String reserveId;

    private String leaderName;

    @JoinColumn(name = "staffUserId")
    @ManyToOne(fetch = FetchType.LAZY)
    private User staffUser;

    @JsonIgnoreProperties({"reserve"})
    @OneToMany(mappedBy = "reserve", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Visitor> visitorList;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public void withStaffUser(User user) {
        this.staffUser = user;
    }

    public void dtoToEntityWhenSave(ReserveDto dto) {
        this.leaderName = dto.getLeaderName();
    }

    public void getVisitorListFromReserveDto(List<VisitorDto> visitorDtoList) {
        this.visitorList = visitorDtoList.stream()
                .map(visitorDto -> {
                    Visitor v = new Visitor();
                    v.dtoToEntity(visitorDto);
                    v.withAssets(visitorDto.getAssetList());
                    return v;
                }).collect(Collectors.toList());
    }

}
