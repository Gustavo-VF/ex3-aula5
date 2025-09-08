package view;

import java.util.concurrent.Semaphore;

import controller.corridaController;

public class main {

	public static void main(String[] args) {
		Semaphore semaforo = new Semaphore(3);                // semaforo de 3 interações por vez
		Thread[] correr = new corridaController[25];          // criei um a tread fora do pro outro for porta acesar e criei como vetor para armazenala  
		for (int i = 1; i < 26; i++) {                        // for inicia normalmente as 25 threads
			correr[i-1] = new corridaController(i, semaforo);
			correr[i-1].start();
			
		}
		
		for (int i = 0; i < 25; i++) {     // for apara impadir que o for de mostrar mostre antes da corrida acabar
		try {
			correr[i].join();              // o .join() faz com que o proxima ação do codigo espere a thread terminar de rodar // entao meo que ese for começa
		} catch (InterruptedException e) { // na primeira Thread espera ela acabar e passa pra procima até a utima ter acabado ai termina o for
			                               // dando tempo certinho pro for de mostrar não mostrar assim que iniciar a corrida 
			e.printStackTrace();           
		}                                  // (informação: uma solução que dava pra usar nos sapinhos pra mostrar a colocaão só depois)
		}

		for (int i = 0; i < 25; i++) { // for de mostrar
			System.out.println("colocado-" + corridaController.colocacao[i][0] + " chegou em " + (i + 1) + " e fez "
					+ corridaController.colocacao[i][1] + " pontos");
		}
	}
}
