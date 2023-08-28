package com.virtusahackathon.ebookpreview.controller;

import com.virtusahackathon.ebookpreview.dto.PreviewDto;
import com.virtusahackathon.ebookpreview.exception.NotFoundException;
import com.virtusahackathon.ebookpreview.service.PreivewServiceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/previews")
@EnableHystrix
@EnableDiscoveryClient
public class PreviewController {

    @Autowired
    PreivewServiceimpl previewService;
    @GetMapping("/{ebookId}")
    public ResponseEntity<PreviewDto> getPreview(@PathVariable Long ebookId) throws NotFoundException {
        PreviewDto preview = previewService.getPreview(ebookId);
        if (preview != null) {
            return ResponseEntity.ok(preview);
        }
        return ResponseEntity.notFound().build();
    }
}
