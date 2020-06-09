package com.amazonaws.samples;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.google.common.base.Joiner;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class ApplicationConfiguration implements GetConfigurations {
	
	static Map<String, ConfigurationMap> database;
	String domainRealm;

	public static void databaseCreate(List<ConfigurationMap> list) {
		System.out.println("Creating database for Caching");
		database = new HashMap<String, ConfigurationMap>();
		 //NOTE : CfgKey having same name will be considered one entity in the Map //////
		for(int i=0;i<list.size();i++)
		{
			database.put(list.get(i).getCfgKey(), list.get(i));			  
		}

	}
	private static ConfigurationMap getFromDatabase(String cfgKey) {
	      System.out.println("Database hit for " + cfgKey);
	      return database.get(cfgKey);	
}

	@Override
	public List<ConfigurationMap> DomainRealm(String Domain, String Realm) {
		
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
	    
	    domainRealm = Joiner.on(",").skipNulls().join(Arrays.asList(Domain,Realm));
	    
	    
	    Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(domainRealm));
		DynamoDBQueryExpression<ConfigurationMap> queryExpression = new DynamoDBQueryExpression<ConfigurationMap>()
				.withKeyConditionExpression("DomainRealm = :v1")
				.withExpressionAttributeValues(eav);
		List<ConfigurationMap> queries  = mapper.query(ConfigurationMap.class, queryExpression);
		return queries;
	}

	@Override
	public Map Cache(List<ConfigurationMap> list) {
		databaseCreate(list);

		System.out.println(database.size() + " Cache"); 
		
	      //create a cache for configurations data based on the cfgKey
	      LoadingCache<String, ConfigurationMap> cache = 
	         CacheBuilder.newBuilder()
	         .expireAfterAccess(30, TimeUnit.MINUTES)      // cache will expire after 30 minutes of access
	         .build(new CacheLoader<String, ConfigurationMap>() {  // build the cacheloader
	            
	            @Override
	            public ConfigurationMap load(String cfgKey) throws Exception {
	               //make the expensive call
	               return getFromDatabase(cfgKey);
	            } 
	         });
	      
	     //on first invocation, cache will be populated with corresponding cfg key	         
		 System.out.println("Invocation #1");
		   for(Map.Entry m : database.entrySet()){    
			   cache.getUnchecked((String) m.getKey());    
			 } 
	
		 //second invocation, data will be returned from cache
		 System.out.println("Invocation #2"); 
		   for(Map.Entry m : database.entrySet()){    
			   cache.getUnchecked((String) m.getKey());    
			 }
		   return cache.asMap();
	}

	@Override
	public ConfigurationMap GetParticularConfigurationValue(String cfgKey) {
		
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

	    Map<String, AttributeValue> eav = new HashMap<String, AttributeValue>();
		eav.put(":v1", new AttributeValue().withS(domainRealm));
		eav.put(":v2",new AttributeValue().withS(cfgKey));
		DynamoDBQueryExpression<ConfigurationMap> queryExpression = new DynamoDBQueryExpression<ConfigurationMap>()
				.withKeyConditionExpression("DomainRealm = :v1 and CfgKey = :v2")
				.withExpressionAttributeValues(eav);
		
		List<ConfigurationMap> queries  = mapper.query(ConfigurationMap.class, queryExpression);
		return queries.get(0);
	}

}
