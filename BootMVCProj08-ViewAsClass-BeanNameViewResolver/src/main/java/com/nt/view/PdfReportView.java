package com.nt.view;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import com.nt.model.Employee;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component("pdf_report")
public class PdfReportView extends AbstractPdfView {
	
	@Override
	protected void buildPdfDocument(Map<String, Object> map, Document doc, PdfWriter writer,
			HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO Auto-generated method stub
		List<Employee> list=(List<Employee>) map.get("empsList");
		
		//add paragraph
		Paragraph para=new Paragraph("Employee Report",new Font(Font.TIMES_ROMAN,Font.DEFAULTSIZE,Font.BOLDITALIC));
		
		doc.add(para);
		//ADD TABLE CONTENT
		Table table=new Table(5,list.size());
		for(Employee emp :list) {
			table.addCell(String.valueOf(emp.getEmpno()));
			table.addCell(emp.getEname());
			table.addCell(emp.getJob());
			table.addCell(String.valueOf(emp.getSal()));
			if (emp.getDeptNo()!=null) {
				table.addCell(String.valueOf(emp.getDeptNo()));
			}else {
				table.addCell("------");
			}
			
		}
		
		doc.add(table);
		
		
		
	}
}
