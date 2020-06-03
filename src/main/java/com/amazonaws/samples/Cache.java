package com.amazonaws.samples;

//********* Cache the Whole Table after Scanning ************//
// This file creates the Cache as a Concurrent Maps where key = CfgKey(from Table) and Value = the whole Item corresponds to the key //
// I have just used expireAfterAccess = 30 Mins (for sample)...This will clear the data(DDB Item from the Cache Map) after 30 mins of last access.




import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.samples.MapClass.MapValue;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import java.util.List;
public class Cache {
	
	 static Map<String, MapClass> database;
	
	
	public static void databaseCreate() {
		System.out.println("Creating database for Caching");
		database = new HashMap<String, MapClass>();
		ScanTable s = new ScanTable();
		List<MapClass> mC = s.scan();
		  
		 //NOTE : CfgKey having same name will be considered one entity in the Map //////
		for(int i=0;i<mC.size();i++)
		{
			database.put(mC.get(i).getCfgKey(), mC.get(i));			  
		}

	}
	
	public static void main(String args[]) {
		   
		
		databaseCreate();

		System.out.println(database.size() + " Cache"); 
		
		
		
	      //create a cache for configurations data based on the cfgKey
	      LoadingCache<String, MapClass> cache = 
	         CacheBuilder.newBuilder()
	         .expireAfterAccess(30, TimeUnit.MINUTES)      // cache will expire after 30 minutes of access
	         .build(new CacheLoader<String, MapClass>() {  // build the cacheloader
	            
	            @Override
	            public MapClass load(String cfgKey) throws Exception {
	               //make the expensive call
	               return getFromDatabase(cfgKey);
	            } 
	         });

	     //on first invocation, cache will be populated with corresponding cfg key	         
		 System.out.println("Invocation #1");
		   for(Map.Entry m : database.entrySet()){    
			   System.out.println(cache.getUnchecked((String) m.getKey()));    
			 } 
	
		 //second invocation, data will be returned from cache
		 System.out.println("Invocation #2"); 
		   for(Map.Entry m : database.entrySet()){    
			   System.out.println(cache.getUnchecked((String) m.getKey()));    
			 } 
		   
		   System.out.println(cache.asMap());                                                 //Printing the cache as the Map	   
	
	}
		 
	   private static MapClass getFromDatabase(String cfgKey) {
		      System.out.println("Database hit for " + cfgKey);
		      return database.get(cfgKey);	
		   }
	

}
