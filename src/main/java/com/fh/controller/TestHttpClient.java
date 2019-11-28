package com.fh.controller;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import java.io.IOException;

public class TestHttpClient {
    @Test
    public void testGet(){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpGet httpGet = new HttpGet("http://localhost:8081/resource/index.do");
        CloseableHttpResponse response = null;
        try {
             response = httpClient.execute(httpGet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
