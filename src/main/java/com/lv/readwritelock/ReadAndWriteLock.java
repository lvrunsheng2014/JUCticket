package com.lv.readwritelock;
/**
 * 
 * @Description: 一个线程写入,100个线程读取
 * @author zzyy
 * @date 2018年3月17日
 */


import java.util.concurrent.locks.ReentrantReadWriteLock;

class Date{
	private Object obj;
	private ReentrantReadWriteLock rw=new ReentrantReadWriteLock();
	public void writeLock(Object obj) {
		rw.writeLock().lock();
		try {
			this.obj=obj;
			System.out.println(Thread.currentThread().getName()+"\t"+obj);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rw.writeLock().unlock();
		}

	}
	
	public void readLock() {
		rw.readLock().lock();
		try {
			System.out.println(Thread.currentThread().getName()+"\t"+obj);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			rw.readLock().unlock();
		}

	}
}


public class ReadAndWriteLock {
	public static void main(String[] args) {
		Date da=new Date();
		new Thread(() -> {
			da.writeLock("多线程的读写分离");
		}, "aaa").start();
		for (int i = 1; i <= 100; i++) {
		new Thread(() -> {
			
				da.readLock();
			
		}, String.valueOf(i)).start();
		}
	}
}
