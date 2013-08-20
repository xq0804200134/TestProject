package com.test.mutlThread;

import java.io.Serializable;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public class Main {
	public static void main(String []args) throws Exception{
		FutureTask<String> future = new FutureTask<String>(new Callable<String>() {
			public String call() throws Exception {
				// TODO Auto-generated method stub
				Thread.currentThread().sleep(2000);
				return "abc";
			}
			
		});
		future.get(10, TimeUnit.MILLISECONDS);
		if(future.isDone()){
			
		}
		
		
	}

}
