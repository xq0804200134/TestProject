package com.jmx.test1;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;
import javax.management.ObjectName;

import com.sun.jdmk.comm.HtmlAdaptorServer;

public class HelloAgent {
	public static void main(String[] args) throws Exception {
		// MBeanServer server = MBeanServerFactory.createMBeanServer();
		MBeanServer server = ManagementFactory.getPlatformMBeanServer();
		ObjectName helloName = new ObjectName("chengang:name=HelloWorld");
		server.registerMBean(new Hello(), helloName);
		ObjectName adapterName = new ObjectName(
				"HelloAgent:name=htmladapter,port=8082");
		HtmlAdaptorServer adapter = new HtmlAdaptorServer();
		server.registerMBean(adapter, adapterName);
		adapter.start();
		System.out.println("start.....");
	}
}
