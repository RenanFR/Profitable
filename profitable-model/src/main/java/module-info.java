module com.profitable.model {
	
	requires static lombok;
	requires org.mongodb.bson;
	requires spring.data.commons;
	
	exports com.profitable.model to com.profitable.db;
	
}