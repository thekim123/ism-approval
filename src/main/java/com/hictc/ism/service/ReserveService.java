package com.hictc.ism.service;

import com.hictc.ism.dto.reserve.ReserveCreateDto;
import com.hictc.ism.entity.asset.Asset;
import com.hictc.ism.entity.reserve.Reserve;
import com.hictc.ism.entity.reserve.Visitor;
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

    @Transactional
    public void submitResult(ReserveCreateDto dto) {
        User staffUser = userRepository.findByUsername(dto.getStaffUserDto().getUsername());
        Reserve reserveEntity = new Reserve().dtoToEntityWhenSave(dto, staffUser);
        List<Visitor> visitors = reserveEntity.getVisitors();

        visitorRepository.saveAll(visitors);
        reserveRepository.save(reserveEntity);
    }

}
