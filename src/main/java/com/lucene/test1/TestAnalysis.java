package com.lucene.test1;

import java.io.StringReader;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;
import org.apache.lucene.util.Version;

public class TestAnalysis {

	/**
	 * @param args
	 */
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		simpleAna();
	}

	public static void simpleAna() throws Exception {
		//SimpleAnalyzer simple = new SimpleAnalyzer(Version.LUCENE_40);
		//WhitespaceAnalyzer simple = new WhitespaceAnalyzer(Version.LUCENE_40);
		StandardAnalyzer simple = new  StandardAnalyzer(Version.LUCENE_40);
		TokenStream tokenStream = simple.tokenStream("contents", new StringReader(
				"I'm a student. these are apples"));
		//tokenStream.reset();  
		CharTermAttribute termAtt = (CharTermAttribute) tokenStream
				.addAttribute(CharTermAttribute.class);
		TypeAttribute typeAtt = (TypeAttribute) tokenStream
				.addAttribute(TypeAttribute.class);
		OffsetAttribute offsetAtt = (OffsetAttribute) tokenStream
				.addAttribute(OffsetAttribute.class);
		PositionIncrementAttribute posAtt = (PositionIncrementAttribute) tokenStream
				.addAttribute(PositionIncrementAttribute.class);
		while (tokenStream.incrementToken()) {
			System.out.println(termAtt.toString() + " " + typeAtt.type() + " ("
					+ offsetAtt.startOffset() + "," + offsetAtt.endOffset()
					+ ")   " + posAtt.getPositionIncrement());
		}
	}

}
