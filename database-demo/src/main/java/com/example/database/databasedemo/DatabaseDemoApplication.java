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

//@SpringBootApplication 
//imp. to stop running this
public class DatabaseDemoApplication implements CommandLineRunner {
	
	private Logger logger =  LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	PersonJdbcDao dao;
	
	public static void main(String[] args) {
		SpringApplication.run(DatabaseDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		logger.info("All users -> {}",dao.findAll());
		logger.info("All users with given ID -> {}",dao.findById(10002));
		logger.info("All users with given Location-> {}",dao.findByLoc("Amsterdam"));
		logger.info("All users with given Name-> {}",dao.findByName("Pieter"));
		logger.info("All users with given Name and Loc-> {}",dao.findByLocAndName("Amsterdam","Pieter"));
		//logger.info("Deleting 10002 -> No of Rows Deleted - {}", 
				//dao.deleteById(10002));
		logger.info("Inserting -> {}", 
				dao.insert(new Person(10004, "Amit", "Delhi", new Date())));
		logger.info("Update 10003 -> {}", 
				dao.update(new Person(10003, "Pieter", "Indore", new Date())));
		
		
		// 2 other ways to do this
	}

}
