package com.ies.module.ed.api.repositories;

import com.ies.module.ed.api.entities.EligEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EligRepository extends JpaRepository<EligEntity,String> {

    public EligEntity findByCaseNo(String caseNo);


}
