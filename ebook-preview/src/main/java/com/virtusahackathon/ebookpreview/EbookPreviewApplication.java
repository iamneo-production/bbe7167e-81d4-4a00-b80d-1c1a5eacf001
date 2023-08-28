package com.virtusahackathon.ebookpreview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EbookPreviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(EbookPreviewApplication.class, args);
		System.out.println("preview service");
	}

}
