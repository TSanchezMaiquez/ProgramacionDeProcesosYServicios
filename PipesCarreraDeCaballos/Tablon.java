package PipesCarreraDeCaballos;

import java.util.concurrent.CountDownLatch;

public class Tablon extends Thread{
	private int[] posicionCaballo;
	private boolean finCarrera = false;
	private final int longitudPista = 100;
	private int ganador = -1;
	private CountDownLatch finHilos;
	
	public Tablon(int numParticipantes, CountDownLatch finHilos) {
		posicionCaballo = new int[numParticipantes];
		for (int i = 0; i < posicionCaballo.length; i++) {
			//All the threads(caballos) inicializate with position=0;
			posicionCaballo[i] = 0;
		}
		this.finHilos = finHilos;
		this.start();
	}
	//This method is called in CCaballo class, at every turn inside the while, for anyone of the threads
	public void  setPosicion(int id, int posicion) {
		//id = number of the horse
		posicionCaballo[id] = posicion;
	}
	
	public void run() {
		while (!finCarrera) {
			finCarrera = true;
			for (int id = 0; id < posicionCaballo.length; id++) {
				System.out.println("Caballo "+id+" "+pinta(posicionCaballo[id]));
				if (posicionCaballo[id] < longitudPista) {
					finCarrera = false;
				}
			}
			try {
				sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}//end-while
		finHilos.countDown();
		//System.out.println("El ganador es "+this.ganador);
	}

	private String pinta(int posi) {
		return "*".repeat(posi/5);
	}

	public  synchronized void setGanador(int id) {
		if (ganador < 0) { //no hay ganador
			this.ganador = id;
		}
		
	}

	public int getGanador() {
		return this.ganador;
	}
}
