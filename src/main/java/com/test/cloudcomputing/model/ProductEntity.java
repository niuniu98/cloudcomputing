package com.test.cloudcomputing.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.microsoft.windowsazure.services.table.client.TableServiceEntity;
@XmlRootElement
public class ProductEntity extends TableServiceEntity{

	public ProductEntity(String productId,String type){
		this.rowKey = productId;
		this.partitionKey = type;
	}
	
	public ProductEntity(){}
	
	String price;

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	
}
