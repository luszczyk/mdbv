package net.luszczyk.mdbv.common.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

import com.sun.activation.registries.MimeTypeFile;
import net.luszczyk.mdbv.common.service.FileService;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService {

	private static final Logger logger = Logger
			.getLogger(FileServiceImpl.class);
	public static final String TEMP_DIR = "/tmp/mdbv/";

	public FileServiceImpl() {

		File f = new File(TEMP_DIR);
		if (f.exists() == false) {
			f.mkdir();
		}
	}

	@Override
	public File getFile(String path) {
		return new File(TEMP_DIR + path);
	}

    @Override
    public String saveFile(byte[] buf, String extension) {

        String fileName = "db_file" + new Date().getTime();
        if (extension != null) {
            fileName = fileName + "." + extension;
        }
        try {

            File file = new File(TEMP_DIR + fileName);
            FileOutputStream fos = new FileOutputStream(file);

            try {
                fos.write(buf);
                fos.close();
            } catch (IOException e) {
                logger.warn("Problem to save file ", e);
                return null;
            }
        } catch (FileNotFoundException e) {
            logger.warn("Problem to save file ", e);
            return null;
        }

        return fileName;
    }

	@Override
	public String saveFile(byte[] buf) {

		return saveFile(buf, null);
	}

	@Override
	public String getFileType(String path) {
		
		String result = null;
		try {

			URL u = new URL("file://" + TEMP_DIR + path);
			URLConnection uc = u.openConnection();

			result = uc.getContentType();

		} catch (Exception e) {
			logger.warn("Problem to get type for file: " + path, e);
		}
		
		return result;
	}

	@Override
	public byte[] getFileContent(String path) {
		byte[] bytes = null;
		
		try {

			URL u = new URL("file://" + TEMP_DIR + path);

			InputStream is = u.openStream();
			bytes = IOUtils.toByteArray(is);
			
			is.close();

		} catch (Exception e) {
			logger.warn("Problem to get content for file: " + path, e);
		}
		return bytes;
	}

}
