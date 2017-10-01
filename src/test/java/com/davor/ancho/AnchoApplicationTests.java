package com.davor.ancho;

import com.davor.ancho.services.KeycloakClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Time;
import java.util.Random;
import java.util.concurrent.*;
import java.util.function.Function;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnchoApplicationTests {

	private final Logger log = LoggerFactory.getLogger(AnchoApplicationTests.class);



	@Test
	public void contextLoads() {

	}

}
