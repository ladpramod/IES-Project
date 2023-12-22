package com.ies.module.report.Controller;

import com.ies.module.report.Bindings.ReportForm;
import com.ies.module.report.Entity.ReportEntity;
import com.ies.module.report.Exceptions.ApiException;
import com.ies.module.report.Service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/ies/report")
public class ReportController {

    @Autowired private ReportService reportService;

    @GetMapping("/getAppDetails/{caseNo}")
    public ResponseEntity<String> generateApplicantDetails(@PathVariable String caseNo){
        boolean app = reportService.getReadyReport(caseNo);
        if (!app){
            return ResponseEntity.badRequest().body("Case number '"+caseNo+"' not available...");
        }
        return ResponseEntity.ok("Applicant details added successfully..");
    }

    @PostMapping("/search")
    public ResponseEntity<List<ReportEntity>> handelSearch(@RequestBody ReportForm search) {

        try {
            List<ReportEntity> result = reportService.Search(search);
            return ResponseEntity.ok(result);

        }
        catch (Exception e){
            throw new ApiException("Data not available..");
        }
    }

    @GetMapping("/excel")
    public void downloadExel(HttpServletResponse response) throws Exception {
        response.setContentType("application/octet-stream");
        response.addHeader("Content-Disposition","attachment;filename=ApplicantReport.xls");
        reportService.importExcelReport(response);
    }

    @GetMapping("/pdf")
    public void downloadPdf(HttpServletResponse response) throws Exception {
        response.setContentType("application/pdf");
        response.addHeader("Content-Disposition","attachment;filename=ApplicantReport.pdf");
        reportService.importPdfReport(response);
    }
}
