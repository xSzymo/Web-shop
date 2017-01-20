package com.shop.others;

import java.sql.Date;
import java.util.LinkedHashSet;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shop.data.repositories.UsersRepository;
import com.shop.data.tables.Users;




@Component
public class RunAtStart {
	@Autowired
	public static UsersRepository parentRepo;

	@Autowired
	public RunAtStart(UsersRepository parentRepo) {
		RunAtStart.parentRepo = parentRepo;
	}

	@PostConstruct
	public void runAtStart() {

		
	}
 }

