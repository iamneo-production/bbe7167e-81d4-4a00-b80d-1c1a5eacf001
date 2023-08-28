package com.abk.ebook.ebookapp.model;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "ebook")
public class EbookModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "ebookSequence")
	@SequenceGenerator(name ="ebookSequence",sequenceName = "ebookSequence",initialValue = 1,allocationSize = 1)
	private Long ebookId;
	@Column(name = "book_content", columnDefinition = "LONGBLOB")
	private byte[] ebookContent;
	private String title;
	private Long userId;
	private  String currentVersion;
	private Timestamp createdOn;
	private Timestamp updatedOn;
	 
	public Long getEbookId() {
		return ebookId;
	}
	public void setEbookId(Long ebookId) {
		this.ebookId = ebookId;
	}
	public byte[] getEbookContent() {
		return ebookContent;
	}
	public void setEbookContent(byte[] ebookContent) {
		this.ebookContent = ebookContent;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getCurrentVersion() {
		return currentVersion;
	}
	public void setCurrentVersion(String currentVersion) {
		this.currentVersion = currentVersion;
	}
	public Timestamp getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}
	public Timestamp getUpdatedOn() {
		return updatedOn;
	}
	public void setUpdatedOn(Timestamp updatedOn) {
		this.updatedOn = updatedOn;
	}
	
	 
	 
}

