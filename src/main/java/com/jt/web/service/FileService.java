package com.jt.web.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import com.jt.web.model.FileModel;

public class FileService {
	
	public boolean write(List<FileModel> files, String path) {
		try {
			for (FileModel file : files) {
				OutputStream outputStream = 
		                new FileOutputStream(new File(path + file.name));
		
				int read = 0;
				byte[] bytes = new byte[1024];
			
				while ((read = file.inputStream.read(bytes)) != -1) {
					outputStream.write(bytes, 0, read);
				}	
			}
			return true;
		} catch (Exception e) {
			System.out.println("FileMoveService.write() error: " + e);
			return false;
		}
	}
}
