package com.example.idus.config.security.jwt;

import com.example.idus.config.Code;
import com.example.idus.config.ResponseResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;

@Component
public class CustomEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, org.springframework.security.core.AuthenticationException authException) throws IOException, ServletException {
        ResponseResult result = new ResponseResult(Code.FORBIDDEN).httpStatus(HttpStatus.FORBIDDEN);

        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JSR310Module());
        om.setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss"));
        String body = om.writeValueAsString(result);

        response.setContentType("application/json; charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.getWriter().write(body);
    }
}