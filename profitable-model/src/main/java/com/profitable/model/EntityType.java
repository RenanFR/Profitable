package com.profitable.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum EntityType {
	
	TRADER("traders");
	
	@Getter
	private final String description;
	
}
