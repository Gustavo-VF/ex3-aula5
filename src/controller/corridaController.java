package controller;

import java.util.concurrent.Semaphore;

public class corridaController extends Thread {

	private int id;
	private Semaphore semaforo;
	private int pontacaoTiro = 0;
	private static int pontosColocacao = 250;
	private int pontuacaoTotal = 0;
	public static int[][] colocacao = new int[25][2];
	private static int indice = 0;

	public corridaController(int _id, Semaphore _semaforo) {
		this.id = _id;
		this.semaforo = _semaforo;

	}

	public void run() {
		corrida();
		tiroAlvo();
		ciclismo();
		chegada();
	}

	private void corrida() {
		int percurso = 3000;
		int percorrido = 0;
		int velocidade;
		try {
			System.out.println("corredor-" + id + " iniciou a corrida");
			while (percorrido < percurso) {
				velocidade = (int) ((Math.random() * 6) + 20);
				sleep(30);
				percorrido += velocidade;
				if (percorrido > 3000) {
					percorrido = 3000;
					System.out.println("corredor-" + id + " finalizou a corrida");

				}

			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void tiroAlvo() {
		try {
			semaforo.acquire();
			for (int i = 0; i < 3; i++) {
				int pontos = (int) (Math.random() * 11);
				pontacaoTiro += pontos;
				System.out.println("corredor-" + id + " fez " + pontos);
			}

			pontacaoTiro += 0;

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			semaforo.release();
		}

	}

	private void ciclismo() {
		int percurso = 5000;
		int percorrido = 0;
		int velocidade;
		try {
			System.out.println("corredor-" + id + " iniciou o ciclismo");
			while (percorrido < percurso) {
				velocidade = (int) ((Math.random() * 11) + 30);
				sleep(40);
				percorrido += velocidade;
				if (percorrido > 5000) {
					percorrido = 5000;
					System.out.println("corredor-" + id + " finalizou o ciclismo");
					pontuacaoTotal = pontacaoTiro + pontosColocacao;
					pontosColocacao -= 10;

				}

			}

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void chegada() {
		colocacao[indice][0] = id;
		// calculo pontuacao
		colocacao[indice][1] = pontuacaoTotal;
		indice++;

	}

}
