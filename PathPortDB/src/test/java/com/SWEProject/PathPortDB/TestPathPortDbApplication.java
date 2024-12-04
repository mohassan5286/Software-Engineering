package com.SWEProject.PathPortDB;

import org.springframework.boot.SpringApplication;

public class TestPathPortDbApplication {

	public static void main(String[] args) {
		SpringApplication.from(PathPortDbApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
