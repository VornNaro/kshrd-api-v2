package com.kshrd.kshrd_websiteapi;

import com.kshrd.kshrd_websiteapi.service.implement.FilesStorageServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KshrdWebsiteApiApplication implements CommandLineRunner {

    @Autowired
    FilesStorageServiceImp filesStorageService;

    public static void main(String[] args) {
        SpringApplication.run(KshrdWebsiteApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        filesStorageService.init();
    }
}
