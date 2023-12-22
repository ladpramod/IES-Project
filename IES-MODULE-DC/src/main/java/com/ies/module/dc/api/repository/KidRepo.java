package com.ies.module.dc.api.repository;


import com.ies.module.dc.api.entity.KidsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KidRepo extends JpaRepository<KidsEntity, Integer> {

    public List<KidsEntity> findByCaseNo(String CaseNo);



}
