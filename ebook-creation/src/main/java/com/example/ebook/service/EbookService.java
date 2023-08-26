package com.example.ebook.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.ebook.dto.EbookDto;
import com.example.ebook.exception.NotFoundException;
import com.example.ebook.model.EbookModel;
import com.example.ebook.repository.EbookRepository;

@Service
public class EbookService {
	
	@Autowired
	private EbookRepository ebookRepo;

//	 public EbookModel createEbook(EbookDto ebookDto,MultipartFile file) throws IOException {
//		EbookModel ebook=new EbookModel();
//		ebook.setEbookContent(file.getBytes());
//		ebook.setTitle(ebookDto.getTitle());
//		ebook.setCurrentVersion(ebookDto.getCurrentVersion());
//		ebook.setUserId(ebookDto.getUserId());
//		Timestamp timeStamp=new Timestamp(System.currentTimeMillis());
//		ebook.setCreatedOn(timeStamp);
//		ebook.setUpdatedOn(timeStamp);
//		return ebookRepo.save(ebook);
//	}
	 
	 public EbookModel getEbookById(Long bookId) throws NotFoundException {
		 Optional<EbookModel> ebookModelOptional=ebookRepo.findById(bookId);
		 if(ebookModelOptional.isEmpty()) {
			 throw new NotFoundException("Book Not exist");
		 }
		 System.out.println(new String(ebookModelOptional.get().getEbookContent()));
		 return ebookModelOptional.get();
	 }
	 
	 public List<EbookModel> getAllEbooks(){
		 return ebookRepo.findAll();
	 }
	 
	 public String deleteBook(Long bookId) {
		ebookRepo.deleteById(bookId);
		return "book deleted";
	 }

	public EbookModel editEbook(EbookDto ebookDto) throws NotFoundException {
		EbookModel ebook=getEbookById(ebookDto.getEbookId());
		ebook.setCurrentVersion(ebook.getCurrentVersion());
		//ebook.setEbookContent(ebookDto.getEbookContent().getBytes());
		ebook.setTitle(ebookDto.getTitle());
		ebook.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
		ebook.setUserId(ebookDto.getUserId());
		return ebookRepo.save(ebook);
	}

	public EbookModel createEbook(MultipartFile ebookContent) throws IOException {
		System.out.println(new String(ebookContent.getBytes()));
		return null;
	}

	public EbookModel createEbook(MultipartFile ebookContent, String title, Long userId, String currentVersion) throws IOException {
		EbookModel ebook=new EbookModel();
		ebook.setEbookContent(ebookContent.getBytes());
		ebook.setTitle(title);
		ebook.setCurrentVersion(currentVersion);
		ebook.setUserId(userId);
		Timestamp timeStamp=new Timestamp(System.currentTimeMillis());
		ebook.setCreatedOn(timeStamp);
		ebook.setUpdatedOn(timeStamp);
		return ebookRepo.save(ebook);
	}

	public EbookModel editEbook(MultipartFile ebookContent, String title, Long userId, String currentVersion,
			Long ebookId) throws NotFoundException, IOException {
		EbookModel ebook=getEbookById(ebookId);
		ebook.setCurrentVersion(currentVersion);
		ebook.setEbookContent(ebookContent.getBytes());
		ebook.setTitle(title);
		ebook.setUpdatedOn(new Timestamp(System.currentTimeMillis()));
		ebook.setUserId(userId);
		return ebookRepo.save(ebook);
	}
}
