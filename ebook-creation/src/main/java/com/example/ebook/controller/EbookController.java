package com.example.ebook.controller;


import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.ebook.dto.EbookDto;
import com.example.ebook.exception.NotFoundException;
import com.example.ebook.model.EbookModel;
import com.example.ebook.service.EbookService;

@RestController
@RequestMapping("/api/ebook")
public class EbookController {
	
	@Autowired
	private EbookService ebookService;
	

	@PostMapping
	public EbookModel createEbook(@RequestParam MultipartFile ebookContent,
		@RequestParam String title,
	    @RequestParam Long userId,
	    @RequestParam  String currentVersion) throws IOException {		
		return ebookService.createEbook(ebookContent,title,userId,currentVersion);
	}
	
	@PutMapping 
	EbookModel editEbook(@RequestParam MultipartFile ebookContent,
			@RequestParam String title,
		    @RequestParam Long userId,
		    @RequestParam  String currentVersion,
		    @RequestParam Long ebookId) throws NotFoundException, IOException {
		return ebookService.editEbook(ebookContent,title,userId,currentVersion,ebookId);
	}
	
	@GetMapping("/{bookId}")
	public EbookDto getEbook(@PathVariable("bookId") Long bookId) throws NotFoundException {		
		EbookModel em= ebookService.getEbookById(bookId);
		return new EbookDto(em.getEbookId(),em.getEbookContent(),em.getTitle(), 
				                   em.getUserId(), em.getCurrentVersion(),
				                   	em.getCreatedOn(), em.getUpdatedOn());
	}
	
	@GetMapping("/")
	public List<EbookModel> getAllBooks(){
		return ebookService.getAllEbooks();
	}
	
	@DeleteMapping("/{bookId}")
	public String deleteBookById(@PathVariable("bookId")Long bookId) {
		return ebookService.deleteBook(bookId);
	}
	
//	@GetMapping(value="/formatted/{ebookId}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
//	public ResponseEntity<byte[]> generatedFormattedData(@PathVariable Long ebookId) throws NotFoundException{
//		EbookModel ebook=ebookService.getEbookById(ebookId);
//		
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//        headers.setContentDispositionFormData("attachment", "document.text");
//
//        return new ResponseEntity<>(ebook.getEbookContent(), headers, org.springframework.http.HttpStatus.OK);
//
//        
//	}
}
