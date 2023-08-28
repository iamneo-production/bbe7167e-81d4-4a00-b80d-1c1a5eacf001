package com.virtusahackathon.ebookpreview.controller;

import com.virtusahackathon.ebookpreview.exception.NotFoundException;
import com.virtusahackathon.ebookpreview.model.EbookDto;
import com.virtusahackathon.ebookpreview.service.FeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/preview")
@EnableHystrix
@EnableDiscoveryClient
public class PreviewController {

    @Autowired
    private FeignClient fientClient;
    @GetMapping(value="/{ebookId}",produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> getPreview(@PathVariable Long ebookId) throws NotFoundException {
        EbookDto preview = fientClient.getEbook(ebookId);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "document.text");

        return new ResponseEntity<>(preview.getEbookContent(), headers, org.springframework.http.HttpStatus.OK);

    }
}
