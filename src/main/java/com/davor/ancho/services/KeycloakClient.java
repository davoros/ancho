package com.davor.ancho.services;

import com.google.common.cache.*;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class KeycloakClient {

    private final Logger log = LoggerFactory.getLogger(KeycloakClient.class);

    private LoadingCache<String, String> cache;
    private final AtomicInteger counter = new AtomicInteger();

    @Value("${keycloakclient.cache.timeout}")
    private Long durationInSec;

    public String getToken(String resource) throws ExecutionException {
        return cache.get(resource);
    }

    @PostConstruct
    protected void init(){
        cache = CacheBuilder.newBuilder()
                .refreshAfterWrite(durationInSec, TimeUnit.SECONDS)
                //.expireAfterWrite(durationInSec, TimeUnit.SECONDS)
                /*
                .removalListener(new RemovalListener<String, String>() {

                    @Override
                    public void onRemoval(RemovalNotification<String, String> notification) {
                        log.info(String.format("CacheLoader: removing entry %s for key %s", notification.getValue(), notification.getKey()));
                        log.info(String.format("CacheLoader: reason for removal is %s", notification.getCause()));
                    }
                })
                */
                .build(new CacheLoader<String, String>() {


                    @Override
                    public String load(String key) throws Exception {
                        String val = counter.getAndIncrement() + "-token";
                        log.info(String.format("CacheLoader: creating new entry %s for key %s", val, key));
                        return val;
                    }

                    @Override
                    public ListenableFuture<String> reload(String key, String oldValue) throws Exception {
                        // asynchronous!
                        ListenableFutureTask<String> task;
                        task = ListenableFutureTask.create(() -> {
                            String val = counter.getAndIncrement() + "-token";
                            log.info(String.format("CacheLoader: reloading old entry %s with new entry %s for key %s", val, oldValue, key));
                            return val;
                        });

                        Executors.newSingleThreadExecutor().execute(task);
                        return task;
                    }
        });
    }


}
