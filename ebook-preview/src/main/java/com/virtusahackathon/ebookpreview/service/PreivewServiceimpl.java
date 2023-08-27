package com.virtusahackathon.ebookpreview.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.virtusahackathon.ebookpreview.dto.PreviewDto;
import com.virtusahackathon.ebookpreview.exception.NotFoundException;
import com.virtusahackathon.ebookpreview.model.EbookModel;
import com.virtusahackathon.ebookpreview.repository.EbookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Service
public class PreivewServiceimpl implements IPreviewService{

    @Autowired
    EbookRepository eBookRepository;
    @Override
    @HystrixCommand(fallbackMethod = "fallbackGetPreview")
    public PreviewDto getPreview(Long eBookId){
        PreviewDto previewDto = null;
        Optional<EbookModel> optionalEbook = eBookRepository.findById(eBookId);
        if (optionalEbook.isPresent()) {
            EbookModel eBook = optionalEbook.get();
            previewDto = new PreviewDto();
            String previewContent = new String(eBook.getEbookContent(), StandardCharsets.UTF_8);
            previewDto.setEBookId(eBook.getEbookId());
            previewDto.setPreviewContent(previewContent);
            previewDto.setTitle(eBook.getTitle());
        }
        throw new NotFoundException("EBook with ID " + eBookId + " not found");
    }

    public PreviewDto fallbackGetPreview(Long ebookId) {

        PreviewDto fallbackPreview = new PreviewDto();
        fallbackPreview.setEBookId(ebookId);
        fallbackPreview.setPreviewContent("Issues while retrieving content for preview");

        return fallbackPreview;
    }
}
