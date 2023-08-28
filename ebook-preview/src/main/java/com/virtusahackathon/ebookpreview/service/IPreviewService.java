package com.virtusahackathon.ebookpreview.service;

import com.virtusahackathon.ebookpreview.model.EbookDto;

public interface IPreviewService {
    public EbookDto getPreview(Long eBookId);
}
