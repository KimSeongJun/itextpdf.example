package com.popbill.itextpdf;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.itextpdf.text.pdf.BaseFont;


public class PDFGenerator {
	private ITextRenderer renderer = new ITextRenderer();
	
	public PDFGenerator() {
		ITextFontResolver resolver = renderer.getFontResolver();
		try {
			resolver.addFont("gulim.ttc", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			resolver.addFont("batang.ttc", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
		} catch (Exception e) {
			throw new RuntimeException("PDF 생성기 초기화에 실패하였습니다.");
		}
		
	}
	public PDFDocument RenderPDf(String url, String BaseURL) {
		
		Document doc;
		
		try {
			doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(url);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException( "PDF 생성을 위한 URL확인에 실패하였습니다.");
		}
		 
		return RenderPdf(doc,BaseURL);
	}

	public PDFDocument RenderPdf(Document document, String BaseURL) {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		
		try 
		{
			renderer.setDocument(document,BaseURL);
			renderer.layout();
			renderer.createPDF(os);
			
			return PDFDocument.Create(os.toByteArray());
			
		}
		catch(Exception E) {
			throw new RuntimeException( "PDF 생성에 실패하였습니다.");
		}
		finally {
			try {
				os.close();
			} catch (IOException e) {}
		}
	}


	
}
