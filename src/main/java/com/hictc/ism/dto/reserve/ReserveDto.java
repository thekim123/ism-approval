package com.hictc.ism.dto.reserve;

import com.hictc.ism.entity.reserve.Reserve;
import com.hictc.ism.entity.reserve.Visitor;
import com.hictc.ism.entity.user.User;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReserveDto {
    private Long id;
    private String reserveId;
    private String leaderName;
    private StaffUserDto staffUserDto;
    private List<VisitorDto> visitorList;

    public void entityToDto(Reserve entity) {
        this.id = entity.getId();
        this.reserveId = entity.getReserveId();
        this.leaderName = entity.getLeaderName();
    }


    public void withStaffUser(User user) {
        this.staffUserDto = StaffUserDto.builder()
                .username(user.getUsername())
                .build();
    }

    public void withVisitorList(List<Visitor> visitorList) {
        this.visitorList = visitorList.stream().map(v -> {
            VisitorDto dto = new VisitorDto();
            dto.entityToDto(v);
            dto.transferAssetListToDto(v.getAssetList());
            return dto;
        }).collect(Collectors.toList());
    }
}
