package com.dto;

import java.util.Date;

public interface OrderInterface {
	Integer getId();
	String getUserName();
	String getPhone();
	String getOrderAddress();
	Date getOrderDate();
	Date getReceivedDate();
	Integer getStatus();
	Integer getCart();
	String getNote();
}
