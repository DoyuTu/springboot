package com.doyutu.spring.async.httpasyncclient;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager;
import org.apache.http.impl.nio.reactor.DefaultConnectingIOReactor;
import org.apache.http.nio.reactor.ConnectingIOReactor;
import org.apache.http.nio.reactor.IOReactorException;

import java.io.IOException;
import java.util.concurrent.Future;

@Slf4j
public class HttpAsyncClient {

    public void httpClient() throws IOReactorException {
        ConnectingIOReactor ioReactor = new DefaultConnectingIOReactor();
        PoolingNHttpClientConnectionManager poolClient = new PoolingNHttpClientConnectionManager(ioReactor);
        poolClient.setMaxTotal(200);
        poolClient.setDefaultMaxPerRoute(200);
        CloseableHttpAsyncClient httpClient = HttpAsyncClients.createDefault();
        httpClient.start();
        HttpGet req = new HttpGet("http://www.apache.org/");
        Future<HttpResponse> execute = httpClient.execute(req, new FutureCallback<HttpResponse>() {
            @Override
            public void completed(HttpResponse result) {
                try {
                    log.info("{}-->{}",result.getStatusLine(), result.getEntity().getContent());
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Exception ex) {

            }

            @Override
            public void cancelled() {

            }
        });
    }

    public static void main(String[] args) {
//        HttpAsyncClient httpAsyncClient = new HttpAsyncClient();
//        httpAsyncClient.httpClient();
    }

}
