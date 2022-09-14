package controller;

import java.util.concurrent.Semaphore;

public class ThreadClass extends Thread {
	
	private Semaphore semaforo;
	
	public ThreadClass(Semaphore semaforo) {
		this.semaforo = semaforo;
		
	}
	
	@Override
	public void run() {
		setup();
	}
	
	private void setup() {
		long threadId = getId();
		if(threadId%3 == 1) {
			int random = (int) ((Math.random() * 801) + 200);
			
			calc(threadId, random);
			banco(threadId, 1000);
			calc(threadId, random);
			banco(threadId, 1000);
		}
		
		if(threadId%3 == 2) {
			int random = (int) ((Math.random() * 1001) + 500);

			calc(threadId, random);
			banco(threadId, 1500);
			calc(threadId, random);
			banco(threadId, 1500);
			calc(threadId, random);
			banco(threadId, 1500);
		}
		
		if(threadId%3 == 3) {
			int random = (int) ((Math.random() * 1001) + 1000);

			calc(threadId, random);
			banco(threadId, 1500);
			calc(threadId, random);
			banco(threadId, 1500);
		}
	}
	
	private void calc(long id, int time) {
		System.out.println("#"+id+" - CALCULANDO");
		pausa(time);
	}
	
	/**
	 * 
	 */
	private void banco(long id, int time) {
		try {
			semaforo.acquire();
			System.out.println("#"+id+" - SALVANDO DADOS");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
			pausa(time);
		}
	}
	
	private void pausa(int time) {
		try {
			sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
