package com.davor.ancho.services;

import com.davor.ancho.services.KeycloakClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KeycloakClientTest {

    private final Logger log = LoggerFactory.getLogger(KeycloakClientTest.class);

    @Autowired
    KeycloakClient keycloakClient;

    @Test
    public void testKeycloakClient() {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        try {
            executor.submit(asyncTask("dolphin"));
            executor.submit(asyncTask("nemo"));
            executor.submit(asyncTask("shell"));

            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.MINUTES);

        } catch (InterruptedException e) {
            log.error("", e);
        }
    }


    public Callable<String> asyncTask(final String resource) {
        return new Callable<String>() {
            @Override
            public String call() throws Exception {
                try {
                    for(int i = 0; i < 10; i++){
                        log.info(String.format("%s: getting the token for resource %s", Thread.currentThread().getName(), resource));
                        String token = keycloakClient.getToken(resource);
                        log.info(String.format("%s: token: %s, resource: %s", Thread.currentThread().getName(), token, resource));
                        long timeToSleep = new Random().nextInt(3) + 1;
                        log.info(String.format("%s: going to sleep for %d sec", Thread.currentThread().getName(), timeToSleep));
                        TimeUnit.SECONDS.sleep(timeToSleep);
                    }

                } catch (Exception e) {
                    log.error("", e);
                }finally {
                    return "ok";
                }
            }
        };
    }
}
