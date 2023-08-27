package com.example.ebook.controller;

import java.io.ByteArrayOutputStream;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;

@RestController
public class MergeController {
	
	 @PostMapping(value = "/generate-pdf", consumes = MediaType.TEXT_HTML_VALUE, produces = MediaType.APPLICATION_PDF_VALUE)
	    public ResponseEntity<byte[]> generatePdf(@RequestBody String htmlContent) {
	        // Convert HTML to PDF
	    	htmlContent = htmlContent.replaceAll("(<br>\\s*){2,}", "<br>");
			ByteArrayOutputStream pdfOutputStream = new ByteArrayOutputStream();
			ConverterProperties converterProperties = new ConverterProperties();
			HtmlConverter.convertToPdf(htmlContent, pdfOutputStream, converterProperties);

			// Prepare the PDF for download
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_PDF);
			headers.setContentDispositionFormData("attachment", "output.pdf");

			return ResponseEntity.ok()
			        .headers(headers)
			        .contentLength(pdfOutputStream.size())
			        .contentType(MediaType.APPLICATION_PDF)
			        .body(pdfOutputStream.toByteArray());
	    }
	}
	






