package com.ies.module.report.Utils;

import com.ies.module.report.Entity.ReportEntity;
import com.ies.module.report.Repository.ReportRepository;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@Component
public class ExcelUtils {

    @Autowired private ReportRepository reportRepository;
    public void generate(HttpServletResponse response, List<ReportEntity> records, File file) throws Exception {


        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Application Status Details");
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Name");
        headerRow.createCell(2).setCellValue("Email");
        headerRow.createCell(3).setCellValue("SSN");
        headerRow.createCell(4).setCellValue("Plan Name");
        headerRow.createCell(5).setCellValue("Plan Status");
        headerRow.createCell(6).setCellValue("Plan StartDate");
        headerRow.createCell(7).setCellValue("Plan EndDate");
        headerRow.createCell(8).setCellValue("Benefit Amount");




        //Declare one local variable to iterating row from his default value
        int dataRowIndex=1;

        //Using for each loop to iterating data from database into excel file
        //fetching all the data from repository
        for(ReportEntity form:records){

            Row rowData = sheet.createRow(dataRowIndex);
            rowData.createCell(0).setCellValue(form.getCaseNo());
            rowData.createCell(1).setCellValue(form.getApplicantName());
            rowData.createCell(2).setCellValue(form.getEmail());
            rowData.createCell(3).setCellValue(form.getSsn());
            rowData.createCell(4).setCellValue(form.getPlanName());
            rowData.createCell(5).setCellValue(form.getPlanStatus());
            if(null!=form.getStartDate()){
                rowData.createCell(6).setCellValue(form.getStartDate()+"");
            }
            if(null!=form.getEndDate()){
                rowData.createCell(7).setCellValue(form.getEndDate()+"");
            }
            else{
                rowData.createCell(6).setCellValue("-");
                rowData.createCell(7).setCellValue("-");
            }


            if(null!=form.getBenefitAmount()){
                rowData.createCell(8).setCellValue(form.getBenefitAmount());
            }
            else{
                rowData.createCell(8).setCellValue("-");
            }

            dataRowIndex++;

        }

        //create the file in current folder
        FileOutputStream fos = new FileOutputStream(file);
        workbook.write(fos);
        fos.close();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();


    }
}
