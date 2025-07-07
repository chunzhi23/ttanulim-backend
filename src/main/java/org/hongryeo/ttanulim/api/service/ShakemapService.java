package org.hongryeo.ttanulim.api.service;

import lombok.RequiredArgsConstructor;
import org.hongryeo.ttanulim.api.dto.ShakemapResponse;
import org.hongryeo.ttanulim.api.repository.ShakemapRepository;
import org.hongryeo.ttanulim.common.domain.Shakemap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ShakemapService {

  private final ShakemapRepository shakemapRepository;

  @Transactional
  public ShakemapResponse getShakemap(String id) {
    Shakemap shakemap = shakemapRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Shakemap not found"));
    return ShakemapResponse.from(shakemap);
  }
}
