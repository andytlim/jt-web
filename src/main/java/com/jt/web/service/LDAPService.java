package com.jt.web.service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.tiaa.bi.services.model.ADUser;
import org.tiaa.bi.services.utility.ActiveDirectory;

@Service
public class LDAPService {

	protected static Logger log = LoggerFactory.getLogger(LDAPService.class);

	public ADUser getUserByRacfId(String racfId) throws NamingException {
		ActiveDirectory ad = new ActiveDirectory("username", "password",
				"ad.jt.web.com");

		// Searching
		NamingEnumeration<SearchResult> result = ad.searchUser(racfId,
				"username", null);

		ADUser adUser = new ADUser();
		int count = 0;
		if (result.hasMore()) {
			count++;
			log.info(String.valueOf(count));
			SearchResult rs = result.next();
			Attributes attrs = rs.getAttributes();
			NamingEnumeration enumeration = attrs.getAll();
				
			adUser.setAll(new String());
			while (enumeration.hasMore()) {
				adUser.setAll(adUser.getAll() + "||"+enumeration.next().toString());
			}			

			String temp = attrs.get("samaccountname").toString();
			adUser.setRacfId(temp.substring(temp.indexOf(":") + 1).trim());
			temp = attrs.get("displayName").toString();
			adUser.setDisplayName(temp.substring(temp.indexOf(":") + 1).trim());
			temp = attrs.get("givenname").toString();
			adUser.setName(temp.substring(temp.indexOf(":") + 1).trim());
			temp = attrs.get("sn").toString();
			adUser.setName(adUser.getName() + " " + temp.substring(temp.indexOf(":") + 1).trim());
			temp = attrs.get("mail").toString();
			adUser.setEmail(temp.substring(temp.indexOf(":") + 1).trim());
			temp = attrs.get("title").toString();
			adUser.setTitle(temp.substring(temp.indexOf(":")+1).trim());
			temp = attrs.get("manager").toString();
			adUser.setManager(temp.substring(temp.indexOf(":")+1).trim());
		} 
		
		// Closing LDAP Connection
		ad.closeLdapConnection();

		return adUser;
	}
	
	public List<Map<String, String>> searchUser(String sn) throws NamingException {
		ActiveDirectory ad = new ActiveDirectory("username", "password",
				"ad.jt.web.com");

		// Searching
		NamingEnumeration<SearchResult> result = ad.searchUser(sn,
				"sn", null);

		List<Map<String, String>> queryResults = new LinkedList<Map<String, String>>();
		
		while (result.hasMore()) {
			SearchResult rs = result.next();
			Attributes attrs = rs.getAttributes();
			NamingEnumeration enumeration = attrs.getAll();

			queryResults.add(new HashMap<String, String>());
			while (enumeration.hasMore()) {
				String[] keyVal = enumeration.next().toString().split("\\s*:\\s*");
				queryResults.get(queryResults.size()-1).put(keyVal[0], keyVal[1]);
			}			
		} 

		// Closing LDAP Connection
		ad.closeLdapConnection();

		return queryResults;
	}
	
	
}
