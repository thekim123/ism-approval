package com.hictc.ism.dto.reserve;

import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ReserveCreateDto {

    private String leaderName;
    private StaffUserDto staffUserDto;

    @Getter
    @Builder
    public static class StaffUserDto {
        private String username;
    }


}
