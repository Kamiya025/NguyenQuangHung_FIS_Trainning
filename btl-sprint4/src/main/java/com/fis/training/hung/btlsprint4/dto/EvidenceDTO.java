package com.fis.training.hung.btlsprint4.dto;

import com.fis.training.hung.btlsprint4.model.CriminalCase;
import com.fis.training.hung.btlsprint4.model.Detective;
import com.fis.training.hung.btlsprint4.model.Evidence;
import com.fis.training.hung.btlsprint4.model.Storage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class EvidenceDTO extends AbtrstractEntityDTO {
    private Long criminalCaseId;
    private Long storageId;
    private String number;
    private String itemName;
    private String notes;
    private Boolean archived;

    public static class Mapper {
        public static EvidenceDTO fromEntity(Evidence evidence) {
            return EvidenceDTO.builder().id(evidence.getId())
                    .version(evidence.getVersion())
                    .createAt(evidence.getCreateAt())
                    .modifiedAt(evidence.getModifiedAt())
                    .number(evidence.getNumber())
                    .criminalCaseId(evidence.getCriminalCase().getId())
                    .storageId(evidence.getStorage().getId())
                    .itemName(evidence.getItemName())
                    .notes(evidence.getNotes())
                    .archived(evidence.getArchived())
                    .build();
        }

        public static Evidence fromDTO(EvidenceDTO evidenceDTO) {

            return Evidence.builder().id(evidenceDTO.getId())
                    .version(evidenceDTO.getVersion())
                    .createAt(evidenceDTO.getCreateAt())
                    .modifiedAt(evidenceDTO.getModifiedAt())
                    .number(evidenceDTO.getNumber())
                    .criminalCase(CriminalCase.builder().id(evidenceDTO.criminalCaseId).build())
                    .storage(Storage.builder().id(evidenceDTO.storageId).build())
                    .itemName(evidenceDTO.getItemName())
                    .notes(evidenceDTO.getNotes())
                    .archived(evidenceDTO.getArchived())
                    .build();

        }
    }
}
