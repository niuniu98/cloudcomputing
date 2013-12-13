package com.test.cloudcomputing.rest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.test.cloudcomputing.model.BookEntity;
import com.test.cloudcomputing.service.Add;
import com.test.cloudcomputing.service.Find;
import com.test.cloudcomputing.util.NumberTool;

@Path("/bookings")
public class BookingResourceRESTService {

	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<BookEntity> listAllMembers() {
    	System.out.println("get");
    	Find bookFindAll = new Find();
    	List<BookEntity> allBook = bookFindAll.findAllBooking();
    	List<BookEntity> books = new ArrayList<BookEntity>();
    	for(int i = 0 ; i < allBook.size() ; i++){
    		BookEntity b1 = allBook.get(i);
    		BookEntity b2 = new BookEntity(b1.getRowKey(), b1.getPartitionKey());
    		b2.setDateTime(b1.getDateTime());
    		b2.setPrice(b1.getPrice());
    		b2.setProductId(b1.getProductId());
    		books.add(b2);
    	}
    	return books;
    	//return producer.produceEntity();
    }

	@POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createBooking(BookEntity book) {
		Response.ResponseBuilder builder = null;
		if(validateBook(book)){
        try {
        	Calendar d = Calendar.getInstance();  
	        Date nowTime = d.getTime();  
	        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");  
	        String time = sf.format(nowTime);
        	NumberTool tool = NumberTool.getInstance();
        	book.setRowKey(tool.getUniqueString(7, false));
        	book.setPrice(tool.getPrice());
        	book.setDateTime(time);
        	System.out.println("post");
        	Add regiserBooking = new Add();
        	regiserBooking.registerBooking(book);;
            //Create an "ok" response
            builder = Response.ok();
        } catch (Exception e) {
            // Handle generic exceptions
            String response = "server errors";
            builder = Response.status(Response.Status.BAD_REQUEST).entity(response);
        }
		}else{
			String response = "data errors";
			builder = Response.status(Response.Status.BAD_REQUEST).entity(response);
			System.out.println("data mistake");
		}
		return builder.build();
    }
	
	public boolean validateBook(BookEntity book){
		if(book.getRowKey() == null ||book.getPartitionKey() =="" || book.getPartitionKey() == null || book.getPartitionKey() ==""){
			return false;
		}
		return true;
	}
}
