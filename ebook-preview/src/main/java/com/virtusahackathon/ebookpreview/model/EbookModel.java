package com.virtusahackathon.ebookpreview.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
@Table(name = "ebook")
@AllArgsConstructor
@NoArgsConstructor
public class EbookModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "ebookSequence")
    @SequenceGenerator(name ="ebookSequence",sequenceName = "ebookSequence",initialValue = 1,allocationSize = 1)
    @Getter
    @Setter
    private Long ebookId;
    @Column(name = "book_content", columnDefinition = "LONGBLOB")
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
