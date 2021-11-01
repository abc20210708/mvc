package com.spring.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan //서블릿들을 검색해서 주입할 수 있도록 설정
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}




