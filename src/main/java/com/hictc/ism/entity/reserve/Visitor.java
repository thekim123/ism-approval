package com.hictc.ism.entity.reserve;


import com.hictc.ism.dto.reserve.VisitorCreateDto;
import com.hictc.ism.entity.approval.Approval;
import com.hictc.ism.entity.asset.Asset;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    private Integer id;

    @Column
    private String name;
    private String content;

    @ManyToOne
    private Reserve reserve;

    @Column(nullable = false)
    private VIsitPurpose purpose;

    @OneToMany
    private List<Asset> assets = new ArrayList<>();
    @ManyToOne
    private Approval approval;

    @Column
    private boolean report;

    private LocalDate birthDay;

    private LocalDateTime createdAt;
    private LocalDateTime reportedAt;

    private LocalDateTime updatedAt;

    public Visitor dtoToEntity(VisitorCreateDto dto) {

        this.name = dto.getName() != null ? dto.getName() : name;
        this.birthDay = dto.getBirthDay() != null ? dto.getBirthDay() : birthDay;


        this.assets = dto.getAssetDtos().stream()
                .map(a -> Asset.builder()
                        .name(a.getName())
                        .build())
                .collect(Collectors.toList());

        return this;
    }
}
