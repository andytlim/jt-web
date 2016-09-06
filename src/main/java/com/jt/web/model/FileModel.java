package com.jt.web.model;

import java.io.InputStream;

public class FileModel {
	public String name;
	public InputStream inputStream;
	public String ext;
	
	public String toString() {
		return "{name: " + this.name + "}";
	}
}
