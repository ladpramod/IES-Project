package com.ies.module.co.api.repository;

import com.ies.module.co.api.entity.CoNoticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoNoticeRepository extends JpaRepository<CoNoticeEntity,String> {

    public CoNoticeEntity findByCaseNo(String caseNo);
}
