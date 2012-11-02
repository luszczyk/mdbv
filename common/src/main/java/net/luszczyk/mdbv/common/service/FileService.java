package net.luszczyk.mdbv.common.service;

import java.io.File;

public interface FileService {
	
	File getFile(String path);
	String saveFile(byte[] buf);
    String saveFile(byte[] buf, String extension);
	String getFileType(String path);
	byte[] getFileContent(String path);

}
