package com.saleanalyzer;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.saleanalyzer.service.WatcherService;

@SpringBootApplication
public class SaleAnalyzerApplication {

	public static void main(String[] args) throws IOException, InterruptedException {
		WatcherService watcherService = new WatcherService();

		watcherService.fileWatcher();
		
		SpringApplication.run(SaleAnalyzerApplication.class, args);
	}

}
