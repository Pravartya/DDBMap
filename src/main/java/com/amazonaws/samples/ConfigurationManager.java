package com.amazonaws.samples;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.collections4.ListUtils;

public class ConfigurationManager {
	
	
	static List<ConfigurationMap> list = Collections.emptyList() ;
	static ApplicationConfiguration newApplication = new ApplicationConfiguration ();
	public static void appendList(String domain,String realm)
	{
		try {
			list = ListUtils.union(list,newApplication.DomainRealm(domain, realm));
			
		}
		catch(Exception e){
					
		}
		
	}
	public static void main(String[] args)  {
		
		String domain;
		String realm;
		String cfgKey;
		Scanner sc= new Scanner(System.in); 
		System.out.println("Enter the Domain of your Application");
		domain = sc.nextLine();
		System.out.println("Enter the Realm of your Application");
		realm = sc.nextLine();

		if(domain.equals("*")  && realm.equals("*"))
		{
			System.out.println("A");
			appendList("test", "EUAmazon");
			appendList("test", "USAmazon");
			appendList("test", "FEAmazon");
			appendList("test", "CNAmazon");
			appendList("prod", "EUAmazon");
			appendList("prod", "USAmazon");
			appendList("prod", "FEAmazon");
			appendList("prod", "CNAmazon");
			appendList("test", "*");
			appendList("*", "prod");
			appendList("*", "*");
		}
		if(domain.equals("*") && !(realm.equals("*")))
		{
			System.out.println("B");
			appendList("test", realm);
			appendList("prod", realm);
			appendList("*", realm);
		
		}
		if(realm.equals("*") && !domain.equals("*"))
		{
			System.out.println("C");
			appendList(domain,"EUAmazon");
			appendList(domain,"USAmazon");
			appendList(domain,"FEAmazon");
			appendList(domain,"CNAmazon");
			appendList(domain,"*");
		}
		if(!(realm.equals("*")) && (!domain.equals("*")))   
		{
			System.out.println("D");
			appendList(domain, realm);
		}
	    System.out.println("size " + list.size());
		//list will contains all the items of matched domain and realm
	    
		
		Map cache = newApplication.Cache(list);
		
		System.out.println("Particular Key Value required ?");
		System.out.println("Enter the cfgkey required ");
	
		cfgKey = sc.nextLine();
		
		try {
			ConfigurationMap particularKey =  newApplication.GetParticularConfigurationValue(cfgKey);
			if(particularKey.getDoubleValue() != null) System.out.println(particularKey.getDoubleValue());
			if(particularKey.getIntegerValue() != null) System.out.println(particularKey.getIntegerValue());
			if(particularKey.getMapValue() != null) System.out.println(particularKey.getMapValue());
			if(particularKey.getStringValue() != null) System.out.println(particularKey.getStringValue());
		}
		catch (Exception e) {
			System.out.println("Invalid Key");

		}

	}


}
