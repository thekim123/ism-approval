package com.hictc.ism.dto.reserve;

import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class VisitorDto {
    private Long id;
    private String name;
    private String purpose;

    // 포스트맨으로 할 때는 왜 날짜가 null로 오지?
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDay;
    private List<AssetDto> assetList;

}
