package com.lv.ticket;


	public  class SaleTicket {
		public static void main(String[] args) {
			Ticket t=new Ticket();
			
			//lambda表达式来做
			new Thread(() -> {for(int i=1;i<=1000;i++) t.sale();}, "aa").start();
			new Thread(() -> {for(int i=1;i<=1000;i++) t.sale();}, "bb").start();
			new Thread(() -> {for(int i=1;i<=1000;i++) t.sale();}, "cc").start();
			
				
			
			/* new Thread(new Runnable() {

				@Override
				public void run() {
					for(int i=1;i<=30;i++) {
						t.sale();
					}
				}
			}, "aa").start();
			 
			 new Thread(new Runnable() {

					@Override
					public void run() {
						for(int i=1;i<=30;i++) {
							t.sale();
						}
					}
				}, "bb").start();
			 
			 new Thread(new Runnable() {

					@Override
					public void run() {
						for(int i=1;i<=30;i++) {
							t.sale();
						}
					}
				}, "cc").start();*/
		}
	
}
