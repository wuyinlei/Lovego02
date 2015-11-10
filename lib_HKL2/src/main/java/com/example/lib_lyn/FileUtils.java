package com.example.lib_lyn;

import java.io.File;

public class FileUtils {
	public static void clearDir(File dir) {
		File[] listFiles = dir.listFiles();
		for (int i = 0; i < listFiles.length; i++) {
			System.out.println(listFiles[i]);
			if (listFiles[i].isFile())
			{
				listFiles[i].delete();
			}
			else
			{
				clearDir(listFiles[i]);
				listFiles[i].delete();
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
