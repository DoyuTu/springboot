package com.doyutu.spring.async.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.AsyncRestTemplate;

import javax.annotation.Resource;
import java.util.Map;

@Component
public class SpringAsyncRest {

    private static final Logger log = LoggerFactory.getLogger(SpringAsyncRest.class);

    @Resource
    private AsyncRestTemplate asyncRestTemplate;

    public void asyncRest(String url, Map<String,?> params) {
        ListenableFuture<ResponseEntity<String>> future = asyncRestTemplate.getForEntity(url, String.class, params);

        future.addCallback(new ListenableFutureCallback<ResponseEntity<String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                log.info("http fail");
            }

            @Override
            public void onSuccess(ResponseEntity<String> stringResponseEntity) {
                log.info("on success");
                while (true) {
                    if (future.isDone()) {
                        log.info("http done");
                        log.info(stringResponseEntity.getBody());
                        break;
                    }
                }
            }
        });

    }
}
