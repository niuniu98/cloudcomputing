package com.test.cloudcomputing.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.microsoft.windowsazure.services.table.client.TableServiceEntity;
@XmlRootElement
public class BookEntity extends TableServiceEntity{
	
	public BookEntity(String bookId,String username){
		this.rowKey=bookId;
		this.partitionKey = username;
	}
	
	public BookEntity(){}
	
	String productId;
	String dateTime;
	int price;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	
}
