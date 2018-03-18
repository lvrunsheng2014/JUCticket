package com.lv.threadoraccess;
/**
 * 
 * @Description: 
 * 多线程之间按顺序调用，实现A->B->C
 * 三个线程启动，要求如下：
 * 
 * AA打印5次，BB打印10次，CC打印15次
 * 接着
 * AA打印5次，BB打印10次，CC打印15次
 * ......来10轮  
 * @author zzyy
 * @date 2018年3月17日
 */

import java.lang.reflect.Array;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Data{
	private int num=1;//A--1  B--2 C--3 ....
	private Lock lock=new ReentrantLock();
	private Condition co1=lock.newCondition();
	private Condition co2=lock.newCondition();
	private Condition co3=lock.newCondition();
	
	public void print5(int totalLoop) {
		lock.lock();
		try {
			//判断
			while(num!=1) {
				co1.await();
			}
			//干活
			for(int i = 1; i <= 5; i++) {
				System.out.println(Thread.currentThread().getName()+"\t"+i+"\t totalLoop"+totalLoop);
			}
			//通知
			num=2;
			co2.signal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}
	public void print10(int totalLoop) {
		lock.lock();
		try {
			//判断
			while(num!=2) {
				co2.await();
			}
			//干活
			for(int i = 1; i <= 10; i++) {
				System.out.println(Thread.currentThread().getName()+"\t"+i+"\t totalLoop"+totalLoop);
			}
			//通知
			num=3;
			co3.signal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}
	public void print15(int totalLoop) {
		lock.lock();
		try {
			//判断
			while(num!=3) {
				co3.await();
			}
			//干活
			for(int i = 1; i <= 15; i++) {
				System.out.println(Thread.currentThread().getName()+"\t"+i+"\t totalLoop"+totalLoop);
			}
			//通知
			num=1;
			co1.signal();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}
}



public class ThreadOrderAccess {
public static void main(String[] args) {
	Data d=new Data();
	new Thread(() -> {
		for (int i = 1; i <= 10; i++) {
			d.print5(i);
		}
	}, "AA").start();
	new Thread(() -> {
		for (int i = 1; i <= 10; i++) {
			d.print10(i);
		}
	}, "BB").start();
	new Thread(() -> {
		for (int i = 1; i <= 10; i++) {
			d.print15(i);
		}
	}, "CC").start();
}
}
