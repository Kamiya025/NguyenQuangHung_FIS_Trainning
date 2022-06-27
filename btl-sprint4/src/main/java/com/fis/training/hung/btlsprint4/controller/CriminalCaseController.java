package com.fis.training.hung.btlsprint4.controller;

import com.fis.training.hung.btlsprint4.Service.CriminalCaseService;
import com.fis.training.hung.btlsprint4.dto.CriminalCaseDTO;
import com.fis.training.hung.btlsprint4.model.CriminalCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/criminalcase")
public class CriminalCaseController {

    private CriminalCaseService criminalCaseService;
    @Autowired
    public CriminalCaseController(CriminalCaseService CriminalCaseService) {
        this.criminalCaseService = CriminalCaseService;
    }

    @GetMapping("/")
    public List<CriminalCaseDTO> getAll() {

        return criminalCaseService.findAll().stream().map(CriminalCaseDTO.Mapper::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CriminalCaseDTO> getCriminalCaseById(@PathVariable(name = "id") Long id) {
        CriminalCase CriminalCase = criminalCaseService.findByCriminalCaseId(id);
        CriminalCaseDTO criminalCaseDTO = CriminalCaseDTO.Mapper.fromEntity(CriminalCase);
        return ResponseEntity.ok().body(criminalCaseDTO);
    }

    @PostMapping
    public ResponseEntity<CriminalCaseDTO> create(@RequestBody CriminalCaseDTO criminalCaseDTO) {

        CriminalCase postCriminalCase = CriminalCaseDTO.Mapper.fromDTO(criminalCaseDTO);

        CriminalCase CriminalCase = criminalCaseService.createCriminalCase(postCriminalCase);
        return new ResponseEntity<CriminalCaseDTO>(CriminalCaseDTO.Mapper.fromEntity(CriminalCase), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CriminalCaseDTO> update(@PathVariable long id, @RequestBody CriminalCaseDTO criminalCaseDTO) {

        CriminalCase postCriminalCase = CriminalCaseDTO.Mapper.fromDTO(criminalCaseDTO);

        CriminalCase CriminalCase = criminalCaseService.updateCriminalCase(postCriminalCase);

        return new ResponseEntity<CriminalCaseDTO>(CriminalCaseDTO.Mapper.fromEntity(CriminalCase), HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public String deleteCriminalCase(@PathVariable long id) {
        criminalCaseService.removeCriminalCase(id);
        return "CriminalCase deleted successfully";
    }
}
