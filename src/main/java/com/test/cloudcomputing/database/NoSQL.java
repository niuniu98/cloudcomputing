package com.test.cloudcomputing.database;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.List;

import com.microsoft.windowsazure.services.core.storage.CloudStorageAccount;
import com.microsoft.windowsazure.services.core.storage.StorageException;
import com.microsoft.windowsazure.services.table.client.CloudTableClient;
import com.microsoft.windowsazure.services.table.client.TableConstants;
import com.microsoft.windowsazure.services.table.client.TableOperation;
import com.microsoft.windowsazure.services.table.client.TableQuery;
import com.microsoft.windowsazure.services.table.client.TableQuery.QueryComparisons;
import com.test.cloudcomputing.model.BookEntity;
import com.test.cloudcomputing.model.CustomerEntity;
import com.test.cloudcomputing.model.ProductEntity;

public class NoSQL {

	private static final String storageConnectionString = 
		    "DefaultEndpointsProtocol=http;" + 
		    "AccountName=csc8110hdi;" + 
		    "AccountKey=3AbdLWkic6n4xNrsoPAqvBwodUTFJkTq34KC6+C2yGlSEzAGu/klCeQ8X5O+4nXeLUjmYfndl2kC5Jsl8jGlYQ==";
	private static NoSQL nosql;
	
	private NoSQL(){
		initDatabase();
		initBookData();
		initProductData();
	}
	
	public static NoSQL getInstance(){
		if(nosql == null){
			nosql = new NoSQL();
		}
		return nosql;
	}
	
	private void initDatabase(){
		try {
			CloudStorageAccount storageAccount =
			    CloudStorageAccount.parse(storageConnectionString);
			CloudTableClient tableClient = storageAccount.createCloudTableClient();
			String tableName = "customer130185592";
			tableClient.getTableReference(tableName).createIfNotExist();
			
			/*
			List<CustomerEntity> ces = findAllMember();
			for(int i = 0 ; i <ces.size();i++){
				System.out.println(ces.get(i).getRowKey());
			}
			*/
		} catch (StorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void initBookData(){
		try {
			CloudStorageAccount storageAccount =
			    CloudStorageAccount.parse(storageConnectionString);
			CloudTableClient tableClient = storageAccount.createCloudTableClient();
			String tableName = "book130185592";
			tableClient.getTableReference(tableName).createIfNotExist();
			/*
			for(int i = 0;i < 10;i++){
				System.out.println("creat book");
				String id = NumberTool.getInstance().getUniqueString(15, false);
				BookEntity book1 = new BookEntity(id,"niuniu");
				book1.setDateTime("2011121233435");
				book1.setProductId("product0");
				// Create an operation to add the new customer to the people table.
				TableOperation insertbook = TableOperation.insert(book1);

				// Submit the operation to the table service.
				tableClient.execute("book130185592", insertbook);
				System.out.println("insert"+i);
			}
			List<BookEntity> ces = findAllBook();
			for(int i = 0 ; i <ces.size();i++){
				System.out.println(ces.get(i).getRowKey());
			}
			*/
		} catch (StorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private void initProductData(){
				try {
					CloudStorageAccount storageAccount =
					    CloudStorageAccount.parse(storageConnectionString);
					CloudTableClient tableClient = storageAccount.createCloudTableClient();
					String tableName = "product130185592";
					tableClient.getTableReference(tableName).createIfNotExist();
					/*
					List<ProductEntity> ces = findAllProduct();
					for(int i = 0 ; i <ces.size();i++){
						System.out.println(ces.get(i).getRowKey());
					}
					*/
				} catch (StorageException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidKeyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
	public List<BookEntity> findAllBook(){
		CloudStorageAccount storageAccount;
		try {
			List<BookEntity> books = new ArrayList<BookEntity>();
			storageAccount = CloudStorageAccount.parse(storageConnectionString);
			CloudTableClient tableClient = storageAccount.createCloudTableClient();
			TableQuery<BookEntity> partitionQuery =
			    TableQuery.from("book130185592", BookEntity.class);
			// Loop through the results, displaying information about the entity.
			for (BookEntity entity : tableClient.execute(partitionQuery)) {
				books.add(entity);
			}
			return books;
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<ProductEntity> findAllProduct(){
		CloudStorageAccount storageAccount;
		try {
			List<ProductEntity> products = new ArrayList<ProductEntity>();
			storageAccount = CloudStorageAccount.parse(storageConnectionString);
			CloudTableClient tableClient = storageAccount.createCloudTableClient();

			TableQuery<ProductEntity> partitionQuery =
			    TableQuery.from("product130185592", ProductEntity.class);

			// Loop through the results, displaying information about the entity.
			for (ProductEntity entity : tableClient.execute(partitionQuery)) {
				products.add(entity);
			}
			return products;
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
}
	
	public List<CustomerEntity> findAllMember(){
		// Retrieve storage account from connection-string
		CloudStorageAccount storageAccount;
		try {
			List<CustomerEntity> customers = new ArrayList<CustomerEntity>();
			storageAccount = CloudStorageAccount.parse(storageConnectionString);
			// Create the table client.
			CloudTableClient tableClient = storageAccount.createCloudTableClient();

			TableQuery<CustomerEntity> partitionQuery =
			    TableQuery.from("customer130185592", CustomerEntity.class);

			// Loop through the results, displaying information about the entity.
			for (CustomerEntity entity : tableClient.execute(partitionQuery)) {
				customers.add(entity);
			}
			return customers;
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void addBookEntity(BookEntity book){
		// Retrieve storage account from connection-string
				CloudStorageAccount storageAccount;
				try {
					storageAccount = CloudStorageAccount.parse(storageConnectionString);
					// Create the table client.
					CloudTableClient tableClient = storageAccount.createCloudTableClient();

					// Create an operation to add the new customer to the people table.
					TableOperation insertbook = TableOperation.insert(book);

					// Submit the operation to the table service.
					tableClient.execute("book130185592", insertbook);

				} catch (StorageException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvalidKeyException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

	}
	
	public void addCustomerEntity(CustomerEntity customer){
		// Retrieve storage account from connection-string
		CloudStorageAccount storageAccount;
		try {
			storageAccount = CloudStorageAccount.parse(storageConnectionString);
			// Create the table client.
			CloudTableClient tableClient = storageAccount.createCloudTableClient();

			// Create an operation to add the new customer to the people table.
			TableOperation insertCustomer = TableOperation.insert(customer);

			// Submit the operation to the table service.
			tableClient.execute("customer130185592", insertCustomer);

		} catch (StorageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<BookEntity> getOrderByUsername(CustomerEntity member){
		CloudStorageAccount storageAccount;
		try {
			storageAccount =
			    CloudStorageAccount.parse(storageConnectionString);
			CloudTableClient tableClient = storageAccount.createCloudTableClient();
			String partitionFilter = TableQuery.generateFilterCondition(
			    TableConstants.PARTITION_KEY, 
			    QueryComparisons.EQUAL,
			    member.getPartitionKey());
			TableQuery<BookEntity> partitionQuery =
			    TableQuery.from("book130185592", BookEntity.class)
			    .where(partitionFilter);
			List<BookEntity> list = new ArrayList<BookEntity>();
			for (BookEntity entity : tableClient.execute(partitionQuery)) {
				list.add(entity);
			}
			return list;
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public CustomerEntity getMemberByUsername(CustomerEntity member){
		CloudStorageAccount storageAccount;
		try {
			storageAccount =
			    CloudStorageAccount.parse(storageConnectionString);
			CloudTableClient tableClient = storageAccount.createCloudTableClient();
			String partitionFilter = TableQuery.generateFilterCondition(
			    TableConstants.ROW_KEY, 
			    QueryComparisons.EQUAL,
			    member.getRowKey());
			TableQuery<CustomerEntity> partitionQuery =
			    TableQuery.from("customer130185592", CustomerEntity.class)
			    .where(partitionFilter);
			for (CustomerEntity entity : tableClient.execute(partitionQuery)) {
				System.out.println(entity.getRowKey());
			    return entity;
			}
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public List<CustomerEntity> getMembersByCountry(String country){
		CloudStorageAccount storageAccount;
		try {
			storageAccount =
			    CloudStorageAccount.parse(storageConnectionString);
			CloudTableClient tableClient = storageAccount.createCloudTableClient();
			String partitionFilter = TableQuery.generateFilterCondition(
			    TableConstants.PARTITION_KEY, 
			    QueryComparisons.EQUAL,
			    country);
			TableQuery<CustomerEntity> partitionQuery =
			    TableQuery.from("customer130185592", CustomerEntity.class)
			    .where(partitionFilter);
			List<CustomerEntity> list = new ArrayList<CustomerEntity>();
			for (CustomerEntity entity : tableClient.execute(partitionQuery)) {
			    list.add(entity);
			}
			return list;
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void delete(){
		// Retrieve storage account from connection-string
		CloudStorageAccount storageAccount;
				try {
					storageAccount = CloudStorageAccount.parse(storageConnectionString);
					// Create the table client.
					CloudTableClient tableClient = storageAccount.createCloudTableClient();
					tableClient.getTableReference("book130185592").deleteIfExists();
					tableClient.getTableReference("customer130185592").deleteIfExists();
				} catch (InvalidKeyException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (URISyntaxException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch(Exception e){
					
				}
	}
	
	public static void main(String[] args) {
		//NoSQL nosql = NoSQL.getInstance();
			//nosql.delete();
	}
}
