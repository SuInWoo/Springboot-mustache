package com.mustache.bbs.repository;

import com.mustache.bbs.domain.entity.Hospital;
import org.hibernate.Session;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class HospitalRepositoryTest {

    @Autowired
    HospitalRepository hospitalRepository;

    @Test
    @DisplayName("BusinessTypeName이 보건소 보건지소 보건진료소인 데이터가 잘 나오는지")
    void findByBusinessTypeNameIn() {
        List<String> inClues = new ArrayList<>();
        inClues.add("보건소");
        inClues.add("보건지소");
        inClues.add("보건진료소");
        List<Hospital> hospitals = hospitalRepository.findByBusinessTypeNameIn(inClues);
        for (var hospital: hospitals) {
            System.out.println(hospital.getHospitalName());
        }
    }

    @Test
    @DisplayName("주소 + 업태 구분명으로 검색")
    void findByAddressContainsAndBusinessTypNameIn() {
        List<String> inClues = new ArrayList<>();
        inClues.add("보건소");
        inClues.add("보건지소");
        inClues.add("보건진료소");
        String address = "인천광역시";
        List<Hospital> hospitals = hospitalRepository.findByRoadNameAddressContainsAndBusinessTypeNameIn(address, inClues);
        for (Hospital hospital : hospitals) {
            System.out.println(hospital.getHospitalName());
        }
    }
}