package com.lucene.test2;

import java.io.Reader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.util.Version;

public class MetaPhoneAnalyzer extends Analyzer {

	@Override
	protected TokenStreamComponents createComponents(String fieldName,
			Reader reader) {
		StandardTokenizer src = new StandardTokenizer(Version.LUCENE_40, reader);
		TokenStream token = new MetaPhoneFilter(
				new LowerCaseFilter(Version.LUCENE_40,
					new StandardFilter(Version.LUCENE_40, 
							src)));
		return new TokenStreamComponents(src,token);
	}

}
