package com.test.cloudcomputing.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.test.cloudcomputing.data.StringData;
import com.test.cloudcomputing.service.Report;

@Path("/reports")
public class ReportResourceRESTService {
	
	@GET
	@Path("{country}")
	@Produces(MediaType.APPLICATION_JSON)
	public StringData calculate(@PathParam("country") String country){
		System.out.println("country:"+country);
		Report countryValueReport = new Report();
		int returnValue = countryValueReport.calculateValueByCountry(country);
		StringData data = new StringData();
		data.setName("add");
		data.setValue(returnValue+"");
		return data;
	}
}
