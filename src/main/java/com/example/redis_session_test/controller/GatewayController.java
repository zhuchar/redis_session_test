package com.example.redis_session_test.controller;

import com.example.redis_session_test.config.SsoKeySession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class GatewayController {

    @Autowired
    SsoKeySession ssoKeySession;

    @GetMapping(path="/ssoKey", produces = {APPLICATION_JSON_VALUE})
    public String ssoKey() {
        String ssoKey = ssoKeySession.getSsoKey();
        if(ssoKey==null) {
            ssoKeySession.setSsoKey(UUID.randomUUID().toString());
            ssoKey = ssoKeySession.getSsoKey();
        }
        return ssoKey;
    }
}
