package com.ies.module.report.Service.Impl;

import com.ies.module.report.Bindings.AppForm;
import com.ies.module.report.Bindings.EdResponse;
import com.ies.module.report.Bindings.ReportForm;
import com.ies.module.report.Config.ArFeignClient;
import com.ies.module.report.Config.EdFeignClient;
import com.ies.module.report.Entity.ReportEntity;
import com.ies.module.report.Repository.ReportRepository;
import com.ies.module.report.Service.ReportService;
import com.ies.module.report.Utils.EmailUtils;
import com.ies.module.report.Utils.ExcelUtils;
import com.ies.module.report.Utils.PdfUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired private ReportRepository reportRepository;
    @Autowired private EmailUtils emailUtils;
    @Autowired private PdfUtils pdfUtils;
    @Autowired private ExcelUtils excelUtils;
    @Autowired private ArFeignClient arFeignClient;
    @Autowired private EdFeignClient edFeignClient;




    @Override
    public List<ReportEntity> Search(ReportForm request) {

        ReportEntity entity = new ReportEntity();

        if(null!=request.getPlanName()&& !"".equals(request.getPlanName())){
            entity.setPlanName(request.getPlanName());
        }

        if(null!=request.getPlanStatus()&& !"".equals(request.getPlanStatus())){
            entity.setPlanStatus(request.getPlanStatus());
        }

        if(null!=request.getGender()&& !"".equals(request.getGender())){
            entity.setGender(request.getGender());
        }

        if (null != request.getStartDate() && !"".equals(request.getStartDate())) {
            String startDate = request.getStartDate();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate parse = LocalDate.parse(startDate, formatter);
            entity.setStartDate(parse);
        }

        if (null != request.getEndDate() && !"".equals(request.getEndDate())) {
            String endDate = request.getEndDate();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate parse = LocalDate.parse(endDate, formatter);
            entity.setEndDate(parse);
        }
        return reportRepository.findAll(Example.of(entity));
    }

    @Override
    public boolean importPdfReport(HttpServletResponse response) throws Exception {

        File file = new File("ApplicantReport.pdf");
        List<ReportEntity> entityList = reportRepository.findAll();
        pdfUtils.generator(response,entityList,file);

        String to = "jainpramodlad@gmail.com";
        String subject = "ApplicationReport";
        StringBuilder builder = new StringBuilder();
        builder.append("Dear Applicant,\n\n");
        builder.append("You have receiving mail, because you have been applied plan from IES-System.");
        builder.append("Please find the attached status report.");
        builder.append("\n\n Note: *** This is system generated report **");
        builder.append("\n\nThank you...");
        builder.append("\n \n \t\t\t\t\t\t IES-System");
        String body = builder.toString();
        emailUtils.emailSend(to,subject,body,file);
        file.delete();

        return true;
    }

    @Override
    public boolean importExcelReport(HttpServletResponse response) throws Exception {

        File file = new File("ApplicantReport.xls");

        List<ReportEntity> entityList = reportRepository.findAll();
        excelUtils.generate(response,entityList,file);

        String to = "jainpramodlad@gmail.com";
        String subject = "Application status report";
        StringBuilder builder = new StringBuilder();
        builder.append("Dear Applicant,");
        builder.append("You have receiving mail, because you have been applied plan from IES-System.");
        builder.append("Please find the attached status report.");
        builder.append("\n\n Note: *** This is system generated report **");
        builder.append("Thank you...");
        builder.append("\t\t\t\t\t IES-System");
        String body = builder.toString();
        emailUtils.emailSend(to,subject,body,file);
        file.delete();
        return true;
    }

    @Override
    public boolean getReadyReport(String caseNo) {

        EdResponse edDetails = edFeignClient.getEdDetails(caseNo);
        AppForm arDetails = arFeignClient.getArDetails(caseNo);

        ReportEntity entity = new ReportEntity();
        entity.setCaseNo(caseNo);
        entity.setApplicantName(arDetails.getFullName());
        entity.setGender(arDetails.getGender());
        entity.setEmail(arDetails.getEmail());
        entity.setMobile(arDetails.getMobile());
        entity.setSsn(arDetails.getSsn());
        entity.setPlanName(edDetails.getPlanName());
        entity.setPlanStatus(edDetails.getPlanStatus());
        entity.setStartDate(edDetails.getEligStartDate());
        entity.setEndDate(edDetails.getEligEndDate());
        entity.setBenefitAmount(edDetails.getBenefitAmt());
        entity.setDenielReason(entity.getDenielReason());

        reportRepository.save(entity);


        return true;
    }
}
