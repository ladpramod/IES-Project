package com.ies.module.co.api.utils;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;

import javax.servlet.http.HttpServletResponse;

import com.ies.module.co.api.bindings.EdResponse;
import com.ies.module.co.api.entity.CoNoticeEntity;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Component;


@Component
public class PdfUtils {

	@SuppressWarnings("static-access")
	public void generate(HttpServletResponse response, CoNoticeEntity coNotice, File f) throws Exception {

		Document document = new Document(PageSize.A4);
		PdfWriter pdfWriter = PdfWriter.getInstance(document, response.getOutputStream());
		pdfWriter.getInstance(document, new FileOutputStream(f));
		document.open();
		// Creating font
		// Setting font style and size
		Font fontTiltle = FontFactory.getFont(FontFactory.HELVETICA);
		fontTiltle.setSize(20);
		fontTiltle.setStyle("BOLD");

		// Creating paragraph
		Paragraph paragraph = new Paragraph("Correspondence Notice \n\n", fontTiltle);

		// Aligning the paragraph in document
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);

		// Adding the created paragraph in document
		document.add(paragraph);

		fontTiltle.setSize(11);
		Paragraph topPara = new Paragraph("Your Application '"+coNotice.getPlanStatus()+"' and its valid till mentioned plan date. \n ", fontTiltle);

		// Aligning the paragraph in document
		topPara.setAlignment(Paragraph.ALIGN_CENTER);

		// Adding the created paragraph in document
		document.add(topPara);

		PdfPTable table = new PdfPTable(7);
		table.setSpacingBefore(6);

		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);

		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);

		cell.setPhrase(new Phrase("Case Number", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("PlanName", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("PlanStatus", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("DenialReason", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("EligStartDate", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("EligEndDate", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Benefit Amount", font));
		table.addCell(cell);

		table.addCell(String.valueOf(coNotice.getCaseNo()));
		table.addCell(coNotice.getPlanName());
		table.addCell(coNotice.getPlanStatus());
		table.addCell(coNotice.getDenielReason());

		if (null != coNotice.getStartDate()) {
			table.addCell(coNotice.getStartDate() + "");
		} else {
			table.addCell("N/A");
		}

		if (null != coNotice.getEndDate()) {
			table.addCell(coNotice.getEndDate() + "");
		} else {
			table.addCell("N/A");
		}

		if (null != coNotice.getBenefitAmount()) {
			table.addCell(coNotice.getBenefitAmount() + "");
		} else {
			table.addCell("N/A");
		}
		document.add(table);
		fontTiltle.setSize(11);

		Paragraph linePara = new Paragraph("\n\n \n Please visit to branch again. \n\n Thank You.. \n" +
				" IES-Rhode Ireland State, USA",fontTiltle);

		// Aligning the paragraph in document
		linePara.setAlignment(Paragraph.ALIGN_JUSTIFIED);

		// Adding the created paragraph in document
		document.add(linePara);

		document.close();
	}
}