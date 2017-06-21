package serviceImpl;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import fileOpeation.IOFileOpeation;
import service.IOService;

public class IOServiceImpl implements IOService{
	
	@Override
	public boolean writeFile(String file, String userId, String fileName) {
		return IOFileOpeation.writeFile(file, userId, fileName);
	}
	@Override
	public String readFile(String userId, String fileName) {
		// TODO Auto-generated method stub
		return IOFileOpeation.readFile(userId, fileName);
	}

	@Override
	public String readFileList(String userId) {
		// TODO Auto-generated method stub
		return IOFileOpeation.readFileList(userId);
	}
	
}
