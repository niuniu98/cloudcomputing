package com.test.cloudcomputing.service;

import com.test.cloudcomputing.database.NoSQL;
import com.test.cloudcomputing.model.BookEntity;
import com.test.cloudcomputing.model.CustomerEntity;

public class Add {

	public void registerCustomer(CustomerEntity member){
		NoSQL nosql = NoSQL.getInstance();
		nosql.addCustomerEntity(member);
	}
	
	public void registerBooking(BookEntity book){
		NoSQL nosql = NoSQL.getInstance();
		nosql.addBookEntity(book);
	}
}
