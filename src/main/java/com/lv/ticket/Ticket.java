package com.lv.ticket;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * 多线程写的规则：线程     操作方法  资源类
 * 				  高内聚 低耦合
 * @author lvrun
 *
 */
//资源类
class Ticket {
	private Integer number=1000;
	private Lock lock=new ReentrantLock();
	
	public void sale() {
		lock.lock();
		try {
			if(number>0) {
				System.out.println(Thread.currentThread().getName()+"已经卖出"+number--+"张票"+"还剩"+number+"张票");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	
}


	
