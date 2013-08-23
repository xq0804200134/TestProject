package com.lucene.test1;

import java.io.File;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
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
import org.apache.lucene.util.Version;

public class TestSort {

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
		//QueryParser();
		tempQuery();
	}

	
	public static void tempQuery() throws Exception{
		String queryStr = "looks";
		Term term = new Term("contents", queryStr.toLowerCase());
		TermQuery query = new TermQuery(term);
		query(query);

	}
	public static void QueryParser() throws Exception{
		String str  = "A bright blue GARBAGE";
		Analyzer luceneAnalyzer = new StandardAnalyzer(Version.LUCENE_40); 
		QueryParser queryParser = new QueryParser(Version.LUCENE_40,"contents",luceneAnalyzer);
		Query query = queryParser.parse(str);
		query(query);
	}
	
	public static void PrefixQuery()  throws Exception{
		Term term = new Term("contents","scree"); 
	    PrefixQuery query = new PrefixQuery(term); 
	    System.out.println(query.toString()); 
	    query(query);
		
	}
	
	private static void query(Query query) throws Exception{
		IndexReader reader = DirectoryReader.open(directory);
		IndexSearcher searcher = new IndexSearcher(reader);
		TopDocs results = searcher.search(query, 10,Sort.RELEVANCE);
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
