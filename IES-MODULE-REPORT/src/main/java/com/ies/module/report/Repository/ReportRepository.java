package com.ies.module.report.Repository;

import com.ies.module.report.Entity.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<ReportEntity, Integer> {


    public ReportEntity findByCaseNo(String caseNo);
}
