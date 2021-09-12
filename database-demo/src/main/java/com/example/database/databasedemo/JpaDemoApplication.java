package com.example.database.databasedemo;


import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.database.databasedemo.entity.Person;
import com.example.database.databasedemo.jdbc.PersonJdbcDao;
import com.example.database.databasedemo.jpa.PersonJpaRepository;

@SpringBootApplication
public class JpaDemoApplication implements CommandLineRunner {
	
	private Logger logger =  LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	PersonJpaRepository repository;
	
	public static void main(String[] args) {
		SpringApplication.run(JpaDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		logger.info("All users with given ID -> {}",repository.findById(10002));
		logger.info("Inserting -> {}", 
				repository.insert(new Person("Amit", "Delhi", new Date())));
		//No need to give ides as they are assogned automatically by hibernate
		logger.info("Update 10003 -> {}", 
				repository.update(new Person(10003, "Pieter", "Indore", new Date())));
		repository.deleteById(10002); // void method
		logger.info("All users -> {}",repository.findAll());
		/*
		
		
		logger.info("All users with given Location-> {}",repository.findByLoc("Amsterdam"));
		logger.info("All users with given Name-> {}",repository.findByName("Pieter"));
		logger.info("All users with given Name and Loc-> {}",repository.findByLocAndName("Amsterdam","Pieter"));
		//logger.info("Deleting 10002 -> No of Rows Deleted - {}", 
				//dao.deleteById(10002));
		
		*/
		
		// 2 other ways to do this
	}

}
