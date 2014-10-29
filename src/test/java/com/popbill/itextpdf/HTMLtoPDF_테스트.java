package com.popbill.itextpdf;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;

import org.junit.Test;
import org.w3c.dom.Document;

import com.google.common.io.Files;
import com.google.common.io.Resources;
public class HTMLtoPDF_테스트 {

		
	//@Test
	public void url_to_pdf_테스트() throws IOException {
		
		PDFGenerator generator = new PDFGenerator();
		
		PDFDocument doc = generator.RenderPDf("http://localhost:8080/", "http://localhost:8080/");
		
		
		File f = new File("/Users/cream/Desktop/temp.pdf");
		
		
		Files.write(doc.getContents(), f);
		
	}
	
	@Test
	public void html_to_pdf_테스트() throws IOException {
		
		PDFGenerator generator = new PDFGenerator();
		
		Document docHtml;
		
		try {
			docHtml = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(Resources.newInputStreamSupplier(Resources.getResource("test.html")).getInput());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException( "PDF 생성을 위한 URL확인에 실패하였습니다.");
		}
		 
		PDFDocument doc =  generator.RenderPdf(docHtml,"http://localhost:8080/");
		
		File f = new File("/Users/cream/Desktop/temp.pdf");
		
		Files.write(doc.getContents(), f);
		
	}
}
