package com.test.cloudcomputing.service;

import java.util.ArrayList;
import java.util.List;

import com.test.cloudcomputing.database.NoSQL;
import com.test.cloudcomputing.model.BookEntity;
import com.test.cloudcomputing.model.CustomerEntity;

public class Report {

	public int calculateValueByCountry(String country){
		int init = 0;
		NoSQL nosql = NoSQL.getInstance();
		//find users from member in those country
		List<CustomerEntity> customerList = nosql.getMembersByCountry(country);
		//find order those user made
		List<Integer> bookAllList = new ArrayList<Integer>();
		for(int i = 0 ; i < customerList.size() ; i++){
			List<BookEntity> bookList = nosql.getOrderByUsername(customerList.get(i));
			for(int j = 0 ; j < bookList.size() ; j++){
				bookAllList.add(bookList.get(i).getPrice());
			}
		}
		//calculate
		for(int k = 0 ; k < bookAllList.size() ; k++){
			init = init + bookAllList.get(k).intValue();
		}
		return init;
	}
	
}
