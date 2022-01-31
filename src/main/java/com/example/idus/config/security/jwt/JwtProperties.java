package com.example.idus.config.security.jwt;

public interface JwtProperties {
	String SECRET = "zaqwert";
	int EXPIRATION_TIME = 864000000; // 10일 (1/1000초)
	String TOKEN_PREFIX = "Bearer ";
	String HEADER_STRING = "Authorization";
}