package com.test.cloudcomputing.model;

import javax.xml.bind.annotation.XmlRootElement;

import com.microsoft.windowsazure.services.table.client.TableServiceEntity;

@XmlRootElement
public class CustomerEntity extends TableServiceEntity {

	public CustomerEntity(String username,String country) {
        this.rowKey = username;
        this.partitionKey = country;
    }

    public CustomerEntity() { }

    String name;
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
