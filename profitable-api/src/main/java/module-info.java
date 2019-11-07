open module com.profitable.api {
	
	requires com.profitable.model;
	
	requires spring.core;
	requires spring.context;
	requires spring.beans;
	requires spring.test;
	requires spring.web;
	requires spring.security.config;
	requires spring.security.web;
	
	requires spring.boot;
	requires spring.boot.autoconfigure;
	requires spring.boot.test;
	requires spring.boot.test.autoconfigure;
	requires spring.boot.starter.test;
	
	requires spring.data.commons;
	requires spring.data.jpa;
	
	requires java.sql;
	requires java.instrument;
	requires java.annotation;
	
	requires junit;
	requires org.mockito;
	
	requires org.slf4j;
	requires org.apache.tomcat.embed.core;
	requires com.fasterxml.jackson.databind;
	requires jjwt;
	
	exports com.profitable.api;
	exports com.profitable.api.repositories;
	
}