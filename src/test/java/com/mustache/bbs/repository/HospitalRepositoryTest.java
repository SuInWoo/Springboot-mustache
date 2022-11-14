package com.mustache.bbs.repository;

import com.mustache.bbs.domain.entity.Hospital;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class HospitalRepositoryTest {

    @Autowired
    HospitalRepository hospitalRepository;

    @Test
    void printHospitalNameAndAddress(List<Hospital> hospitals) {
        for (var hospital : hospitals) {
            System.out.printf("%s | %s %f\n", hospital.getHospitalName(), hospital.getRoadNameAddress(),
                    hospital.getTotalAreaSize());
        }
        System.out.println(hospitals.size());
    }

    @Test
    @DisplayName("BusinessTypeName이 보건소 보건지소 보건진료소인 데이터가 잘 나오는지")
    void findByBusinessTypeNameIn() {
        List<String> inClues = new ArrayList<>();
        inClues.add("보건소");
        inClues.add("보건지소");
        inClues.add("보건진료소");
        List<Hospital> hospitals = hospitalRepository.findByBusinessTypeNameIn(inClues);
        printHospitalNameAndAddress(hospitals);
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
        printHospitalNameAndAddress(hospitals);
    }

    @Test
    @DisplayName("병상 수가 10개 이상 20개 미만 병원 찾기")
    void findByTotalNumberOfBeds() {
        List<Hospital> hospitals = hospitalRepository.findByTotalNumberOfBedsGreaterThanEqualAndTotalNumberOfBedsLessThan(10, 20);
        printHospitalNameAndAddress(hospitals);
    }

}