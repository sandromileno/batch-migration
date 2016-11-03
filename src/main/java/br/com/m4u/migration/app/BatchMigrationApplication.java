package br.com.m4u.migration.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("br.com.m4u.migration")
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class BatchMigrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(BatchMigrationApplication.class, args);
	}
}