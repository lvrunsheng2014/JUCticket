package com.lv.CountDownLatchTest;
import java.util.concurrent.CountDownLatch;
/**
 * Talk is cheap,show me your code
 * 
 * @Description: CountDownLatch  
 * @author zzyy
 * @date 2018年3月17日
 *让一些线程阻塞直到另一些线程完成一系列操作后才被唤醒。
 * 
 * CountDownLatch主要有两个方法，当一个或多个线程调用await方法时，这些线程会阻塞。
 * 其它线程调用countDown方法会将计数器减1(调用countDown方法的线程不会阻塞)，
 * 当计数器的值变为0时，因await方法阻塞的线程会被唤醒，继续执行。
 * 
 * 解释：6个同学陆续离开教室后值班同学才可以关门。
 * 也即	秦灭6国，一统华夏
 * main主线程必须要等前面5个线程完成全部工作后，自己才能开干
 */
public class CountDownLatchDemo {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
	
	
	
		CountDownLatch cdl=new CountDownLatch(6);
		for (int i = 1; i <= 6; i++) {
			new Thread(() -> {
				System.out.println(Thread.currentThread().getName()+"\t"+"国被灭亡");
				cdl.countDown();
			}, CountyEmes.forEachCountyEmes(i).getRetName()).start();
		}
			cdl.await();
			System.out.println(Thread.currentThread().getName()+"\t"+"秦灭6国，一统华夏");
		}
		
		//6个同学陆续离开教室后值班同学才可以关门。
		/*CountDownLatch cdl=new CountDownLatch(6);
		for (int i = 1; i <= 6; i++) {
			new Thread(() -> {
				System.out.println(Thread.currentThread().getName()+"\t"+"号同学下课走人");
				cdl.countDown();
			}, String.valueOf(i)).start();
		}
		cdl.await();
		System.out.println(Thread.currentThread().getName()+"\t"+"锁门走人");*/
		
	}


