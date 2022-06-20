package com.hung.sprint3.btl.btl_sprint3.service.impl;

import com.hung.sprint3.btl.btl_sprint3.service.IDetectiveService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class DetectiveServiceImplTest {
    @Autowired
    IDetectiveService detectiveService;
    @Test
    void findById() {
        System.out.println(detectiveService.findById(1));
    }

    @Test
    void createDetective() {

    }

    @Test
    void updateDetective() {
    }

    @Test
    void deleteDetective() {
    }

    @Test
    void findAll() {
    }
}