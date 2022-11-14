package com.mustache.bbs.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "nation_wide_hospitals")
public class Hospital {
    @Id
    private Integer id;

    @Column(name = "road_name_address")
    private String roadNameAddress;

    @Column(name = "hospital_name")
    private String hospitalName;
    private Integer patientRoomCount;
    private Integer totalNumberOfBeds;
    private String businessTypeName;
    private Float totalAreaSize;

    public Integer getId() {
        return id;
    }

    public String getRoadNameAddress() {
        return roadNameAddress;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public Integer getPatientRoomCount() {
        return patientRoomCount;
    }

    public Integer getTotalNumberOfBeds() {
        return totalNumberOfBeds;
    }

    public String getBusinessTypeName() {
        return businessTypeName;
    }

    public Float getTotalAreaSize() {
        return totalAreaSize;
    }
}
