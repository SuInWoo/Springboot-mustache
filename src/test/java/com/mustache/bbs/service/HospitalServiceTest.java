package com.mustache.bbs.service;

import com.mustache.bbs.domain.dto.HospitalResponse;
import com.mustache.bbs.domain.entity.Hospital;
import com.mustache.bbs.repository.HospitalRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class HospitalServiceTest {
    private HospitalRepository hospitalRepository = Mockito.mock(HospitalRepository.class);

    private HospitalService hospitalService;

    @BeforeEach
    void setUp() {
        hospitalService = new HospitalService(hospitalRepository);
    }

    @Test
    @DisplayName("13 => 영업중")
    void openTest() {
        Hospital hospital1 = Hospital.builder()
                .id(111)
                .businessStatusCode(13)
                .build();

        Mockito.when(hospitalRepository.findById(any()))
                .thenReturn(Optional.of(hospital1));

        HospitalResponse openHospitalResponse = hospitalService.getHospital(hospital1.getId());

        assertEquals("영업중", openHospitalResponse.getBusinessStatusName());
    }

    @Test
    @DisplayName("3 => 폐업")
    void closeTest() {

        Hospital hospital2 = Hospital.builder()
                .id(222)
                .businessStatusCode(3)
                .build();

        Mockito.when(hospitalRepository.findById(any()))
                .thenReturn(Optional.of(hospital2));

        HospitalResponse closeHospitalResponse = hospitalService.getHospital(hospital2.getId());

        assertEquals("폐업", closeHospitalResponse.getBusinessStatusName());
    }
}