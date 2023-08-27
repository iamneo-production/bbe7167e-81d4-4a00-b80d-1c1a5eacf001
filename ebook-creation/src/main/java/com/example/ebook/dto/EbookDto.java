package com.example.ebook.dto;

import java.sql.Timestamp;

public class EbookDto {

	private Long ebookId;
	private String ebookContent;
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
	
	public String getEbookContent() {
		return ebookContent;
	}
	public void setEbookContent(String ebookContent) {
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
	public EbookDto(Long ebookId, String ebookContent, String title, Long userId, String currentVersion,
			Timestamp createdOn, Timestamp updatedOn) {
		super();
		this.ebookId = ebookId;
		this.ebookContent = ebookContent;
		this.title = title;
		this.userId = userId;
		this.currentVersion = currentVersion;
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
	}
	
	
	
}
