package com.hictc.ism.dto.reserve;

import com.hictc.ism.entity.reserve.Reserve;
import com.hictc.ism.entity.reserve.Visitor;
import com.hictc.ism.entity.user.User;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReserveDto {
    private Long id;
    private String leaderName;
    private StaffUserDto staffUserDto;
    private List<VisitorDto> visitorList;


    public void entityToDto(Reserve entity) {
        this.id = entity.getId();
        this.leaderName = entity.getLeaderName();
    }

    public void withStaffUser(User user) {
        this.staffUserDto = StaffUserDto.builder()
                .username(user.getUsername())
                .build();
    }

    public void withVisitorDto(List<Visitor> visitors) {
        this.visitorList = new ArrayList<>();
        visitors.forEach(v -> {

        });
    }

}
