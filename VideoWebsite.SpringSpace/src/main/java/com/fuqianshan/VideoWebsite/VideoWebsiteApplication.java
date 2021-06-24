package com.fuqianshan.VideoWebsite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@SpringBootApplication // (exclude = {DataSourceAutoConfiguration.class})
@RestController
public class VideoWebsiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(VideoWebsiteApplication.class, args);
	}

	
}
