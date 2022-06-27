package com.fis.training.hung.btlsprint4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class StorageDTO extends AbtrstractEntityDTO{
    private String name;
    private String location;
}
