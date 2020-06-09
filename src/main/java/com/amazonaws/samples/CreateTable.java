package com.amazonaws.samples;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;

public class CreateTable {

	public static void main(String[] args) {
		
		
		ProfileCredentialsProvider credentialsProvider = new ProfileCredentialsProvider();
        try {
            credentialsProvider.getCredentials();
        } catch (Exception e) {
            throw new AmazonClientException(
                    "Cannot load the credentials from the credential profiles file. " +
                    "Please make sure that your credentials file is at the correct " +
                    "location (C:\\Users\\PD\\.aws\\credentials), and is in valid format.",
                    e);
        }
        
    	AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withCredentials(credentialsProvider)
				 .withRegion("us-east-1")
				 .build();

		  DynamoDBMapper mapper = new DynamoDBMapper(client);
		  CreateTableRequest req = mapper.generateCreateTableRequest(ConfigurationMap.class);
		  // Table provision throughput is still required since it cannot be specified in your POJO
		  req.setProvisionedThroughput(new ProvisionedThroughput(5L, 5L));
		  // Fire off the CreateTableRequest using the low-level client
		  client.createTable(req);
		  System.out.println("Table Created!");

	}

}
