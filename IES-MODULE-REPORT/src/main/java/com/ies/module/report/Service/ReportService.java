package com.ies.module.report.Service;

import com.ies.module.report.Bindings.ReportForm;
import com.ies.module.report.Entity.ReportEntity;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ReportService {


    public List<ReportEntity> Search(ReportForm request);

    boolean importPdfReport(HttpServletResponse response) throws Exception;

    boolean importExcelReport(HttpServletResponse response) throws Exception;

    public boolean getReadyReport(String CaseNo);
}
