package com.virtusahackathon.ebookpreview.service;

import com.virtusahackathon.ebookpreview.dto.PreviewDto;

public interface IPreviewService {
    public PreviewDto getPreview(Long eBookId);
}
