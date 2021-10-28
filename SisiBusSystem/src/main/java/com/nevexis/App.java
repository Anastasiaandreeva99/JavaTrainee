package com.nevexis;

import java.io.IOException;
import java.util.concurrent.BrokenBarrierException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
@EnableCaching
@SpringBootApplication
public class App {

	public static void main(String[] args) throws InterruptedException, BrokenBarrierException, IOException {

		SpringApplication.run(App.class, args);
	}

}
