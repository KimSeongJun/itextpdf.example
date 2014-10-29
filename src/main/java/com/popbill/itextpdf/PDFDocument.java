package com.popbill.itextpdf;

import java.io.IOException;

import com.itextpdf.text.pdf.PdfReader;

public class PDFDocument {

	private byte[] _pdf;
	private int PageNum;
	
	private PDFDocument() { }
	
	public static PDFDocument Create(byte[] buffer) {
		PDFDocument newDoc = new PDFDocument();
		
		newDoc._pdf = buffer;
		
		PdfReader reader = null;
		try {
			reader = new PdfReader(buffer);
			
			newDoc.PageNum = reader.getNumberOfPages();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException( "Invalid PDF Document");
		}
		finally {
			if(reader != null) reader.close();
			reader = null;
		}
		
		return newDoc;
	}
	
	public byte[] getContents() {
		return _pdf;
	}

	public int getPageNum() {
		return PageNum;
	}

}
