package com.lucene.test1;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.Explanation;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.DisjunctionMaxQuery;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.spans.SpanTermQuery;
import org.apache.lucene.util.Version;

/**
 * This class is used to demonstrate the process of searching on an existing
 * Lucene index
 * 
 */
public class TxtFileSearcher {
	static File indexDir = new File("D:\\tmp\\luceneIndex");
	static Directory directory;
	static {
		try {
			directory = FSDirectory.open(indexDir);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void tempQuery() throws Exception {
		String queryStr = "looks";
		Term term = new Term("contents", queryStr.toLowerCase());
		TermQuery query = new TermQuery(term);
		query(query);

	}

	public static void TermRangeQuery() throws Exception {
		TermRangeQuery query = TermRangeQuery.newStringRange("range", "5", "6",
				true, true);
		query(query);
	}

	public static void BooleanQuery() throws Exception {
		Term term1 = new Term("contents", "fast");
		Term term2 = new Term("contents", "across");
		TermQuery query1 = new TermQuery(term1);
		TermQuery query2 = new TermQuery(term2);
		BooleanQuery query = new BooleanQuery();
		query.add(query1, BooleanClause.Occur.MUST);
		query.add(query2, BooleanClause.Occur.MUST);
		query(query);

	}

	public static void PrefixQuery() throws Exception {
		Term term = new Term("contents", "scree");
		PrefixQuery query = new PrefixQuery(term);
		System.out.println(query.toString());
		query(query);

	}

	public static void QueryParser() throws Exception {
		String str = "A bright blue GARBAGE";
		Analyzer luceneAnalyzer = new StandardAnalyzer(Version.LUCENE_40);
		QueryParser queryParser = new QueryParser(Version.LUCENE_40,
				"contents", luceneAnalyzer);
		Query query = queryParser.parse(str);
		query(query);
	}

	public static void MultiFieldQueryParser() throws Exception {
		String[] fields = { "range", "contents" };
		String[] values = { "1", "bright" };
		Analyzer luceneAnalyzer = new StandardAnalyzer(Version.LUCENE_40);
		Query query = MultiFieldQueryParser.parse(Version.LUCENE_40, values,
				fields, new BooleanClause.Occur[] { BooleanClause.Occur.MUST,BooleanClause.Occur.MUST },
				luceneAnalyzer);
		query(query);
	}

	public static void DisjunctionMaxQuery() throws Exception {
		//默认按照or操作
		DisjunctionMaxQuery query = new DisjunctionMaxQuery(1.0f);
		query.add(new TermQuery(new Term("contents", "bright")));
		query.add(new TermQuery(new Term("contents", "blue")));
		query(query);
	}

	private static void query(Query query) throws Exception {
		IndexReader reader = DirectoryReader.open(directory);
		IndexSearcher searcher = new IndexSearcher(reader);
		TopDocs results = searcher.search(query, 10);
		ScoreDoc[] hits = results.scoreDocs;

		int numTotalHits = results.totalHits;
		System.out.println(numTotalHits + " total matching documents");

		for (int i = 0; i < hits.length; i++) {
			System.out
					.println("doc=" + hits[i].doc + " score=" + hits[i].score);
			Document doc = searcher.doc(hits[i].doc);
			String path = doc.get("path");
			Explanation expl = searcher.explain(query, hits[i].doc);
			// System.out.println(expl.toString());
			if (path != null) {
				System.out.println((i + 1) + ". " + path);
			} else {
				System.out
						.println((i + 1) + ". " + "No path for this document");
			}
			// String content = doc.get("contents");
			// System.out.println(content);
			System.out
					.println("////////////////////////////////////////////////////////////////////////////");
		}
		reader.close();
	}

	public static void main(String[] args) throws Exception {
		// TermRangeQuery();
		// BooleanQuery();
		// PrefixQuery();
		DisjunctionMaxQuery();
	}
}
