package com.fis.training.hung.btlsprint4.controller;

import com.fis.training.hung.btlsprint4.Service.EvidenceService;
import com.fis.training.hung.btlsprint4.dto.EvidenceDTO;
import com.fis.training.hung.btlsprint4.model.Evidence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/evidence")
public class EvidenceController {
    private EvidenceService evidenceService;
    @Autowired
    public EvidenceController(EvidenceService EvidenceService) {
        this.evidenceService = EvidenceService;
    }

    @GetMapping("/")
    public List<EvidenceDTO> getAll() {

        return evidenceService.findAll().stream().map(EvidenceDTO.Mapper::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvidenceDTO> getEvidenceById(@PathVariable(name = "id") Long id) {
        Evidence evidence = evidenceService.findByEvidenceId(id);
        EvidenceDTO evidenceDTO = EvidenceDTO.Mapper.fromEntity(evidence);
        return ResponseEntity.ok().body(evidenceDTO);
    }

    @PostMapping
    public ResponseEntity<EvidenceDTO> create(@RequestBody EvidenceDTO evidenceDTO) {

        Evidence postEvidence = EvidenceDTO.Mapper.fromDTO(evidenceDTO);

        Evidence Evidence = evidenceService.createEvidence(postEvidence);
        return new ResponseEntity<EvidenceDTO>(EvidenceDTO.Mapper.fromEntity(Evidence), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EvidenceDTO> update(@PathVariable long id, @RequestBody EvidenceDTO evidenceDTO) {

        Evidence postEvidence = EvidenceDTO.Mapper.fromDTO(evidenceDTO);

        Evidence Evidence = evidenceService.updateEvidence(postEvidence);

        return new ResponseEntity<EvidenceDTO>(EvidenceDTO.Mapper.fromEntity(Evidence), HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public String deleteEvidence(@PathVariable long id) {
        evidenceService.removeEvidence(id);
        return "Evidence deleted successfully";
    }
}
