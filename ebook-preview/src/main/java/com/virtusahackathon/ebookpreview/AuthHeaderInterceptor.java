package com.virtusahackathon.ebookpreview;

import feign.RequestInterceptor;

import feign.RequestTemplate;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class AuthHeaderInterceptor implements RequestInterceptor {

 

    @Override

    public void apply(RequestTemplate template) {

        // Retrieve the authorization header from the current request context

        String authorizationHeader = getAuthorizationHeaderFromCurrentRequest();

 

        // Add the authorization header to the Feign request template

        template.header("Authorization", authorizationHeader);

    }

 

    private String getAuthorizationHeaderFromCurrentRequest() {

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        if (requestAttributes != null) {

            HttpServletRequest request = requestAttributes.getRequest();

            return request.getHeader("Authorization");

        }

        return null;

    }

}