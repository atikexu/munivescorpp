package pe.nasqa.values.control;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;


@Component
public class ImpExpDbZip {
	
	static final int BUFFER = 2048;
	
	private Logger log = Logger.getLogger(ImpExpDbZip.class);

//	public List<String> unZipFile(File file) {
//		List<String> result = new ArrayList<String>();
//		try {
//			BufferedOutputStream dest = null;
//			BufferedInputStream is = null;
//			ZipEntry entry;
//			ZipFile zipfile = new ZipFile(file);
//			
//			Enumeration e = zipfile.entries();
//			while (e.hasMoreElements()) {
//				entry = (ZipEntry) e.nextElement();
//				result.add(entry.getName());
//
//				log.info("Extracting: " + entry);
//				is = new BufferedInputStream(zipfile.getInputStream(entry));
//				int count;
//				byte data[] = new byte[BUFFER];
//				FileOutputStream fos = new FileOutputStream(new File(file.getParent(),entry.getName()));
//				
//				dest = new BufferedOutputStream(fos, BUFFER);
//				while ((count = is.read(data, 0, BUFFER)) != -1) {
//					dest.write(data, 0, count);
//				}
//				dest.flush();
//				dest.close();
//				is.close();
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return result;
//	}
	
	public List<String> unZipFileZip4j(File file, String password) {
		List<String> result = new ArrayList<String>();
		try {
			ZipFile zipFile = new ZipFile(file);
			
			// Check to see if the zip file is password protected 
			if (zipFile.isEncrypted()) {
				// if yes, then set the password for the zip file
				zipFile.setPassword(password);
			}
			
			// Get the list of file headers from the zip file
			List fileHeaderList = zipFile.getFileHeaders();
			
			// Loop through the file headers
			for (int i = 0; i < fileHeaderList.size(); i++) {
				FileHeader fileHeader = (FileHeader)fileHeaderList.get(i);
				// Extract the file to the specified destination
				zipFile.extractFile(fileHeader, file.getParent());
				result.add(fileHeader.getFileName());
			}
		} catch (ZipException e) {
			log.error("Descompresion fallida: "+file.getName()+", "+e.getMessage());
		}
		return result;
	}

	public String zipFilesZip4j(File dir, String password) {
		String result = null;
		try {
			String zipName = CVDinamico.getDateInFormat("yyyy-MM-dd_hhmmss") + ".zip";
			ZipFile zipFile = new ZipFile(new File(dir,zipName));
			
			// Build the list of files to be added in the array list
			// Objects of type File have to be added to the ArrayList
			ArrayList filesToAdd = new ArrayList();
			
			String files[] = dir.list();
			for (int i = 0; i < files.length; i++) {
				//log.info("Adding: " + files[i]);
				filesToAdd.add(new File(dir, files[i]));
				//System.out.println(files[i]);
			}
			
			// Initiate Zip Parameters which define various properties such
			// as compression method, etc.
			ZipParameters parameters = new ZipParameters();
			parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE); // set compression method to store compression
			
			// Set the compression level
			parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL); 
			
			
			if(password!=null){
				// Set the encryption flag to true
				// If this is set to false, then the rest of encryption properties are ignored
				parameters.setEncryptFiles(true);
				
				// Set the encryption method to Standard Zip Encryption
				parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD);
				
				// Set password
				parameters.setPassword(password);
			}
			
			// Now add files to the zip file
			// Note: To add a single file, the method addFile can be used
			// Note: If the zip file already exists and if this zip file is a split file
			// then this method throws an exception as Zip Format Specification does not 
			// allow updating split zip files
			zipFile.addFiles(filesToAdd, parameters);
			
			result = zipName;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String zipFileZip4j(File dir, String fileToZip, String password) {
		String result = null;
		try {
			ZipFile zipFile = new ZipFile(new File(dir,fileToZip+".zip"));
			
			// Build the list of files to be added in the array list
			// Objects of type File have to be added to the ArrayList
			ArrayList filesToAdd = new ArrayList();
			
			filesToAdd.add(new File(dir, fileToZip));
			// Initiate Zip Parameters which define various properties such
			// as compression method, etc.
			ZipParameters parameters = new ZipParameters();
			parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE); // set compression method to store compression
			
			// Set the compression level
			parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL); 
			
			
			if(password!=null){
				// Set the encryption flag to true
				// If this is set to false, then the rest of encryption properties are ignored
				parameters.setEncryptFiles(true);
				
				// Set the encryption method to Standard Zip Encryption
				parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_STANDARD);
				
				// Set password
				parameters.setPassword(password);
			}
			
			// Now add files to the zip file
			// Note: To add a single file, the method addFile can be used
			// Note: If the zip file already exists and if this zip file is a split file
			// then this method throws an exception as Zip Format Specification does not 
			// allow updating split zip files
			zipFile.addFiles(filesToAdd, parameters);
			
			result = fileToZip+".zip";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
//	public String zipFiles(File dir) {
//		String result = null;
//		try {
//			String zipName = CVDinamico.getDateInFormat("yyyy-MM-dd_hhmm") + ".zip";
//			BufferedInputStream origin = null;
//			FileOutputStream dest = new FileOutputStream(dir + File.separator + zipName);
//			CheckedOutputStream checksum = new CheckedOutputStream(dest, new Adler32());
//			ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(checksum));
//			// out.setMethod(ZipOutputStream.DEFLATED);
//			byte data[] = new byte[BUFFER];
//			// get a list of files from current directory
//			String files[] = dir.list();
//
//			for (int i = 0; i < files.length; i++) {
//				log.info("Adding: " + files[i]);
//				FileInputStream fi = new FileInputStream(files[i]);
//				origin = new BufferedInputStream(fi, BUFFER);
//				ZipEntry entry = new ZipEntry(files[i]);
//				out.putNextEntry(entry);
//				int count;
//				while ((count = origin.read(data, 0, BUFFER)) != -1) {
//					out.write(data, 0, count);
//				}
//				origin.close();
//			}
//			out.close();
//			result = zipName;
//			log.info("checksum: " + checksum.getChecksum().getValue());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return result;
//	}
}
