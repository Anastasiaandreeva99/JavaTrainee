package com.nevexis;

import java.io.IOException;
import java.util.concurrent.BrokenBarrierException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

	public static void main(String[] args) throws InterruptedException, BrokenBarrierException, IOException {

		SpringApplication.run(Main.class,args);
	}

}
