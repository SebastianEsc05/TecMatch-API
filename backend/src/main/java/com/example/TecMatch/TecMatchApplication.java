package com.example.TecMatch;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TecMatchApplication {

	public static void main(String[] args) {
		//EntityManagerFactory emf = Persistence.createEntityManagerFactory("TecMatchPU");
		//emf.close();
        SpringApplication.run(TecMatchApplication.class, args);
	}



}