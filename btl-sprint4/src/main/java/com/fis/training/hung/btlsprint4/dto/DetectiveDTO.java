package com.fis.training.hung.btlsprint4.dto;

import com.fis.training.hung.btlsprint4.model.CriminalCase;
import com.fis.training.hung.btlsprint4.model.Detective;

import com.fis.training.hung.btlsprint4.util.EmploymentStatus;
import com.fis.training.hung.btlsprint4.util.Rank;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class DetectiveDTO extends AbtrstractEntityDTO{

    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private LocalDateTime hiringDate;
    private String badgeNumber;
    private Rank rank;
    private Boolean armed;
    private EmploymentStatus status;
    private int criminalCasesSize;

    public static class Mapper{
        public static DetectiveDTO fromEntity(Detective detective) {
            return DetectiveDTO.builder().id(detective.getId())
                    .version(detective.getVersion())
                    .createAt(detective.getCreateAt())
                    .modifiedAt(detective.getModifiedAt())
                    .username(detective.getUsername())
                    .firstName(detective.getFirstName())
                    .lastName(detective.getLastName())
                    .password(detective.getPassword())
                    .hiringDate(detective.getHiringDate())
                    .badgeNumber(detective.getBadgeNumber())
                    .rank(detective.getRank())
                    .armed(detective.getArmed())
                    .status(detective.getStatus())
                    .criminalCasesSize(detective.getCriminalCases().size())
                    .build();
        }
        public static Detective fromDTO(DetectiveDTO detectiveDTO) {
            return Detective.builder().id(detectiveDTO.getId())
                    .version(detectiveDTO.getVersion())
                    .username(detectiveDTO.getUsername())
                    .firstName(detectiveDTO.getFirstName())
                    .lastName(detectiveDTO.getLastName())
                    .password(detectiveDTO.getPassword())
                    .hiringDate(detectiveDTO.getHiringDate())
                    .badgeNumber(detectiveDTO.getBadgeNumber())
                    .rank(detectiveDTO.getRank())
                    .armed(detectiveDTO.getArmed())
                    .status(detectiveDTO.getStatus())
                    .build();

        }
    }
}
