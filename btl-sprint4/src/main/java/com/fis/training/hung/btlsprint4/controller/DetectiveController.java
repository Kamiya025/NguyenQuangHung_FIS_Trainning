package com.fis.training.hung.btlsprint4.controller;

import com.fis.training.hung.btlsprint4.Service.DetectiveService;
import com.fis.training.hung.btlsprint4.dto.CriminalCaseDTO;
import com.fis.training.hung.btlsprint4.dto.DetectiveDTO;
import com.fis.training.hung.btlsprint4.model.CriminalCase;
import com.fis.training.hung.btlsprint4.model.Detective;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/detective")
public class DetectiveController {


    private DetectiveService detectiveService;
    @Autowired
    public DetectiveController(DetectiveService detectiveService) {
        this.detectiveService = detectiveService;
    }

    @GetMapping("/")
    public List<DetectiveDTO> getAll() {

        return detectiveService.findAll().stream().map(DetectiveDTO.Mapper::fromEntity)
                        .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetectiveDTO> getDetectiveById(@PathVariable(name = "id") Long id) {
        Detective detective = detectiveService.findByDetectiveId(id);
        DetectiveDTO detectiveDTO = DetectiveDTO.Mapper.fromEntity(detective);
        return ResponseEntity.ok().body(detectiveDTO);
    }

    @PostMapping
    public ResponseEntity<DetectiveDTO> create(@RequestBody DetectiveDTO detectiveDTO) {

        Detective postDetective = DetectiveDTO.Mapper.fromDTO(detectiveDTO);

        Detective detective = detectiveService.createDetective(postDetective);

        return new ResponseEntity<DetectiveDTO>(DetectiveDTO.Mapper.fromEntity(detective), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetectiveDTO> update(@PathVariable long id, @RequestBody DetectiveDTO detectiveDTO) {

        Detective postDetective = DetectiveDTO.Mapper.fromDTO(detectiveDTO);

        Detective detective = detectiveService.updateDetective(postDetective);

        return new ResponseEntity<DetectiveDTO>(DetectiveDTO.Mapper.fromEntity(detective), HttpStatus.CREATED);
    }
    @PutMapping("/{id}/addCase/{caseId}")
    public ResponseEntity<DetectiveDTO> addCase(@PathVariable long id, @PathVariable long caseId) {

        Detective detective = detectiveService.addCriminalCase(id,caseId);

        return new ResponseEntity<DetectiveDTO>(DetectiveDTO.Mapper.fromEntity(detective), HttpStatus.CREATED);
    }
    @PutMapping("/{id}/removeCase/{caseId}")
    public ResponseEntity<DetectiveDTO> removeCase(@PathVariable long id, @PathVariable long caseId) {

        Detective detective = detectiveService.removeCriminalCase(id,caseId);

        return new ResponseEntity<DetectiveDTO>(DetectiveDTO.Mapper.fromEntity(detective), HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public String deleteDetective(@PathVariable long id) {
        detectiveService.removeDetective(id);
        return "detective deleted successfully";
    }

}
