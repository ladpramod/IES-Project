package com.ies.module.dc.api.repository;

import com.ies.module.dc.api.entity.EducationEntity;
import org.springframework.data.jpa.repository.JpaRepository;



public interface EducationRepo extends JpaRepository<EducationEntity, Integer>{

    public EducationEntity findByCaseNo(String caseNo);
}
