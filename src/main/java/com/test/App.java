package com.test;

import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * Hello world!
 *
 */
public class App 
{
	private static final boolean windows = 
            System.getProperty("os.name").startsWith("Windows");
    public static void main( String[] args )
    {
    	//UnixLogi
    	//System.out.println(App.class.getClassLoader().getResource("/").toString());
    	/*if (System.getProperty("java.vendor").contains("IBM")) {
    	      System.out.println( windows ? "com.ibm.security.auth.module.NTLoginModule"
    	       : "com.ibm.security.auth.module.LinuxLoginModule");    
    	    } else {
    	    	System.out.println( windows ? "com.sun.security.auth.module.NTLoginModule"
    	        : "com.sun.security.auth.module.UnixLoginModule");
    	    }*/
    	/*String str = "dubbo://10.20.144.96:20880/com.alibaba.da.pbserver.server.dubbo.api.IPBService2?anyhost=true&application=pbserver&check=false&decode.in.io=false&default.service.filter=dragoon&dubbo=2.4.9&interface=com.alibaba.da.pbserver.server.dubbo.api.IPBService2&methods=queryForList,getRealSql,queryForSingle&pid=4796&revision=2.0-SNAPSHOT&side=consumer&timeout=100000&timestamp=1370250375918&version=1.0.0";
    	Pattern pattern = Pattern.compile("^dubbo://(.+)/.+");
    	Matcher matcher = pattern.matcher(str);
    	while (matcher.find()) {  
            String va = matcher.group(1);  
            System.out.println(va);
        }  */
//    	 URI NAME = URI.create("file:///");
//    	 String authority = NAME.getAuthority();
//    	 System.out.println(NAME.getScheme());
//    	 System.out.println(authority);
    	String str = "pe/piire";
    	if(str.contains("/")){
    		System.out.println("yes");
    		String arg[] = str.split("/");
    		System.out.println(arg[0]);
    		System.out.println(arg[1]);
    	}else{
    		System.out.println("no");
    	}
    }
}
