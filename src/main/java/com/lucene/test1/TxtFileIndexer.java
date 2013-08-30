package com.lucene.test1;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import org.apache.lucene.document.Field;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Date;
import java.util.Random;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.FieldInfo.IndexOptions;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
/** 
* This class demonstrate the process of creating index with Lucene 
* for text files 
*/ 
public class TxtFileIndexer { 
     public static void main(String[] args) throws Exception{ 
     //indexDir is the directory that hosts Lucene's index files 
     File   indexDir = new File("D:\\tmp\\luceneIndex"); 
     //dataDir is the directory that hosts the text files that to be indexed 
     File   dataDir  = new File("D:\\tmp\\luceneData"); 
     Analyzer luceneAnalyzer = new StandardAnalyzer(Version.LUCENE_40); 
     File[] dataFiles  = dataDir.listFiles();
   
     Directory dir = FSDirectory.open(indexDir);
     
     IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_40, luceneAnalyzer);
     iwc.setOpenMode(OpenMode.CREATE_OR_APPEND);
     IndexWriter indexWriter = new IndexWriter(dir,iwc); 
     
     long startTime = new Date().getTime();
     for(int i = 0; i < dataFiles.length; i++){ 
          if(dataFiles[i].isFile() && dataFiles[i].getName().endsWith(".txt")){
               System.out.println("Indexing file " + dataFiles[i].getCanonicalPath()); 
               Document document = new Document();
               
               Field pathField = new StringField("path",dataFiles[i].getCanonicalPath(), Field.Store.YES);
               document.add(pathField);
               
               //FieldType type1 = new  FieldType();
              // type1.setIndexed(true);
              // type1.setStored(true);
              // type1.setIndexOptions(IndexOptions.DOCS_ONLY);
              // Field field1 = new Field("path","abc",type1);
               
               
               Field rangeField = new StringField("range",i+"", Field.Store.YES);
               document.add(rangeField);
               
               Field contentsField = new TextField("contents",getFileContent(dataFiles[i]), Field.Store.YES);
               document.add(contentsField);
               indexWriter.addDocument(document); 
          } 
     } 
     
     indexWriter.commit();
     indexWriter.close(); 
     long endTime = new Date().getTime(); 
        
     System.out.println("It takes " + (endTime - startTime) 
         + " milliseconds to create index for the files in directory "
         + dataDir.getPath());        
     } 
     
     /** 
      * ��ȡ�ļ����� 
      * @param file 
      * @return 
      * @throws Exception  
      */  
     public static String getFileContent(File file) throws Exception{  
         Reader reader = new InputStreamReader(new FileInputStream(file),"GBK");  
         BufferedReader br = new BufferedReader(reader);  
         String result ="";  
         while(br.readLine() != null){  
             result = result+"\n"+br.readLine();  
         }  
         br.close();  
         reader.close();  
         return result;  
     }
} 
