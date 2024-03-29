package com.profitable.api.security;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.profitable.api.service.TraderService;
import com.profitable.model.Trader;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
@PropertySource("classpath:token.properties")
public class TokenService {
	
	public static long EXPIRATION_TIME;
	
	public static String SECRET;
	
	public static String TOKEN_PREFIX;
	
	public static String HEADER_STRING;
	
	private static TraderService userService;
	
	public static void addTokenToResponse(HttpServletResponse response, String userName) {
		String jsonWebToken = createToken(userName);
		response.addHeader(HEADER_STRING, jsonWebToken);
	}
	
	public static String createToken(String userEmail) {
		Trader user = (Trader) userService.loadUserByUsername(userEmail);
		Map<String, Object> claims = new HashMap<>();
		claims.put("userEmail", user.getEmail());
		String profiles = user.getAuthorities().stream().map(r -> r.getAuthority()).collect(Collectors.joining(","));
		claims.put("profiles", profiles);
		String jwt = TOKEN_PREFIX + " " + Jwts.builder()
			.setSubject(user.getUsername())
			.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
			.addClaims(claims)
			.signWith(SignatureAlgorithm.HS512, SECRET)
			.compact();
		return jwt;
	}
	
	public static Authentication getTokenFromRequest(HttpServletRequest request) {
		String jwt = request.getHeader(HEADER_STRING);
		if (jwt != null) {
			String userFromToken = Jwts.parser()
					.setSigningKey(SECRET)
					.parseClaimsJws(jwt.replace((TOKEN_PREFIX + " "), ""))
					.getBody()
					.getSubject();
			if (userFromToken != null) {
				return new UsernamePasswordAuthenticationToken(userFromToken, null, Collections.emptyList());
			}
		}
		return null;
	}

	public static void setUserService(TraderService userService) {
		TokenService.userService = userService;
	}

	@Value("${token.expiration.time}")
	public void setExpirationTime(long expirationTime) {
		TokenService.EXPIRATION_TIME = expirationTime;
	}

	@Value("${token.secret}")
	public void setSecret(String secret) {
		TokenService.SECRET = secret;
	}

	@Value("${token.prefix}")
	public void setTokenPrefix(String tokenPrefix) {
		TokenService.TOKEN_PREFIX = tokenPrefix;
	}

	@Value("${token.header}")
	public void setHeaderString(String headerString) {
		TokenService.HEADER_STRING = headerString;
	}

}
