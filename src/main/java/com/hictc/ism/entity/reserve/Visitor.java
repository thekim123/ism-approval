package com.hictc.ism.entity.reserve;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hictc.ism.dto.reserve.AssetDto;
import com.hictc.ism.dto.reserve.VisitorDto;
import com.hictc.ism.entity.approval.Approval;
import com.hictc.ism.entity.asset.Asset;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Visitor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    private String content;
    private String companyName;

    @ManyToOne
    private Reserve reserve;

    @Column(nullable = false)
    private VIsitPurpose purpose;

    @JsonIgnoreProperties({"visitor"})
    @OneToMany(mappedBy = "visitor", cascade = CascadeType.ALL)
    private List<Asset> assetList;
    @ManyToOne
    private Approval approval;

    @Column
    private boolean report;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDay;

    private LocalDateTime createdAt;
    private LocalDateTime reportedAt;

    private LocalDateTime updatedAt;

    public void withReserve(Reserve reserve) {
        this.reserve = reserve;
    }


    public void withAssets(VisitorDto dto) {
        List<Asset> assetList = new ArrayList<>();
        dto.getAssetList().forEach(aDto -> {
            Asset asset = new Asset();
            asset.updateEntityFromDto(aDto);
            assetList.add(asset);
        });
        this.assetList = assetList;
    }

    public void dtoToEntity(VisitorDto dto) {
        this.name = (dto.getName() != null) ? dto.getName() : name;
        this.purpose = (dto.getPurpose() != null) ? VIsitPurpose.valueOf(dto.getPurpose()) : purpose;
        this.birthDay = (dto.getBirthDay() != null) ?
                LocalDate.parse(dto.getBirthDay(), DateTimeFormatter.ofPattern("yyyy-MM-dd")) : birthDay;
        this.companyName = (dto.getCompanyName() != null) ? dto.getCompanyName() : companyName;
    }


    public void withAssets(List<AssetDto> assetDtoList) {
        this.assetList = assetDtoList.stream().map(a -> {
            Asset asset = new Asset();
            asset.dtoToEntity(a);
            asset.withVisitor(this);
            return asset;
        }).collect(Collectors.toList());
    }


}
