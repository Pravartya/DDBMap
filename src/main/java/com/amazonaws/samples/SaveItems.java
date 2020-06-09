package com.amazonaws.samples;

//**** This Class is just saving the items in the Table (For Creating the Dummy Table) ///******


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.samples.ConfigurationMap.MapValue;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.google.common.base.Joiner;

public class SaveItems {
	
	
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
        
        
        //1st item
        ConfigurationMap item = new ConfigurationMap();
        
        item.setDomainName("test");
        item.setRealm("*");
        item.setDomainRealm(Joiner.on(",").skipNulls().join(Arrays.asList(item.getDomainName(),item.getRealm())));
        item.setCfgKey("sqs.configuration");
        
        
        MapValue m = new MapValue();
        m.setCredentials("com.amazon.access.cmt-edac-dev-edac-onboardingservice-devo-1");
        m.setUrl("https://sqs.us-west-2.amazonaws.com");
        m.setReservationRequestsqueueURL( "https://sqs.us-west-2.amazonaws.com/969720227852/reservationRequests");
        m.setManualApprovalqueueURL("https://sqs.us-west-2.amazonaws.com/969720227852/manuallyCheckedRequests");
        m.setCrawlCompletionResponsequeueURL("https://sqs.us-west-2.amazonaws.com/969720227852/crawlCompletionResponse");
        m.setSubscriptionsAllocationqueueURL("https://sqs.us-west-2.amazonaws.com/969720227852/subscriptionsAllocation");
       
        item.setMapValue(m);
        
        
        //2nd item
        ConfigurationMap item2 = new ConfigurationMap();
        item2.setDomainName("prod");
        item2.setRealm("EUAmazon");
        item2.setDomainRealm(Joiner.on(",").skipNulls().join(Arrays.asList(item2.getDomainName(),item2.getRealm())));
        item2.setCfgKey("sqs.configuration");
        
        
        MapValue m2 = new MapValue();
        m2.setCredentials("com.amazon.access.cmt-edac-edac-request-management-1");
        m2.setUrl("https://sqs.eu-west-1.amazonaws.com");
        m2.setReservationRequestsqueueURL( "https://sqs.eu-west-1.amazonaws.com/037074343510/reservationRequests");
        m2.setManualApprovalqueueURL("https://sqs.eu-west-1.amazonaws.com/037074343510/manuallyCheckedRequests");
        m2.setCrawlCompletionResponsequeueURL("https://sqs.eu-west-1.amazonaws.com/037074343510/crawlCompletionResponse");
        m2.setSubscriptionsAllocationqueueURL("https://sqs.eu-west-1.amazonaws.com/037074343510/subscriptionsAllocation");

        item2.setMapValue(m2);
        
        //ANOTHER ITEM
        ConfigurationMap item3 = new ConfigurationMap();
        item3.setDomainName("*");
        item3.setRealm("*");
        item3.setDomainRealm(Joiner.on(",").skipNulls().join(Arrays.asList(item3.getDomainName(),item3.getRealm())));
        item3.setCfgKey("visibilityTimeout");
        item3.setIntegerValue(30);
        
        
        
        //ANOTHER ITEM
        ConfigurationMap item4 =  new ConfigurationMap();
        item4.setDomainName("test");
        item4.setRealm("*");
        item4.setDomainRealm(Joiner.on(",").skipNulls().join(Arrays.asList(item4.getDomainName(),item4.getRealm())));
        item4.setCfgKey("sqs.reservationRequests.queueName");
        item4.setStringValue("reservationRequests");
        
        
        //BATCHSAVE ALL THE ITEMS
        mapper.batchSave(Arrays.asList(item,item2,item3,item4));
        
    
	}



}
