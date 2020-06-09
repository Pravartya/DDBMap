package com.amazonaws.samples;
//***** POJO CLASS **********/
// This is basically the POJO Class which contains the format of the DDB Table///
// Each Java Property (eg. domain,realm,etc.) corresponds to the Attributes of the items in Table


import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGenerated;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIgnore;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.google.common.base.Joiner;

@DynamoDBTable(tableName="ConfigurationTable")
public class ConfigurationMap {

	private String domain;
	private String realm;
	private String cfgKey;
	private String stringValue;
	private Integer integerValue;
	private Integer doubleValue;
	private MapValue mapValue;
	private String domainRealm;
	
	

	@DynamoDBIgnore
	public String getDomainName() {return domain;}
 	public void setDomainName(String domain) {this.domain= domain; }
	
 	@DynamoDBIgnore
	public String getRealm() {return realm;}
 	public void setRealm(String realm) {this.realm = realm; }
 	
 	
    @DynamoDBHashKey(attributeName="DomainRealm")
 	public String getDomainRealm() { return domainRealm; }
 	public void setDomainRealm(String domainRealm) {this.domainRealm = domainRealm; }
 	
// 	
// @DynamoDBRangeKey(attributeName = "Realm")
//	public String getRealm() {return realm;}
// 	public void setRealm(String realm) {this.realm = realm; }
 
    @DynamoDBRangeKey(attributeName="CfgKey")
 	public String getCfgKey() {return cfgKey; }
	public void setCfgKey(String cfgKey) { this.cfgKey= cfgKey; }	
 
    @DynamoDBAttribute(attributeName="StringValue")
	public String getStringValue() {return stringValue; }
  	public void setStringValue(String stringValue) { this.stringValue= stringValue; }
			 	
		 
    @DynamoDBAttribute(attributeName="IntegerValue")
 	public Integer getIntegerValue() {return integerValue; }
	public void setIntegerValue(Integer integerValue) { this.integerValue= integerValue; }			
	
	
    @DynamoDBAttribute(attributeName="DoubleValue")
	public Integer getDoubleValue() {return doubleValue; }
  	public void setDoubleValue(Integer doubleValue) { this.doubleValue= doubleValue; }		
	
		
  	@DynamoDBAttribute(attributeName="MapValue")
	 public MapValue getMapValue() { return mapValue;}
	 public void setMapValue(MapValue mapValue) {this.mapValue = mapValue;}
	 	 // Additional properties go here.

	 
	 
	 @DynamoDBDocument
 	 public static class MapValue {
 		 private String credentials;
 		 private String url;
 		 private String reservationRequestsqueueURL;
 		 private String manualApprovalqueueURL;
 		 private String crawlCompletionResponsequeueURL;
 		 private String subscriptionsAllocationqueueURL;
 		
 		 
 		 private String reallocationEventstopicArn;
 		 private String subscriptionEventstopicArn;
 		 private String connectTimeout;
 		 private String maxConnectRetries;
 		 private String maxTotalHttpConnections;
 		 private String maxHttpConnectionsPerHost;
 		 private String closeAfterUse;
 		 private String connectionStaleCheckingEnabled;
 		 private String maxReplyThreads;
 		 
 		
 		
 		 @DynamoDBAttribute(attributeName = "Credentials")
 		 public String getCredentials() { return credentials; }
 		 public void setCredentials(String credentials) { this.credentials = credentials; }
 		 
 		 @DynamoDBAttribute(attributeName = "URL")
 		 public String getUrl() { return url; }
 		 public void setUrl(String url) { this.url = url; }
 		 
 		 @DynamoDBAttribute(attributeName = "ReservationRequestsqueueURL")
 		 public String getReservationRequestsqueueURL() { return reservationRequestsqueueURL; }
 		 public void setReservationRequestsqueueURL(String reservationRequestsqueueURL) { this.reservationRequestsqueueURL = reservationRequestsqueueURL; }
 		 
 		 @DynamoDBAttribute(attributeName = "ManualApprovalqueueURL")
 		 public String getManualApprovalqueueURL() { return manualApprovalqueueURL; }
 		 public void setManualApprovalqueueURL(String manualApprovalqueueURL) { this.manualApprovalqueueURL = manualApprovalqueueURL; }
 		 
 		 @DynamoDBAttribute(attributeName = "CrawlCompletionResponsequeueURL")
 		 public String getCrawlCompletionResponsequeueURL() { return crawlCompletionResponsequeueURL; }
 		 public void setCrawlCompletionResponsequeueURL(String crawlCompletionResponsequeueURL) { this.crawlCompletionResponsequeueURL = crawlCompletionResponsequeueURL; }
 		 
 		 @DynamoDBAttribute(attributeName = "SubscriptionsAllocationqueueURL")
 		 public String getSubscriptionsAllocationqueueURL() { return subscriptionsAllocationqueueURL; }
 		 public void setSubscriptionsAllocationqueueURL(String subscriptionsAllocationqueueURL) { this.subscriptionsAllocationqueueURL = subscriptionsAllocationqueueURL; }
 		 
 		 
 		 @DynamoDBAttribute(attributeName = "ReallocationEventstopicArn")
 		 public String getReallocationEventstopicArn() {return reallocationEventstopicArn;}
 		 public void setReallocationEventstopicArn(String reallocationEventstopicArn) {this.reallocationEventstopicArn = reallocationEventstopicArn;}
		
		
		@DynamoDBAttribute(attributeName = "SubscriptionEventstopicArn")
		public String getSubscriptionEventstopicArn() {return subscriptionEventstopicArn;}
		public void setSubscriptionEventstopicArn(String subscriptionEventstopicArn) {this.subscriptionEventstopicArn = subscriptionEventstopicArn;}
		
		
		@DynamoDBAttribute(attributeName = "ConnectTimeout")
		public String getConnectTimeout() {return connectTimeout;}
		public void setConnectTimeout(String connectTimeout) {this.connectTimeout = connectTimeout;}
		
		@DynamoDBAttribute(attributeName = "MaxConnectRetries")
		public String getMaxConnectRetries() {return maxConnectRetries;}
		public void setMaxConnectRetries(String maxConnectRetries) {this.maxConnectRetries = maxConnectRetries;}
		
		@DynamoDBAttribute(attributeName = "MaxTotalHttpConnections")
		public String getMaxTotalHttpConnections() {return maxTotalHttpConnections;}
		public void setMaxTotalHttpConnections(String maxTotalHttpConnections) {this.maxTotalHttpConnections = maxTotalHttpConnections;}
		
		@DynamoDBAttribute(attributeName = "MaxHttpConnectionsPerHost")
		public String getMaxHttpConnectionsPerHost() {return maxHttpConnectionsPerHost;}
		public void setMaxHttpConnectionsPerHost(String maxHttpConnectionsPerHost) {this.maxHttpConnectionsPerHost = maxHttpConnectionsPerHost;}
		
		@DynamoDBAttribute(attributeName = "CloseAfterUse")
		public String getCloseAfterUse() {return closeAfterUse;}
		public void setCloseAfterUse(String closeAfterUse) {this.closeAfterUse = closeAfterUse;}
		
		@DynamoDBAttribute(attributeName = "ConnectionStaleCheckingEnabled")
		public String getConnectionStaleCheckingEnabled() {return connectionStaleCheckingEnabled;}
		public void setConnectionStaleCheckingEnabled(String connectionStaleCheckingEnabled) {this.connectionStaleCheckingEnabled = connectionStaleCheckingEnabled;}
		
		@DynamoDBAttribute(attributeName = "MaxReplyThreads")
		public String getMaxReplyThreads() {return maxReplyThreads;}
		public void setMaxReplyThreads(String maxReplyThreads) {this.maxReplyThreads = maxReplyThreads;}


 	 }

}



