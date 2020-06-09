package com.amazonaws.samples;

import java.util.List;
import java.util.Map;

public interface GetConfigurations {
	List<ConfigurationMap> DomainRealm (String Domain,String Realm);                                        //Client will give his Domain and Realm of its application
	Map Cache(List <ConfigurationMap> list );                                                                                     //if client needs for the cache service	
	ConfigurationMap GetParticularConfigurationValue(String CfgKey);    
}
