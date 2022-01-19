package com.example.redis_session_test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class RedisSessionTestApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void redisSessionTest() throws InterruptedException {
        ResponseEntity<String> ssoKeyResponse = restTemplate.getForEntity("/ssoKey",String.class);
        String ssoKey = ssoKeyResponse.getBody();
        List<String> coockies = ssoKeyResponse.getHeaders().get("Set-Cookie");
        HttpHeaders headers = new HttpHeaders();
        headers.put(HttpHeaders.COOKIE, coockies);

        Thread.sleep(3000);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> ssoKey2Response = restTemplate.exchange("/ssoKey", HttpMethod.GET, entity, String.class);
        String ssoKey2 = ssoKey2Response.getBody();
        assertThat(ssoKey2).isEqualTo(ssoKey);

        Thread.sleep(6000);
        ResponseEntity<String> ssoKey3Response = restTemplate.exchange("/ssoKey", HttpMethod.GET, entity, String.class);
        String ssoKey3 = ssoKey3Response.getBody();
        assertThat(ssoKey3).isNotEqualTo(ssoKey);
    }
}
