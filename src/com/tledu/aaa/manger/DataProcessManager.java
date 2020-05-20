package com.tledu.aaa.manger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.tledu.aaa.pojo.IPAndLocationPojo;
import com.tledu.aaa.util.FileOperatorUtil;
import com.tledu.aaa.util.IPUtil;
import com.tledu.aaa.util.RegexUtil;
import com.tledu.aaa.util.SerDeUtil;
import com.tledu.aaa.util.StaticValue;

/**
 * 该类为项目数据处理管理类,衔接各个模块的输入输出 进行有机组合
 * 
 */
public class DataProcessManager {
	
	private static IPAndLocationPojo[] ipArray =null;
	static{
		// 业务二分法测试
		List<IPAndLocationPojo> pojoList;
		// 判断序列化文件是否存在
		boolean isinitFlag =new File(StaticValue.serde_obj_filepath).exists();
		if (isinitFlag) {
			long startMs = System.currentTimeMillis();
			Object object;
			try {
				object = SerDeUtil.getObj(StaticValue.serde_obj_filepath, StaticValue.cacheByteArrayLength);
				ipArray = (IPAndLocationPojo[]) object;
				long endMS = System.currentTimeMillis();
				System.out.println("反序列化用时 : " + (endMS - startMs));
			} catch (IOException|ClassNotFoundException e) {
				e.printStackTrace();
			}		
		}else {
			// 不存在 就初始化 并写出
			try {
				long startMS = System.currentTimeMillis();
				pojoList = DataProcessManager.getPojoList(StaticValue.ip_lib_filepath, StaticValue.default_encoding);
				ipArray=DataProcessManager.convertListToArrayAndSort(pojoList);
				SerDeUtil.saveObj(ipArray, StaticValue.serde_obj_filepath, StaticValue.cacheByteArrayLength);
				long endMS = System.currentTimeMillis();
				System.out.println("序列化用时 : " + (endMS - startMS));
			} catch (Exception e) {
				e.printStackTrace();
			}	
		}		
	}
	
	public static List<IPAndLocationPojo> getPojoList(String filePath,String encoding) throws IOException{
		
		List<String> lineList =FileOperatorUtil.getLineList(filePath, encoding);
		List<IPAndLocationPojo> ipAndLocationPojos = new ArrayList<IPAndLocationPojo>();
		for (String	line : lineList) {
			if (line==null || line.trim().equals("")) {
				continue;
			}
			String[] columArray = line.split("\t");
			String startIP =columArray[0];
			String endIP=columArray[1];
			String location = columArray[2];
			IPAndLocationPojo ipAndLocationPojo =new IPAndLocationPojo(startIP, endIP, location);
			ipAndLocationPojos.add(ipAndLocationPojo);
		}
		return ipAndLocationPojos;
	}
	
	//将对象的集合形式 转换为 数组形式 并 进行排序
	public static IPAndLocationPojo[] convertListToArrayAndSort(List<IPAndLocationPojo> pojos) {
		IPAndLocationPojo[] ipAndLocationPojos =new IPAndLocationPojo[pojos.size()];
		pojos.toArray(ipAndLocationPojos);
		Arrays.sort(ipAndLocationPojos);
		return ipAndLocationPojos;
		
	}
	
	//二分法查找IP
	public static int binaraySearchIpPojo(IPAndLocationPojo[] ipPojos, String aidIP,int startIndex, int endIndex){
		if (startIndex>endIndex) {
			return -1;
		}
		int mid = (startIndex+endIndex)/2;
		/**
		 * 思路 :
		 * IP的值 如果 小于 起始IP段 说明在左边
		 * 如果 大于 结束IP段 说明在右边
		 */
		// 转换为long类型
		long IPLong = IPUtil.ipToLong(aidIP);
		if (IPLong >ipPojos[mid].getEndIPLong()) {
			startIndex =mid +1;
			
		}else if (IPLong< ipPojos[mid].getStartIPLong()) {
			endIndex = mid -1;
		}else {
			return mid;
		}
		return binaraySearchIpPojo(ipPojos, aidIP, startIndex, endIndex);
				
	}
	
	//对外提供接口,入参IP 出参归属地
	public static String getLocation(String ip) {
		if (!RegexUtil.isValidIP(ip)) {
			return "请保证输入IP地址的格式正确性!!";
		}
		int startIndex = 0;
		int endIndex = ipArray.length-1;
		int resultIndex = DataProcessManager.binaraySearchIpPojo(ipArray, ip, startIndex, endIndex);
		if (resultIndex == -1) {
			return null;
		}
		return ipArray[resultIndex].getLocation();
		
	}
}
