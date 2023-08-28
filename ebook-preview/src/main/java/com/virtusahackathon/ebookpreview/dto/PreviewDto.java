package com.virtusahackathon.ebookpreview.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class PreviewDto {
    @Getter
    @Setter
    private Long eBookId;
    @Getter
    @Setter
    private String title;
    @Getter
    @Setter
    private String previewContent;
}
