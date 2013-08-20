package com.ini4j;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.lang.StringUtils;
import org.ini4j.Ini;
import org.ini4j.InvalidFileFormatException;
import org.ini4j.Profile.Section;

import com.googlecode.aviator.AviatorEvaluator;

public class Ini4jTest {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InvalidFileFormatException 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		URL path = Ini4jTest.class.getResource("server.ini");
		File file = new File(path.toURI());
		 Ini conf = new Ini(file);
		 
		 Section sysConf = conf.get("system");

	     int brokerId = Ini4jTest.getInt(sysConf, "brokerId");
	     System.out.println("brokerId:"+brokerId);
	     
	     final Section zkConf = conf.get("zookeeper");
	     String diamondZKDataId = zkConf.get("diamondZKDataId");
	     System.out.println("diamondZKDataId:"+diamondZKDataId);

	}
	
	
	  private static int getInt(final Section section, final String key, final int defaultValue) {
	        final  String value = section.get(key);
	        if (StringUtils.isBlank(value)) {
	            return defaultValue;
	        }
	        else {
	            final Long rt = (Long) AviatorEvaluator.execute(value);
	            return rt.intValue();
	        }
	    }


	    private static int getInt(final Section section, final String key) {
	        final String value = section.get(key);
	        if (StringUtils.isBlank(value)) {
	            throw new NullPointerException("Blank value for " + key);
	        }
	        else {
	            final Long rt = (Long) AviatorEvaluator.execute(value);
	            return rt.intValue();
	        }
	    }


	    private static boolean getBoolean(final Section section, final String key) {
	        final String value = section.get(key);
	        if (StringUtils.isBlank(value)) {
	            throw new NullPointerException("Blank value for " + key);
	        }
	        else {
	            final Boolean rt = (Boolean) AviatorEvaluator.execute(value);
	            return rt;
	        }
	    }


	    private static long getLong(final Section section, final String key) {
	        final String value = section.get(key);
	        if (StringUtils.isBlank(value)) {
	            throw new NullPointerException("Blank value for " + key);
	        }
	        else {
	            final Long rt = (Long) AviatorEvaluator.execute(value);
	            return rt.longValue();
	        }
	    }

}
