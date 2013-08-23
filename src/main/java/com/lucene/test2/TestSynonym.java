package com.lucene.test2;

import java.io.File;
import java.io.IOException;

import org.apache.commons.codec.language.Metaphone;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.synonym.SynonymMap;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Explanation;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.CharsRef;
import org.apache.lucene.util.Version;

public class TestSynonym {

	public static  SynonymMap synonymMap = null;
	static{
		try {
			init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void  init() throws Exception{
		String base1 = "leaving"; 
		String syn1 = "me"; 
		String base2 = "slow"; 
		String syn2 = "sluggish"; 

		SynonymMap.Builder sb = new SynonymMap.Builder(true); 
		sb.add(new CharsRef(base1), new CharsRef(syn1), true); 
		sb.add(new CharsRef(base2), new CharsRef(syn2), true); 
		synonymMap = sb.build(); 
	}
	
	static File indexDir = new File("D:\\tmp\\luceneIndex");
	static Directory directory ;
	static{
		try{
			directory = FSDirectory.open(indexDir);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	
	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		QueryParser();
		System.out.println();
		System.out.println();
		System.out.println();
		SynonyQuery(); 
	}
	
	public static void SynonyQuery() throws Exception{
		String str  = "leaving";
		SynonymAnalyzer synonymAnalyzer = new SynonymAnalyzer(synonymMap);
		QueryParser queryParser = new QueryParser(Version.LUCENE_40,"contents",synonymAnalyzer);
		Query query = queryParser.parse(str);
		query(query);
	}
	
	public static void QueryParser() throws Exception{
		String str  = "leaving";
		Analyzer luceneAnalyzer = new StandardAnalyzer(Version.LUCENE_40); 
		QueryParser queryParser = new QueryParser(Version.LUCENE_40,"contents",luceneAnalyzer);
		Query query = queryParser.parse(str);
		query(query);
	}
	
	private static void query(Query query) throws Exception{
		IndexReader reader = DirectoryReader.open(directory);
		IndexSearcher searcher = new IndexSearcher(reader);
		TopDocs results = searcher.search(query, 30,Sort.INDEXORDER);
		ScoreDoc[] hits = results.scoreDocs;

		int numTotalHits = results.totalHits;
		System.out.println(numTotalHits + " total matching documents");

		for (int i = 0; i < hits.length; i++) {
			System.out.println("doc=" + hits[i].doc + " score=" + hits[i].score);
			Document doc = searcher.doc(hits[i].doc);
			String path = doc.get("path");
			Explanation expl = searcher.explain(query, hits[i].doc);
			//System.out.println(expl.toString());
			if (path != null) {
				System.out.println((i + 1) + ". " + path);
			} else {
				System.out
						.println((i + 1) + ". " + "No path for this document");
			}
			//String content = doc.get("contents");
			//System.out.println(content);
			System.out.println("////////////////////////////////////////////////////////////////////////////");
		}
		reader.close();
	}
	

}
