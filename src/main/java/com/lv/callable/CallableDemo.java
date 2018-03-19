package com.lv.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * callable接口获得多线程
 * @author lvrun
 *
 */
class  MyThread implements Callable<Integer>{

	@Override
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("***********callable");
		TimeUnit.SECONDS.sleep(4);
		return 171018;
	}
	
}




public class CallableDemo {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		FutureTask<Integer> fu=new FutureTask<Integer>(new MyThread()) ;
		new Thread(fu, "AA").start();
		new Thread(fu, "BB").start();
		System.out.println(Thread.currentThread().getName()+"---------------我咋活啊");
		Integer result = fu.get();
		
		
		System.out.println("result----"+result);
		
	}
}
