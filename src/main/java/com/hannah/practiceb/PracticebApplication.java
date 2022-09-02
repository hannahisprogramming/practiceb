package com.hannah.practiceb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PracticebApplication {
	private static final Logger logger = LoggerFactory.getLogger(PracticebApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PracticebApplication.class, args);
		logger.info("\n" +
			"------------------------------------------------------------------------------------------\n" +
			"   ########  #######   ######    #######   ##     ##   ##      ######## ####### #######   \n" +
			"   ##       ##      ## ##    ##  ##        ##     ##   ##         ##    ##      ##        \n" +
			"   ##       ##      ## ##     ## ##        ##     ##   ##         ##    ##      ##        \n" +
			"   ##       ##      ## ##     ## #####     #########   ##         ##    ######  #####     \n" +
			"   ##       ##      ## ##     ## ##               ##   ##         ##    ##      ##        \n" +
			"   ##       ##      ## ##    ##  ##               ##   ##         ##    ##      ##        \n" +
			"   ########  #######   ######    #######          ##   ####### ######## ##      #######   \n" +
			"------------------------------------------------------------------------------------------\n"
		);
		logger.info("Practice Backend Application is now running! Press the Red Stop Button in Eclipse to shutdown or CTRL+C in other IDEs.");
	}
}
