package com.test2;

import java.io.IOException;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class Main {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String str="23+4*(5+1);str=\"HelloWorld\";";
		ANTLRInputStream input =new ANTLRInputStream(str);
		a1Lexer lexer =new a1Lexer(input);
		CommonTokenStream tokens=new CommonTokenStream(lexer);
		a1Parser parser=new a1Parser(tokens);
		a1Parser.ProgramContext r=parser.program();
		//System.out.println(r.getChild(0).getText());
		//System.out.println(r.getChild(1).getText());
		ParseTree tree = r.getChild(0);
		print(tree);
		//System.out.println(r.toStringTree());
	}
	public static void  print(ParseTree tree){
		for(int i=0;i<tree.getChildCount();i++){
			if(tree.getChild(i).getChildCount()>0){
				print(tree.getChild(i));
			}
			else
			System.out.println(tree.getChild(i).getText());
		}
	}

}
