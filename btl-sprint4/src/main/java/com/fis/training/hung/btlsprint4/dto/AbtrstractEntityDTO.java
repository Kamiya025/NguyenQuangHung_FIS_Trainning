package com.fis.training.hung.btlsprint4.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbtrstractEntityDTO {
    private Long id;
    private int version;
    private LocalDateTime createAt;
    private LocalDateTime modifiedAt;
}
