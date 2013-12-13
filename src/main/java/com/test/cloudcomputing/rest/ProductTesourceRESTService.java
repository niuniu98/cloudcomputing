package com.test.cloudcomputing.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.test.cloudcomputing.data.StringData;
import com.test.cloudcomputing.model.ProductEntity;
import com.test.cloudcomputing.service.Find;

@Path("/products")
public class ProductTesourceRESTService {

	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductEntity> listAllProduct() {
    	System.out.println("get");
    	Find productFindAll = new Find();
    	List<ProductEntity> allProduct = productFindAll.findAllProduct();
    	List<ProductEntity> products = new ArrayList<ProductEntity>();
    	for(int i = 0 ; i < allProduct.size() ; i++){
    		ProductEntity pe1 = allProduct.get(i);
    		ProductEntity pe2 = new ProductEntity(pe1.getRowKey(), pe1.getPartitionKey());
    		pe1.setPrice(pe1.getPrice());;
    		products.add(pe2);
    	}
    	return products;
    }
	
	@GET
	@Path("{product}")
    @Produces(MediaType.APPLICATION_JSON)
    public StringData getPrice(@PathParam("product") String product) {
    	System.out.println("price");
    	Find findPrice = new Find();
    	int returnValue = findPrice.getPrice(product);
		StringData data = new StringData();
		data.setName("price");
		data.setValue(returnValue+"");
    	return data;
    }

}
