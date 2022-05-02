package com.bcstp.helpdesk;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.bcstp.helpdesk.models.Student;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;


public class StudentPDFExporter {
	private List<Student> listStudents;

	public StudentPDFExporter(List<Student> listStudents) {
		this.listStudents = listStudents;
	}

	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.orange);
		cell.setPadding(5);

		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.black);
		
		cell.setPhrase(new Phrase("ID", font));
        table.addCell(cell);
        
		cell.setPhrase(new Phrase("Titulo", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Depto", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Autor", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Técnico", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Problema", font));
        table.addCell(cell);
        
        cell.setPhrase(new Phrase("Status", font));
        table.addCell(cell);
        
	}

	private void writeTableData(PdfPTable table) {
		for (Student student : listStudents) {
			table.addCell(String.valueOf(student.getId()));
			table.addCell(String.valueOf(student.getName()));
			table.addCell(String.valueOf(student.getDepartment()));
			table.addCell(String.valueOf(student.getUpdatedBy()));
			table.addCell(String.valueOf(student.getTecnico()));
			table.addCell(String.valueOf(student.getDescricao()));
			table.addCell(String.valueOf(student.getStatus()));
		}
	}

	public void export(HttpServletResponse response) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.darkGray);

		Paragraph p = new Paragraph("Lista de Ocorrências", font);
		p.setAlignment(Paragraph.ALIGN_CENTER);

		document.add(p);

		PdfPTable table = new PdfPTable(7);
		table.setWidthPercentage(100f);
		//table.setWidths(new float[] { 1.5f, 3.5f, 3.0f, 3.0f, 1.5f });
		table.setSpacingBefore(10);

		writeTableHeader(table);
		writeTableData(table);

		document.add(table);

		document.close();

	}
}