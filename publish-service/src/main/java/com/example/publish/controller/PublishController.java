package com.example.publish.controller;

import java.io.ByteArrayOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.publish.model.EbookDto;
import com.example.publish.service.FeignClientEbook;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;


@RestController
@RequestMapping("/api/publish")
public class PublishController {
	
	
	@Autowired
	private FeignClientEbook feignClient;
	
	 @GetMapping(value = "/generate-pdf/{bookId}", produces = MediaType.APPLICATION_PDF_VALUE)
	    public ResponseEntity<byte[]> generatePdf(@PathVariable Long bookId) {
	        // Convert HTML to PDF
	    	
	    	 EbookDto ebook=feignClient.getEbook(bookId);
	    	String htmlContent=new String(ebook.getEbookContent());
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
	    
	    @GetMapping("/test")
	    public String abc() {
	    	return "working fine";
	    }
	}
	






