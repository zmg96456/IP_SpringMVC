package com.tledu.aaa.pojo;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import com.tledu.aaa.util.Base64Util;
import com.tledu.aaa.util.IPUtil;

public class IPAndLocationPojo implements Comparable<IPAndLocationPojo>,Serializable{

	private static final long serialVersionUID = 1L;
	private transient String startIP;
	private transient String endIP;
	private String location;
	
	// 衍生字段
	private long startIPLong;
	private long endIPLong;
	
	public long getStartIPLong() {
		return startIPLong;
	}
	public void setStartIPLong(long startIPLong) {
		this.startIPLong = startIPLong;
	}
	public long getEndIPLong() {
		return endIPLong;
	}
	public void setEndIPLong(long endIPLong) {
		this.endIPLong = endIPLong;
	}
	public String getStartIP() {
		return startIP;
	}
	public void setStartIP(String startIP) {
		this.startIP = startIP;
	}
	public String getEndIP() {
		return endIP;
	}
	public void setEndIP(String endIP) {
		this.endIP = endIP;
	}
	public String getLocation() {
		try {
			return Base64Util.decode(location);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public IPAndLocationPojo(String startIP, String endIP, String location) {
		super();
		this.startIP = startIP;
		this.endIP = endIP;
		try {
			this.location = Base64Util.encode(location);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		this.startIPLong = IPUtil.ipToLong(startIP);
		this.endIPLong=IPUtil.ipToLong(endIP);
	}
	public IPAndLocationPojo() {
		super();
	}
	@Override
	public String toString() {
		return "IPAndLocationPojo [startIP=" + startIP + ", endIP=" + endIP
				+ ", location=" + location + ", startIPLong=" + startIPLong
				+ ", endIPLong=" + endIPLong + "]";
	}
	@Override
	public int compareTo(IPAndLocationPojo o) {
		long status=this.startIPLong-o.startIPLong;
		return status >0 ? 1 :(status<0 ?-1 : 0);
	}
	
	
}
