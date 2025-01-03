package com.trillionares.tryit.image_manage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ImageManageApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImageManageApplication.class, args);
	}

}
