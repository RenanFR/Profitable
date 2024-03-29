package com.profitable.model;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
	
	private OrderType type;
	
	private LocalDateTime execution;
	
	private Exchange exchange;
	
	private boolean executed;
	
	private List<OrderFee> fees;
	
}
