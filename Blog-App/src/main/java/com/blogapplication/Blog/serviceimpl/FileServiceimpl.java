package com.blogapplication.Blog.serviceimpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.blogapplication.Blog.services.FileService;

@Service
public class FileServiceimpl implements FileService {

	@Override
	public String uploanImage(String path, MultipartFile file) throws IOException {
		// TODO Auto-generated method stub
		//get file name  abc.png
		String name=file.getOriginalFilename();
		
		//random name generate file
		String randomUID=UUID.randomUUID().toString();
		String filename=randomUID.concat(name.substring(name.lastIndexOf(".")));
		
		//filepath
		String filepath=path+File.separator+filename;
		
		//create folder if ot created
		File f=new File(path);
		if(!f.exists()) {
			f.mkdir();
		}
		
		Files.copy(file.getInputStream(), Paths.get(filepath));
		
		return filename;
	}

	@Override
	public InputStream getResource(String path, String filename) throws FileNotFoundException {
		// TODO Auto-generated method stub
		String filepath=path+File.separator+filename;
		InputStream in=new FileInputStream(filepath);
		
		return in;
	}

}
