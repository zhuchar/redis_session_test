package com.example.redis_session_test.config;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Component
public class SsoKeySession {

    public String getSsoKey() {
        return (String) getSession().getAttribute("ssoKey");
    }

    public void setSsoKey(String id) {
        getSession().setAttribute("ssoKey", id);
    }

//    private void invaliateSession() {
//        getSession().invalidate();
//        SecurityContextHolder.getContext().setAuthentication(null);
//    }

    private HttpSession getSession() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        return request.getSession();
    }

    private boolean isSessionExpired() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        return request.getRequestedSessionId() != null && !request.isRequestedSessionIdValid();
    }
}
