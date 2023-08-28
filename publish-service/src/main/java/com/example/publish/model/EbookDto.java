package com.example.publish.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;


@AllArgsConstructor
@NoArgsConstructor
public class EbookDto {
    @Getter
    @Setter
    private Long ebookId;
    @Getter
    @Setter
    private byte[] ebookContent;
    @Getter
    @Setter
    private String title;
    @Getter
    @Setter
    private Long userId;
    @Getter
    @Setter
    private  String currentVersion;
    @Getter
    @Setter
    private Timestamp createdOn;
    @Getter
    @Setter
    private Timestamp updatedOn;
}
