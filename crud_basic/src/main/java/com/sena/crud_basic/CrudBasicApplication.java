package com.sena.crud_basic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrudBasicApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudBasicApplication.class, args);
		
		// // Generar una nueva clave HMAC-SHA256 segura:
        //Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        //byte[] keyBytes = key.getEncoded();
        //System.out.println("Generated key: " + Base64.getEncoder().encodeToString(keyBytes));
	}

}
