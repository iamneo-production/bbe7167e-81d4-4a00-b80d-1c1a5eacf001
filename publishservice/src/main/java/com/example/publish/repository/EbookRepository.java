package com.example.publish.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.publish.model.EbookModel;





@Repository
public interface EbookRepository extends JpaRepository<EbookModel, Long>{

}