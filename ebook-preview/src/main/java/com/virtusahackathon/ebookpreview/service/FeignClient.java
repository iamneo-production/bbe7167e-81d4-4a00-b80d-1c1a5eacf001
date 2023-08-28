package com.virtusahackathon.ebookpreview.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.virtusahackathon.ebookpreview.exception.NotFoundException;
import com.virtusahackathon.ebookpreview.model.EbookDto;

@org.springframework.cloud.openfeign.FeignClient("EBOOK-SERVICE")
public interface FeignClient {

	@GetMapping("/api/ebook/{bookId}")
	@HystrixCommand(fallbackMethod = "fallbackGetPreview")
	public EbookDto getEbook(@PathVariable("bookId") Long bookId) throws NotFoundException;
	
	 public default EbookDto fallbackGetPreview(Long ebookId) {

	        EbookDto fallbackPreview = new EbookDto();
	        fallbackPreview.setEbookId(ebookId);
	        fallbackPreview.setTitle("Issues while retrieving content for preview");

	        return fallbackPreview;
	    }
}
