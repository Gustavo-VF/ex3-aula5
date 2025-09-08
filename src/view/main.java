package view;

import java.util.concurrent.Semaphore;

import controller.corridaController;

public class main {

	public static void main(String[] args) {
		Semaphore semaforo = new Semaphore(3);
		Thread[] correr = new corridaController[25];
		for (int i = 1; i < 26; i++) {
			correr[i-1] = new corridaController(i, semaforo);
			correr[i-1].start();
			
		}
		
		for (int i = 0; i < 25; i++) {
		try {
			correr[i].join();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		}

		for (int i = 0; i < 25; i++) {
			System.out.println("colocado-" + corridaController.colocacao[i][0] + " chegou em " + (i + 1) + " e fez "
					+ corridaController.colocacao[i][1] + " pontos");
		}
	}
}
