package com.fis.training.hung.btlsprint4.dto;

import com.fis.training.hung.btlsprint4.model.TrackEntry;
import com.fis.training.hung.btlsprint4.util.TrackAction;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class TrackEntryDTO extends AbtrstractEntityDTO{
    private LocalDateTime date;
    private Long detectiveId;
    private Long evidenceId;
    private TrackAction trackAction;

    public static class Mapper{
        public static  TrackEntryDTO fromEntity(TrackEntry trackEntry) {
            return TrackEntryDTO.builder().id(trackEntry.getId())
                    .version(trackEntry.getVersion())
                    .createAt(trackEntry.getCreateAt())
                    .modifiedAt(trackEntry.getModifiedAt())
                    .date(trackEntry.getDate())
                    .detectiveId(trackEntry.getDetective().getId())
                    .evidenceId(trackEntry.getEvidence().getId())
                    .trackAction(trackEntry.getTrackAction())
                    .build();
        }
        public static TrackEntry fromDTO(TrackEntryDTO trackEntryDTO) {
            return TrackEntry.builder().id(trackEntryDTO.getId())
                    .version(trackEntryDTO.getVersion())
                    .createAt(trackEntryDTO.getCreateAt())
                    .modifiedAt(trackEntryDTO.getModifiedAt())
                    .date(trackEntryDTO.getDate())
                    .trackAction(trackEntryDTO.getTrackAction())
                    .build();
        }
    }

}
