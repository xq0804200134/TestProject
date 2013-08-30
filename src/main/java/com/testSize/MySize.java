package com.testSize;

import java.lang.instrument.Instrumentation;

public class MySize {
	 private static Instrumentation inst;
	 
	 /** initializes agent */
	    public static void premain(String agentArgs, Instrumentation instP) 
	    {
	        inst = instP;
	    }
	    //用来测量java对象的大小（这里先理解这个大小是正确的，后面再深化）
	    public static long sizeOf(Object o) {
	        if(inst == null) {
	           throw new IllegalStateException("Can not access instrumentation environment.\n" +
	              "Please check if jar file containing SizeOfAgent class is \n" +
	              "specified in the java's \"-javaagent\" command line argument.");
	         }
	         return inst.getObjectSize(o);
	     }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MySize.sizeOf("abc");
	}

}
