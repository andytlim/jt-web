package com.jt.web.service;

import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;

@Service("winSharedPathService")
public class WinSharedPathService {

	protected static Logger log = LoggerFactory.getLogger(WinSharedPathService.class);
	
	public InputStream getFile(String path, String fileName) throws IOException {
		String url = "smb://ad.jt.web.com/files/shared/"+path;
		log.info(url);
		NtlmPasswordAuthentication auth = new NtlmPasswordAuthentication("ad.jt.web.com", "username", "password");
		SmbFile dir = new SmbFile(url, auth);
		for (SmbFile f : dir.listFiles())
		{
			log.info(f.getName());
			if (f.getName().equals(fileName))
				return f.getInputStream();
		}
		
		return null;
	}
}
