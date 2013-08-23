package com.lucene.test2;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;

public class TestMetaPhone {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		MetaPhoneAnalyzer meta = new MetaPhoneAnalyzer();
		TokenStream tokenStream = meta.tokenStream("content", 
				new StringReader("the quick brown phox jumped over the lazy dog"));
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
