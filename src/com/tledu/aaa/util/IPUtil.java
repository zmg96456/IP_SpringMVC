package com.tledu.aaa.util;

public class IPUtil {
	
	public static void main(String[] args) {
		String ip="0.0.0.0";
		long longIP = ipToLong(ip);
		String ipp= longToIp(longIP);
		String ip1 = longToIp(16778238);
		System.out.println(ipp+" ---"+ip+"---"+longIP+"   ---"+ip1);
	}
	
	public static long ipToLong(String ipString) {
		long result = 0;
		java.util.StringTokenizer token = new java.util.StringTokenizer(
				ipString, ".");
		result += Long.parseLong(token.nextToken()) << 24;
		result += Long.parseLong(token.nextToken()) << 16;
		result += Long.parseLong(token.nextToken()) << 8;
		result += Long.parseLong(token.nextToken());
		return result;
	}

	public static String longToIp(long ipLong) {
		StringBuilder sb = new StringBuilder();
		sb.append(ipLong >>> 24);
		sb.append(".");
		sb.append(String.valueOf((ipLong & 0x00FFFFFF) >>> 16));
		sb.append(".");
		sb.append(String.valueOf((ipLong & 0x0000FFFF) >>> 8));
		sb.append(".");
		sb.append(String.valueOf(ipLong & 0x000000FF));
		return sb.toString();
	}
}
