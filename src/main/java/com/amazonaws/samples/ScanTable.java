package com.amazonaws.samples;

/////////////// Return the List of Java Objects of Each items of the Table //////////////////




import java.util.List;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;

public class ScanTable {
	
	public  List<MapClass> scan() {
		
		
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
	    
	    
	        //SCANNInG THE TABLE NAMED IN THE MAPCLASS
	 		DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
	 		List<MapClass> mapClass = mapper.scan(MapClass.class, scanExpression); 
	 		
	 		System.out.println(mapClass.size() + "scanTable");
	 		return mapClass;
	 		//System.out.println(mapClass.size());

	}
	
}
