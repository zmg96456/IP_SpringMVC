package com.tledu.aaa.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 读取IP地址库,并封装到集合中
 * txtFilePath    字符串格式的文件路径
 * encoding  字符编码
 */
public class FileOperatorUtil {
	public static List<String> getLineList(String txtFilePath,String encoding) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(txtFilePath),encoding));
		String temp =null;
		List<String> lineList = new ArrayList<String>();
		while ((temp=br.readLine())!=null) {
			lineList.add(temp);
		}
		br.close();
		return lineList;
	}
}
