package com.hictc.ism.dto.reserve;

import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class VisitorCreateDto {
    private String name;
    private String purpose;
    private List<AssetDto> assetDtos = new ArrayList<>();
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDay;


    @Getter
    @Builder
    public static class AssetDto {
        private String name;
        private String productType;

    }


}
