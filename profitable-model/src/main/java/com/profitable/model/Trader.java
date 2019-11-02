package com.profitable.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Trader {

	@Id
	@Getter(value = AccessLevel.NONE)
	private ObjectId id;
	
	private String firstName;
	
	private String lastName;

	public String getId() {
		return id.toHexString();
	}
	
}
