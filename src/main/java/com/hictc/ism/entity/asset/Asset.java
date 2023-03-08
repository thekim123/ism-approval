package com.hictc.ism.entity.asset;

import com.hictc.ism.dto.reserve.AssetDto;
import com.hictc.ism.dto.reserve.VisitorDto;
import com.hictc.ism.entity.reserve.Visitor;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String serialNumber;

    @ManyToOne
    private Visitor visitor;

    @Enumerated(EnumType.STRING)
    private AssetType assetType;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public void withVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

    public void updateEntityFromDto(AssetDto dto) {
        this.name = dto.getName() != null ? dto.getName() : name;
        this.assetType = (dto.getProductType() != null)
                ? AssetType.valueOf(dto.getProductType()) : assetType;
    }
}
