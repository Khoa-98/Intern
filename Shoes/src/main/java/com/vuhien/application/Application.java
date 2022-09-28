package com.vuhien.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import com.vuhien.application.googlesheet.SheetDataService;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }

    @Autowired
    private SheetDataService sheetDataService;

    @Override
    public void run(String... args) throws Exception {
        // sheetDataService.renderProductFromGoogleSheetToDatabase();

    }

}
