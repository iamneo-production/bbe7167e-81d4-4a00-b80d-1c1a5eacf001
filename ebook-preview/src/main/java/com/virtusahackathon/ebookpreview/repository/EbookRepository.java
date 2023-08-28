package com.virtusahackathon.ebookpreview.repository;

import com.virtusahackathon.ebookpreview.model.EbookModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EbookRepository extends JpaRepository<EbookModel, Long> {
}
