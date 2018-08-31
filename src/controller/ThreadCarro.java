package controller;

import java.util.concurrent.Semaphore;

public class ThreadCarro extends Thread {

	private int idThread;
	private Semaphore semaforo;
	private static int posicaoChegada;
	private static int posicaoSaida;

	public ThreadCarro(int idThread, Semaphore semaforo) {
		this.idThread = idThread;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		carroAndando();
		try {
			semaforo.acquire();
			carroParado();
			carroSaindo();
		} catch (InterruptedException e) {

			e.printStackTrace();
		} finally {
			semaforo.release();
		}

	}

	private void carroAndando() {
		int distanciaFinal = (int) ((Math.random() * 1001) + 2000);
		int distanciaPercorrida = 0;
		int deslocamento = (int) ((Math.random() * 6) + 10);
		int tempo = 30;

		while (distanciaPercorrida < distanciaFinal) {
			distanciaPercorrida += deslocamento;
			try {
				Thread.sleep(tempo);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			System.out.println("Carro #" + idThread + " andou " + distanciaPercorrida + " em metros.");
		}
		posicaoChegada++;
		System.out.println("Carro #" + idThread + " foi o " + posicaoChegada + "º a chegar");
	}

	private void carroParado() {
		System.out.println("O carro #" + idThread + " estacionou");
		int tempoEspera = (int) ((Math.random() * 2501) + 500);
		try {
			Thread.sleep(tempoEspera);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}

	private void carroSaindo() {
		posicaoSaida++;
		System.out.println("O carro #" + idThread + " foi o " + posicaoSaida + "º a sair.");

	}
}
