package com.hictc.ism.dto.reserve;

import com.hictc.ism.entity.asset.Asset;
import com.hictc.ism.entity.reserve.Visitor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VisitorDto {
    private Long id;
    private String name;
    private String purpose;
    private String companyName;

    private String birthDay;
    private List<AssetDto> assetList;

    public void entityToDto(Visitor visitor) {
        this.id = visitor.getId();
        this.name = visitor.getName();
        this.companyName = visitor.getCompanyName();
        this.purpose = visitor.getPurpose().toString();
        this.birthDay = visitor.getBirthDay().toString();
        this.assetList = transferAssetListToDto(visitor.getAssetList());
    }

    public List<AssetDto> transferAssetListToDto(List<Asset> assetList) {
        return assetList.stream().map(a -> {
            return AssetDto.builder()
                    .productType(a.getAssetType().toString())
                    .id(a.getId())
                    .serialNumber(a.getSerialNumber())
                    .build();
        }).collect(Collectors.toList());
    }

}
