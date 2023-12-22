package com.ies.module.report.Utils;

import com.ies.module.report.Bindings.ReportForm;
import com.ies.module.report.Entity.ReportEntity;
import com.ies.module.report.Repository.ReportRepository;
import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@Component
public class PdfUtils {

    @Autowired
    private ReportRepository reportRepository;

    public void generator(HttpServletResponse response, List<ReportEntity> reportForms, File file) throws Exception{

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
        PdfWriter.getInstance(document,new FileOutputStream(file));

        document.open();

        Font font = FontFactory.getFont("ARIAL-BOLD");
        font.setColor(Color.BLUE);
        font.setSize(14);


        Paragraph p = new Paragraph("- APPLICANT DETAILS -",font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);


        PdfPTable table = new PdfPTable(9);


        table.setWidthPercentage(100f);

        table.setSpacingBefore(7);

        table.addCell("ID");

        table.addCell("Name");

        table.addCell("Email");

        table.addCell("SSN");

        table.addCell("Plan");

        table.addCell("Plan Status");

        table.addCell("Start Date");

        table.addCell("End Date");

        table.addCell("Amount");




        for(ReportEntity form: reportForms){

            table.addCell(String.valueOf(form.getCaseNo()));
            table.addCell(form.getApplicantName());
            table.addCell(form.getEmail());
            table.addCell(String.valueOf(form.getSsn()));
            table.addCell(form.getPlanName());
            table.addCell(form.getPlanStatus());
            table.addCell(form.getStartDate()+"");
            table.addCell(form.getEndDate()+"");
            table.addCell(String.valueOf(form.getBenefitAmount()));
        }

        document.add(table);

        document.close();
    }
}
