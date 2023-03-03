package com.hictc.ism.entity.reserve;


import com.hictc.ism.dto.reserve.VisitorCreateDto;
import com.hictc.ism.entity.approval.Approval;
import com.hictc.ism.entity.asset.Asset;
import com.hictc.ism.entity.asset.AssetType;
import com.hictc.ism.entity.user.User;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @ManyToOne
    private Reserve reserve;

    @Column(nullable = false)
    private VIsitPurpose purpose;

    @OneToMany(mappedBy = "visitor", cascade = CascadeType.ALL)
    private List<Asset> assets = new ArrayList<>();
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


    public void withAssets(VisitorCreateDto dto) {
        List<Asset> assetList = new ArrayList<>();
        dto.getAssetDtos().forEach(aDto -> {
            Asset asset = new Asset();
            asset.updateEntityFromDto(aDto);
            assetList.add(asset);
        });
        this.assets = assetList;
    }

    public void dtoToEntity(VisitorCreateDto dto) {
        this.name = dto.getName() != null ? dto.getName() : name;
        this.birthDay = dto.getBirthDay() != null ? dto.getBirthDay() : birthDay;
        this.purpose = dto.getPurpose() != null ? VIsitPurpose.valueOf(dto.getPurpose()) : purpose;
    }
}
