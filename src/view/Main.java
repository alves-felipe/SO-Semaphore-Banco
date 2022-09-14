package view;

import java.util.concurrent.Semaphore;
import controller.ThreadClass;

public class Main {
	
	public static void main(String[] args) {
		Semaphore semaforo = new Semaphore(1);
		
		for(int i = 0; i < 21; i++) {
			ThreadClass tc = new ThreadClass(semaforo);
			tc.start();
		}
	}

}
