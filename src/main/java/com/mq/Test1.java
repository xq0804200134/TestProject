package com.mq;

public class Test1 {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		DataExchange dataExchange = new DataExchange();
		dataExchange.dataSend("hello world..");
		Thread.currentThread().sleep(1000);
		dataExchange.dataReceive();
		System.out.println("end");
	}

}
