package com.shop.others;

import com.configuration.DataBaseTestConfiguration;
import com.shop.data.repositories.CookiesRepository;
import com.shop.data.tables.Cookies;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.LinkedList;

import static org.junit.Assert.fail;

public class CoderTest extends DataBaseTestConfiguration {
	@Autowired
	public CookiesRepository cookiesRepository;
	public static final int HOW_MANY_TIMES_RUN_TEST = 100000;


	@Test
	public void coderTest() {
		LinkedList<String> list = new LinkedList<>();
		for (int i = 0; i < HOW_MANY_TIMES_RUN_TEST; i++)
			list.add(Coder.getUniqueCode());
		int a = 0;
		for (String x : list) {
			a = 0;
			for (String x1 : list)
				if (x.equals(x1))
					a++;
			if (a > 1)
				fail("code must be unique");
		}

		for (Cookies x : cookiesRepository.findAll())
			for (String x1 : list)
				if (x.getName().equals(x1))
					fail("code can't be same like exist cookie name");
				else if (x.getValue().equals(x1))
					fail("code can't be same like exist cookie value");
	}
}