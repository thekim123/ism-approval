package com.hictc.ism.service;

import com.hictc.ism.dto.reserve.ReserveDto;
import com.hictc.ism.dto.reserve.VisitorDto;
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
import org.modelmapper.AbstractProvider;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import org.modelmapper.TypeMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
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

        Provider<Visitor> visitorEntityProvider = new AbstractProvider<>() {
            @Override
            public Visitor get() {
                return Visitor.builder()
                        .build();
            }
        };

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(VisitorDto.class, Visitor.class).setProvider(visitorEntityProvider);

        List<Visitor> visitorEntities = dto.getVisitorList().stream()
                .map(visitorDto -> modelMapper.map(visitorDto, Visitor.class))
                .collect(Collectors.toList());

        TypeMap<ReserveDto, Reserve> typeMap = modelMapper.createTypeMap(ReserveDto.class, Reserve.class)
                .addMappings(mapper -> mapper.map(ReserveDto::getVisitorList, Reserve::setVisitorList));

        Reserve printReserve1 = modelMapper.map(ReserveDto.class, Reserve.class);
        System.out.println(printReserve1);
        typeMap.setProvider(new Provider<Reserve>() {
            @Override
            public Reserve get(ProvisionRequest<Reserve> request) {
                ReserveDto source = (ReserveDto) request.getSource();
                return Reserve.builder()
                        .leaderName(source.getLeaderName())
                        .build();
            }
        });

        Reserve printReserve2 = modelMapper.map(ReserveDto.class, Reserve.class);
        System.out.println(printReserve2);

        reserveRepository.save(printReserve2);
        visitorRepository.saveAll(visitorEntities);

    }

    @Transactional
    public void updateReserve(ReserveDto dto) {
        dto.getVisitorList().forEach(v -> {
            Visitor entity = visitorRepository.findById(v.getId()).orElseThrow(() -> {
                throw new CustomApiException("방문객 정보를 찾을 수 없습니다.");
            });
            entity.dtoToEntity(v);
        });

        dto.getVisitorList().forEach(v -> {
            if (v.getAssetList() != null) {
                v.getAssetList().forEach(a -> {
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

    @Transactional(readOnly = true)
    public void getReserveDetail(Long reserveId) {
        Reserve entity = reserveRepository.findById(reserveId).orElseThrow(() -> {
            throw new CustomApiException("예약 정보를 찾을 수 없습니다.");
        });

        ReserveDto dto = new ReserveDto();
        dto.entityToDto(entity);
        dto.withStaffUser(entity.getStaffUser());


    }

}
