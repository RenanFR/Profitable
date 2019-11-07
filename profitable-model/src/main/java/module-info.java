module com.profitable.model {
	
	requires static lombok;
	requires transitive java.persistence;
	requires transitive org.hibernate.orm.core;
	requires transitive spring.hateoas;
	requires transitive spring.security.core;
	requires com.fasterxml.jackson.annotation;
	
	exports com.profitable.model to com.profitable.api;
	opens com.profitable.model to org.hibernate.orm.core, spring.core, spring.beans;
	    
}