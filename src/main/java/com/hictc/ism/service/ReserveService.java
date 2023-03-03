package com.hictc.ism.service;

import com.hictc.ism.dto.reserve.ReserveCreateDto;
import com.hictc.ism.entity.asset.Asset;
import com.hictc.ism.entity.reserve.Reserve;
import com.hictc.ism.entity.reserve.Visitor;
import com.hictc.ism.handler.exception.CustomApiException;
import com.hictc.ism.repository.AssetRepository;
import com.hictc.ism.repository.ReserveRepository;
import com.hictc.ism.entity.user.User;
import com.hictc.ism.repository.UserRepository;
import com.hictc.ism.repository.VisitorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReserveService {
    private final ReserveRepository reserveRepository;
    private final UserRepository userRepository;
    private final VisitorRepository visitorRepository;
    private final AssetRepository assetRepository;

    @Transactional
    public void submitReserve(ReserveCreateDto dto) {
        User staffUser = userRepository
                .findByUsername(dto.getStaffUserDto().getUsername())
                .orElseThrow(() -> {
                    throw new CustomApiException("회원을 찾을 수 없습니다.");
                });

        Reserve reserveEntity = new Reserve();
        reserveEntity.withStaffUser(staffUser);
        reserveEntity.dtoToEntityWhenSave(dto);

        List<Visitor> visitors = new ArrayList<>();
        dto.getVisitorCreateDtoList().forEach(vDto -> {
            Visitor entity = new Visitor();
            entity.dtoToEntity(vDto);
            entity.withAssets(vDto);
            visitors.add(entity);
            System.out.println(vDto.getAssetDtos());
        });

        List<Asset> assetList = new ArrayList<>();
        visitors.forEach(v -> {
            v.withReserve(reserveEntity);
            v.getAssets().forEach(a -> a.withVisitor(v));
            assetList.addAll(v.getAssets());
        });

        assetRepository.saveAll(assetList);
        visitorRepository.saveAll(visitors);
        reserveRepository.save(reserveEntity);
    }

    @Transactional
    public void updateReserve(ReserveCreateDto dto) {
        dto.getVisitorCreateDtoList().forEach(v -> {
            Visitor entity = visitorRepository.findById(v.getId()).orElseThrow(() -> {
                throw new CustomApiException("방문객 정보를 찾을 수 없습니다.");
            });
            entity.dtoToEntity(v);
        });

        dto.getVisitorCreateDtoList().forEach(v -> {
            if (v.getAssetDtos() != null) {
                v.getAssetDtos().forEach(a -> {
                    Asset entity = assetRepository.findById(a.getId()).orElseThrow(() -> {
                        throw new CustomApiException("자산 정보를 찾을 수 없습니다.");
                    });
                    entity.updateEntityFromDto(a);
                });
            }
        });
    }

    @Transactional
    public void deleteReserve(Long reserveId) {
        Reserve reserveEntity = reserveRepository.findById(reserveId).orElseThrow(() -> {
            throw new CustomApiException("해당 예약을 찾을 수 없습니다.");
        });
        reserveRepository.delete(reserveEntity);
    }

}
