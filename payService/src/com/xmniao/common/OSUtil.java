package com.xmniao.common;

import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class OSUtil {

	/**
	 * 
	 * 判断当前操作是否windows
	 * 
	 * @return true---是Windows操作系統
	 */

	public static boolean isWindowsOS() {

		boolean isWindowsOS = false;

		String osName = System.getProperty("os.name");

		if (osName.toLowerCase().indexOf("windows") > -1) {

			isWindowsOS = true;

		}

		return isWindowsOS;

	}

	/**
	 * 
	 * 獲取本機IP地址，並自動區分Windows還是Linux操作系統 获取本机IP地址，并自动区分windows还是Linux操作系统
	 * 
	 * @return String
	 */

	public static String getLocalIP() {

		String sIP = "";

		InetAddress ip = null;

		try {

			// 如果是Windows操作系統

			if (isWindowsOS()) {

				ip = InetAddress.getLocalHost();

			}

			// 如果是Linux操作系統

			else {

				boolean bFindIP = false;

				Enumeration<NetworkInterface> netInterfaces = (Enumeration<NetworkInterface>) NetworkInterface

				.getNetworkInterfaces();

				while (netInterfaces.hasMoreElements()) {

					if (bFindIP) {

						break;

					}

					NetworkInterface ni = (NetworkInterface) netInterfaces

					.nextElement();

					// ----------特定情況，可以考虑用ni.getName判断

					// 遍历所有ip

					Enumeration<InetAddress> ips = ni.getInetAddresses();

					while (ips.hasMoreElements()) {

						ip = (InetAddress) ips.nextElement();

						if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress() // 127.开头的都是lookback地址

								&& ip.getHostAddress().indexOf(":") == -1) {

							bFindIP = true;

							break;

						}

					}

				}

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		if (null != ip) {

			sIP = ip.getHostAddress();

		}

		return sIP;

	}
	
	public static String getLocalIP_() {
		
		String localIP =getLocalIP();
		localIP = localIP.replace(".", "_");
		return localIP;
	}
		
	/**
	 * 
	 * 方法描述：获取当前进程ID
	 * 创建人： ChenBo
	 * 创建时间：2017年6月23日
	 * @return String
	 */
	public static String getPid(){
		String name = ManagementFactory.getRuntimeMXBean().getName();  
        return name.split("@")[0];
	}

}