package com.hictc.ism.dto.reserve;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AssetDto {
    private Long id;
    private String name;
    private String productType;
}
