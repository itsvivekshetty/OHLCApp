package com.upstox;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class responsible for booting up the application.
 * @author vivek
 */
@SpringBootApplication
@EnableAutoConfiguration
public class UpstoxOHLCApp {

	private static Logger log = LogManager.getLogger(UpstoxOHLCApp.class);

	public static void main(String[] args) {
		log.info("Application starting....");
		SpringApplication.run(UpstoxOHLCApp.class, args);
	}

}
