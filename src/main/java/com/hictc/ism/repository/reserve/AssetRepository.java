package com.hictc.ism.repository.reserve;

import com.hictc.ism.entity.asset.Asset;
import com.hictc.ism.entity.reserve.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssetRepository extends JpaRepository<Asset, Long> {
}
