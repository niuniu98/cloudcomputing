package com.test.cloudcomputing.service;

import java.util.List;

import com.test.cloudcomputing.database.NoSQL;
import com.test.cloudcomputing.model.BookEntity;
import com.test.cloudcomputing.model.CustomerEntity;
import com.test.cloudcomputing.model.ProductEntity;
import com.test.cloudcomputing.util.NumberTool;

public class Find {
	
	public List<CustomerEntity> findAllMember(){
		NoSQL nosql = NoSQL.getInstance();
		List<CustomerEntity> allMember = nosql.findAllMember();
		return allMember;
	}
	
	public CustomerEntity findMemberByUsername(CustomerEntity member) {
		NoSQL nosql = NoSQL.getInstance();
		CustomerEntity member1 = nosql.getMemberByUsername(member);
		return member1;
	}
	
	public List<BookEntity> findAllBooking(){
		NoSQL nosql = NoSQL.getInstance();
		List<BookEntity> allBooking = nosql.findAllBook();
		return allBooking;
	}
	public List<ProductEntity> findAllProduct(){
		NoSQL nosql = NoSQL.getInstance();
		List<ProductEntity> allProduct = nosql.findAllProduct();
		return allProduct;
	}
	
	public int getPrice(String product){
		NumberTool tool = NumberTool.getInstance();
		return tool.getPrice();
	}
}
