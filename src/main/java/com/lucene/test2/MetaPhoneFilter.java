package com.lucene.test2;

import java.io.IOException;

import org.apache.commons.codec.language.Metaphone;
import org.apache.lucene.analysis.TokenFilter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;

public class MetaPhoneFilter extends TokenFilter {
	private Metaphone metaPhoner = new Metaphone();
	private CharTermAttribute termAttr;
	private TypeAttribute typeAttr;

	protected MetaPhoneFilter(TokenStream input) {
		super(input);
		// TODO Auto-generated constructor stub
		termAttr = addAttribute(CharTermAttribute.class);
		typeAttr = addAttribute(TypeAttribute.class);
	}

	@Override
	public boolean incrementToken() throws IOException {
		// TODO Auto-generated method stub
		if(!input.incrementToken())
			return false;
		String encode = metaPhoner.encode(termAttr.toString());
		termAttr.setEmpty();
		termAttr.append(encode);
		typeAttr.setType("metaPhone");
		return true;
	}

}
