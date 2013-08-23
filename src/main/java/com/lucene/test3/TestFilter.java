package com.lucene.test3;

import java.io.File;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.Explanation;
import org.apache.lucene.search.Filter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.NumericRangeFilter;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.TermRangeFilter;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class TestFilter {

	static File indexDir = new File("D:\\tmp\\luceneIndex");
	static Directory directory ;
	static{
		try{
			directory = FSDirectory.open(indexDir);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static void query(Query query,Filter filter) throws Exception{
		IndexReader reader = DirectoryReader.open(directory);
		IndexSearcher searcher = new IndexSearcher(reader);
		TopDocs results = searcher.search(query, filter,30,Sort.INDEXORDER);
		ScoreDoc[] hits = results.scoreDocs;

		int numTotalHits = results.totalHits;
		System.out.println(numTotalHits + " total matching documents");

		for (int i = 0; i < hits.length; i++) {
			System.out.println("doc=" + hits[i].doc + " score=" + hits[i].score);
			Document doc = searcher.doc(hits[i].doc);
			String path = doc.get("path");
			String range = doc.get("range");
			Explanation expl = searcher.explain(query, hits[i].doc);
			//System.out.println(expl.toString());
			if (path != null) {
				System.out.println((i + 1) + ". " + path);
				System.out.println("range:"+range);
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
	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		testFilterSearch();
	}
    public static void testFilterSearch() throws Exception{
        
    	Term term = new Term("contents","scree"); 
	    PrefixQuery query = new PrefixQuery(term); 
	    System.out.println(query.toString()); 
	    Filter filter=null;
	    //1.域   2.起始位置  3.结束位置   4.是否包含起始位置    5.是否包含结束位置   
        filter =TermRangeFilter.newStringRange("range","4","9",true,true);
        
      //使用NumericRangeFilter进行过滤
        //filter=NumericRangeFilter.newIntRange("size", 10, 5000, true, true);
	    query(query,filter);
    }

}
