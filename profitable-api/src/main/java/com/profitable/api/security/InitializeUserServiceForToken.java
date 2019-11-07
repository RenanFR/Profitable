package com.profitable.api.security;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.profitable.api.service.TraderService;

@Component
public class InitializeUserServiceForToken {
	
	@Autowired
	private TraderService userService;
	
	@PostConstruct
	public void injectOnStatic() {
		TokenService.setUserService(userService);
	}

}
