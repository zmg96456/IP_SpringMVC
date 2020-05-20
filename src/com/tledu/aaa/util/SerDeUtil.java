package com.tledu.aaa.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SerDeUtil {
	
	//对象读取  加入缓冲流
	public static Object getObj(String objFilePath,int cacheByteArrayLength) throws IOException, ClassNotFoundException{
		FileInputStream fis = new FileInputStream(objFilePath);
		byte[]  byteArray =new byte[cacheByteArrayLength];
		fis.read(byteArray);
		fis.close();
		ByteArrayInputStream byteArrayInputStream =new ByteArrayInputStream(byteArray);
		ObjectInputStream ois = new ObjectInputStream(byteArrayInputStream);
		Object object = ois.readObject();
		ois.close();
		return object;
	}
	
	//对象写出  加入缓冲流
	public static void saveObj(Object object,String objFilePath,int cacheByteArrayLength) throws IOException{

		FileOutputStream fos =new FileOutputStream(objFilePath);
		// 字节数组缓冲流
		ByteArrayOutputStream baos = new ByteArrayOutputStream(cacheByteArrayLength);
		ObjectOutputStream oos =new ObjectOutputStream(baos);
		oos.writeObject(object);
		byte[] byteArray =baos.toByteArray();
		oos.close();
		fos.write(byteArray);
		fos.close();
		
	}
	
	
	//对象读取
	public static Object getObj(String objFilePath) throws IOException{
		FileInputStream fis = new FileInputStream(objFilePath);
		ObjectInputStream ois = new ObjectInputStream(fis);
		Object object = new Object();
		ois.close();
		return object;
	}
	
	//对象写出
	public static void saveObj(Object object,String objFilePath) throws IOException{
		FileOutputStream fos =new FileOutputStream(objFilePath);
		ObjectOutputStream oos =new ObjectOutputStream(fos);
		oos.writeObject(object);
		oos.close();
		
	}
}
