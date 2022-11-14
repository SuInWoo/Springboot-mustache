package com.mustache.bbs.repository;

import com.mustache.bbs.domain.entity.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
    List<Hospital> findByBusinessTypeNameIn(List<String> businessTypes);

    List<Hospital> findByRoadNameAddressContainsAndBusinessTypeNameIn(String address, List<String> inClues);

    List<Hospital> findByTotalNumberOfBedsGreaterThanEqualAndTotalNumberOfBedsLessThan(Integer min, Integer max);

    List<Hospital> findByTotalNumberOfBedsBetween(Integer min, Integer max);
}
