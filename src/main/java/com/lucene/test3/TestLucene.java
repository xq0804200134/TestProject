package com.lucene.test3;

import java.io.File;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.ParallelAtomicReader;
import org.apache.lucene.index.SegmentInfos;
import org.apache.lucene.index.SegmentReader;
import org.apache.lucene.search.FieldCache;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

public class TestLucene {

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
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static void testCache(){
		//AtomicReader  reader = new 
		
		float [] weights = FieldCache.DEFAULT.getFloats(reader, "contents", false);
	}

}
