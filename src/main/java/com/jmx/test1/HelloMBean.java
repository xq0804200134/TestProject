package com.jmx.test1;

public interface HelloMBean {
	public String getName();

	public void setName(String name);

	public void printHello();

	public void printHello(String whoName);
}
