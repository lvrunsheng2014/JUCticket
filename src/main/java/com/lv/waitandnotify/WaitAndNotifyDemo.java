package com.lv.waitandnotify;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/*
 * java 8 后的线程的通知和唤醒机制 我们用await和signalAll来做
 */
class Data {
	private Integer num=0;
	private Lock lock=new ReentrantLock();
	private Condition co=lock.newCondition();
	public void increment() {
		lock.lock();
		try {
			while (num != 0) {
				co.await();

			}
			++num;
			System.out.println(Thread.currentThread().getName() + "----" + num);
			co.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	public void decrement() {
		lock.lock();
		try {
			while(num==0) {
				co.await();
			}
			--num;
			System.out.println(Thread.currentThread().getName()+ "----" + num);
			co.signalAll();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}

	}
	/*public synchronized void increment() throws InterruptedException {
		while(num!=0) {
		this.wait();
			
		}
		++num;
		System.out.println(Thread.currentThread().getName()+"----"+num);
		this.notifyAll();
}
	public synchronized void decrement() throws InterruptedException {
		while(num==0) {
		this.wait();
			
		}
		--num;
		System.out.println(Thread.currentThread().getName()+"----"+num);
		this.notifyAll();
}*/
}

public class WaitAndNotifyDemo{
	public static void main(String[] args) {
		Data d=new Data();
		new Thread(() -> {
			
				try {
					
					for(int i=1;i<=10;i++)	d.increment();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}, "aa").start();
		new Thread(() -> {

			try {
				for(int i=1;i<=20;i++)	d.decrement();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}, "bb").start();
		new Thread(() -> {

			try {
				for(int i=1;i<=20;i++)	d.increment();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}, "cc").start();
		new Thread(() -> {

			try {
				for(int i=1;i<=10;i++)	d.decrement();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}, "dd").start();
		
	}
}