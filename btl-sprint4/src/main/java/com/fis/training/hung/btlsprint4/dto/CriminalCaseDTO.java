package com.fis.training.hung.btlsprint4.dto;

import com.fis.training.hung.btlsprint4.model.CriminalCase;
import com.fis.training.hung.btlsprint4.model.Detective;
import com.fis.training.hung.btlsprint4.model.Evidence;
import com.fis.training.hung.btlsprint4.util.CaseStatus;
import com.fis.training.hung.btlsprint4.util.CaseType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Set;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CriminalCaseDTO extends AbtrstractEntityDTO {
    private String number;
    private CaseType type;
    private String shortDescription;
    private String detailedDescription;
    private CaseStatus status;
    private String notes;
    private Long leadInvestigatorId;

    public static class Mapper {
        public static CriminalCaseDTO fromEntity(CriminalCase criminalCase) {
            return CriminalCaseDTO.builder().id(criminalCase.getId())
                    .version(criminalCase.getVersion())
                    .createAt(criminalCase.getCreateAt())
                    .modifiedAt(criminalCase.getModifiedAt())
                    .number(criminalCase.getNumber())
                    .type(criminalCase.getType())
                    .shortDescription(criminalCase.getShortDescription())
                    .detailedDescription(criminalCase.getDetailedDescription())
                    .status(criminalCase.getStatus())
                    .notes(criminalCase.getNotes())
                    .leadInvestigatorId(criminalCase.getLeadInvestigator().getId())
                    .build();
        }

        public static CriminalCase fromDTO(CriminalCaseDTO criminalCaseDTO) {

            return CriminalCase.builder().id(criminalCaseDTO.getId())
                    .version(criminalCaseDTO.getVersion())
                    .number(criminalCaseDTO.getNumber())
                    .type(criminalCaseDTO.getType())
                    .shortDescription(criminalCaseDTO.getShortDescription())
                    .detailedDescription(criminalCaseDTO.getDetailedDescription())
                    .status(criminalCaseDTO.getStatus())
                    .notes(criminalCaseDTO.getNotes())
                    .leadInvestigator(Detective.builder().id(criminalCaseDTO.getLeadInvestigatorId()).build())
                    .build();

        }
    }
}
