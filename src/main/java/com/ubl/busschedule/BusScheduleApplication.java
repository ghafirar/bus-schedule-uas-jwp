package com.ubl.busschedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication (exclude = {SecurityAutoConfiguration.class})
public class BusScheduleApplication {

	public static void main(String[] args) {
		SpringApplication.run(BusScheduleApplication.class, args);

	}

}
