package com.example.ebook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ebook.model.EbookModel;

@Repository
public interface EbookRepository extends JpaRepository<EbookModel, Long>{

}
