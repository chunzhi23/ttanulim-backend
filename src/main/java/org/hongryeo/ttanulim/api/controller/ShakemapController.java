package org.hongryeo.ttanulim.api.controller;

import lombok.RequiredArgsConstructor;
import org.hongryeo.ttanulim.api.dto.ShakemapResponse;
import org.hongryeo.ttanulim.api.service.ShakemapService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/shakemap")
public class ShakemapController {

  private final ShakemapService shakemapService;

  @GetMapping("/{id}")
  public ResponseEntity<ShakemapResponse> getShakemap(@PathVariable String id) {
    ShakemapResponse shakemap = shakemapService.getShakemap(id);
    return ResponseEntity.ok(shakemap);
  }
}
