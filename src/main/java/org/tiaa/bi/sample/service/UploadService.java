package org.tiaa.bi.sample.service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import org.tiaa.bi.sample.model.FileModel;

public class UploadService {

	public FileModel readFile(HttpServletRequest request) {
		
		FileModel file = new FileModel();
		boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);
		
		if (!isMultipartContent) {
			System.out.println("No multipart form data found!");
			return null;
		}
		System.out.println("Reading multipart form data...");
		
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try {
			List<FileItem> fields = upload.parseRequest(request);
			System.out.println("Number of fields: " + fields.size());
			Iterator<FileItem> it = fields.iterator();
			if (!it.hasNext()) {
				System.out.println("No fields found");
				return null;
			}
			
			while (it.hasNext()) {
				FileItem fileItem = it.next();
				
				info(fileItem);
				
				file.name = fileItem.getName();
				file.inputStream = fileItem.getInputStream(); 
				
				return file;
			}		
			
		} catch (Exception e) {
			System.out.println("UploadService.readFile() error: " + e);
		}
		
		return null;
	}
	
	public List<FileModel> readFiles(HttpServletRequest request) {
		
		List<FileModel> fileList = new LinkedList<FileModel>();
		boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);
		
		if (!isMultipartContent) {
			System.out.println("No multipart form data found!");
			return null;
		}
		System.out.println("Reading multipart form data...");
		
		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		
		try {
			List<FileItem> fields = upload.parseRequest(request);
			System.out.println("Number of fields: " + fields.size());
			Iterator<FileItem> it = fields.iterator();
			if (!it.hasNext()) {
				System.out.println("No fields found");
				return fileList;
			}
			
			while (it.hasNext()) {
				FileItem fileItem = it.next();
				FileModel file = new FileModel();
				
				info(fileItem);
				
				file.name = fileItem.getName();
				file.inputStream = fileItem.getInputStream(); 
				file.ext = FilenameUtils.getExtension(fileItem.getName());
				
				fileList.add(file);
			}
			
			return fileList;
			
		} catch (Exception e) {
			System.out.println("UploadService.readFile() error: " + e);
		}
		
		return fileList;
	}
	
	public void info(FileItem fileItem) {
		System.out.println("------- FILE INFO -------");
		if (!fileItem.isFormField()) {
			System.out.println("Input Field Name: " + fileItem.getFieldName() +
				//"Data: " + fileItem.getString() +
				"\nFile Name: " + fileItem.getName() +
				"\nContent Type: " + fileItem.getContentType() +
				"\nSize: " + (fileItem.getSize()  / 1024) + " KB" +
				"\nFile Item String: " + fileItem.toString()
				);
		}
		System.out.println("------- FILE INFO END -------");
	}
}
