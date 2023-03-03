package com.hictc.ism.dto.reserve;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReserveCreateDto {
    private Long id;
    private String leaderName;
    private StaffUserDto staffUserDto;
    private List<VisitorCreateDto> visitorCreateDtoList;


    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StaffUserDto {
        private String username;
    }


}
