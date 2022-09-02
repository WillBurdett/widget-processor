package com.widgetmicroservice.widgetprocessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class WidgetProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(WidgetProcessorApplication.class, args);
	}

}
