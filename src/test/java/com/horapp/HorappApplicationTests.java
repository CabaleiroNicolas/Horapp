package com.horapp;

import org.junit.jupiter.api.Test;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;


@Suite
@SelectPackages("com.horapp.service")
class HorappApplicationTests {

	@Test
	void contextLoads() {
	}

}
