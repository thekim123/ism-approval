package com.hictc.ism.service;

import com.hictc.ism.dto.reserve.ReserveDto;
import com.hictc.ism.entity.asset.Asset;
import com.hictc.ism.entity.reserve.Reserve;
import com.hictc.ism.entity.reserve.Visitor;
import com.hictc.ism.entity.user.User;
import com.hictc.ism.handler.exception.CustomApiException;
import com.hictc.ism.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReserveService {
    private final ReserveRepository reserveRepository;
    private final UserRepository userRepository;
    private final VisitorRepository visitorRepository;
    private final AssetRepository assetRepository;

    @Transactional
    public void submitReserve(ReserveDto dto) {
        String staffUsername = dto.getStaffUserDto().getUsername();
        User staffUser = userRepository.findByUsername(staffUsername).orElseThrow(() -> {
            throw new CustomApiException("해당 사원을 찾을 수 없습니다.");
        });


        Reserve reserveEntity = Reserve.builder()
                .reserveId(UUID.randomUUID().toString())
                .build();
        reserveEntity.dtoToEntityWhenSave(dto);
        reserveEntity.withStaffUser(staffUser);
        reserveEntity.getVisitorListFromReserveDto(dto.getVisitorList());

        List<Visitor> visitorEntities = reserveEntity.getVisitorList();
        visitorEntities.forEach(entity -> entity.withReserve(reserveEntity));

        List<Asset> assetEntities = new ArrayList<>();
        visitorEntities.forEach(v -> assetEntities.addAll(v.getAssetList()));

        System.out.println(visitorEntities.size());

        reserveRepository.save(reserveEntity);
        visitorRepository.saveAll(visitorEntities);
        assetRepository.saveAll(assetEntities);

    }


    // 피방문자 수정은 불가함.
    @Transactional
    public void updateReserve(ReserveDto dto) {
        Reserve reserveEntity = reserveRepository.findById(dto.getId()).orElseThrow(() -> {
            throw new CustomApiException("해당 예약을 찾을 수 없습니다.");
        });
        reserveEntity.dtoToEntityWhenSave(dto);

        List<Visitor> visitorList = dto.getVisitorList().stream().map(v -> {
            Visitor visitor;
            if (v.getId() != null) {
                visitor = visitorRepository.findById(v.getId()).orElseThrow(() -> {
                    throw new CustomApiException("방문객을 찾을 수 없습니다.");
                });
                visitor.dtoToEntity(v);
            } else {
                System.out.println(v.getBirthDay());
                visitor = new Visitor();
                visitor.dtoToEntity(v);
                visitor.withReserve(reserveEntity);
            }
            return visitor;
        }).collect(Collectors.toList());

        visitorRepository.saveAll(visitorList);
    }

    @Transactional
    public void deleteReserve(Long reserveId) {
        Reserve reserveEntity = reserveRepository.findById(reserveId).orElseThrow(() -> {
            throw new CustomApiException("해당 예약을 찾을 수 없습니다.");
        });
        reserveRepository.delete(reserveEntity);
    }

    @Transactional(readOnly = true)
    public ReserveDto getReserveDetail(Long reserveId) {
        Reserve entity = reserveRepository.findById(reserveId).orElseThrow(() -> {
            throw new CustomApiException("예약 정보를 찾을 수 없습니다.");
        });

        ReserveDto dto = new ReserveDto();
        dto.entityToDto(entity);
        dto.withStaffUser(entity.getStaffUser());
        dto.withVisitorList(entity.getVisitorList());
        return dto;
    }

}
