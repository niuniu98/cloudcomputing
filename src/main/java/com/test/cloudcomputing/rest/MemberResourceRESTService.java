/*
 * JBoss, Home of Professional Open Source
 * Copyright 2013, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.test.cloudcomputing.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.test.cloudcomputing.model.CustomerEntity;
import com.test.cloudcomputing.service.Add;
import com.test.cloudcomputing.service.Find;
import com.test.cloudcomputing.util.FieldNotNullException;
import com.test.cloudcomputing.util.UserNameTakenException;

/**
 * JAX-RS Example
 * <p/>
 * This class produces a RESTful service to read/write the contents of the members table.
 */
@Path("/members")
public class MemberResourceRESTService {
    
    //private Logger log;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CustomerEntity> listAllMembers() {
    	System.out.println("get");
    	Find memberFindAll = new Find();
    	List<CustomerEntity> allCustomer = memberFindAll.findAllMember();
    	List<CustomerEntity> customers = new ArrayList<CustomerEntity>();
    	for(int i = 0 ; i < allCustomer.size() ; i++){
    		CustomerEntity ce = new CustomerEntity(allCustomer.get(i).getRowKey(), allCustomer.get(i).getPartitionKey());
    		ce.setName(allCustomer.get(i).getName());
    		customers.add(ce);
    	}
    	return customers;
    }
/*
    @GET
    @Path("/{id:[0-9][0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public CustomerEntity lookupCustomerById(@PathParam("id") long id) {
        
        return null;
    }
*/
    /**
     * Creates a new member from the values provided. Performs validation, and will return a JAX-RS response with either 200 ok,
     * or with a map of fields, and related errors.
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createMember(CustomerEntity customer) {

    	System.out.println("1:"+customer.getRowKey()+"2"+customer.getPartitionKey());
        Response.ResponseBuilder builder = null;

        try {
            validateMember(customer);
        	Add regiserMember = new Add();
        	regiserMember.registerCustomer(customer);
            //Create an "ok" response
            builder = Response.ok();
        } catch (FieldNotNullException e){
        	//Map<String, String> responseObj = new HashMap<String, String>();
        	String response = "not null";
			builder = Response.status(Response.Status.CONFLICT).entity(response);
        } catch (UserNameTakenException e) {
        	String response = "name taken";
			builder = Response.status(Response.Status.CONFLICT).entity(response);
		} catch (Exception e) {
            // Handle generic exceptions
            String response = "server errors";
            builder = Response.status(Response.Status.BAD_REQUEST).entity(response);
        }

        return builder.build();
    }

    private void validateMember(CustomerEntity customer) throws FieldNotNullException,UserNameTakenException{
    	System.out.println("validateMember");
        if(null == customer.getRowKey() || ""==customer.getRowKey()){
        	throw new FieldNotNullException("not null");
        }
        if(null != usernameAlreadyExists(customer)){
        	throw new UserNameTakenException("username taken");
        }
    }

    public CustomerEntity usernameAlreadyExists(CustomerEntity member) {
    	Find memberFindByUername = new Find();
    	CustomerEntity member1 = memberFindByUername.findMemberByUsername(member);
    	System.out.println("Member:"+member1);
        return member1;
    }
}
