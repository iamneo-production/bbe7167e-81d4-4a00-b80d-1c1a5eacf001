package com.abk.ebook.ebookapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abk.ebook.ebookapp.model.EbookModel;



@Repository
public interface EbookRepository extends JpaRepository<EbookModel, Long>{

}
