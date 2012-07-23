package net.luszczyk.mdbv.common.service;

import java.io.File;

public interface FileService {
	
	File getFile(String path);
	String saveFile(byte[] buf);
	String getFileType(String path);
	byte[] getFileContent(String path);

}
