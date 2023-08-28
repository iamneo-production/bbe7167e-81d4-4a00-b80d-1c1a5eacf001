package com.example.publish.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.publish.exceptions.NotFoundException;
import com.example.publish.model.EbookModel;
import com.example.publish.service.EbookService;
import com.example.publish.service.PdfGenerationService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PdfController {

    private final EbookService ebookService;
    private final PdfGenerationService pdfGenerationService;

    public PdfController(EbookService ebookService, PdfGenerationService pdfGenerationService) {
        this.ebookService = ebookService;
        this.pdfGenerationService = pdfGenerationService;
    }

    @GetMapping(value = "/generate-pdf/{bookId}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> generatePdf(@PathVariable Long bookId) throws NotFoundException {
        EbookModel ebook = ebookService.getEbookById(bookId);
        

			 
		
        byte[] pdfBytes = pdfGenerationService.generatePdfForBook(ebook.getEbookContent());

        if (pdfBytes != null) {
            HttpHeaders headers = new HttpHeaders();

            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "ebook_" + bookId + ".pdf");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(pdfBytes);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}



