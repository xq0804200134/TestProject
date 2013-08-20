package com.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.PosixParser;

public class PosixParseTest {
	public static void main(String []args) {
		PosixParser parser = new PosixParser();
		String[] args1 = new String[] { "-a",
                "-b", "toast,1,2",",",
                "foo", "bar","-c"};
		
		Option option = new Option("b", "bfile", true, "set the value of [b]");
		option.setValueSeparator(',');
		option.setArgs(3);
		
		
		Options options = new Options()
        .addOption("a", "enable-a", false, "turn [a] on or off")
        .addOption(option)
        .addOption("c", "copt", false, "turn [c] on or off");
	

        try
        {
        	CommandLine cl = parser.parse(options, args1);
        	Option []ops = cl.getOptions();
        	System.out.println(ops.length);
        	System.out.println();
        	for(int i=0;i<ops.length;i++){
        		System.out.println("getArgName:"+ops[i].getArgName());
        		System.out.println("getArgs:"+ops[i].getArgs());
        		System.out.println("getDescription:"+ops[i].getDescription());
        		System.out.println("getId:"+ops[i].getId());
        		System.out.println("getOpt:"+ops[i].getOpt());
        		System.out.println("getType:"+ops[i].getType());
        		System.out.println("value:"+ops[i].getValue());
        		if(ops[i].getValues()!=null)
        		System.out.println("getValues:"+ops[i].getValues()[1]);
        		System.out.println();
        		System.out.println();
        	}
        }
        catch (Exception e)
        {
        	e.printStackTrace();
        }
	}

}
