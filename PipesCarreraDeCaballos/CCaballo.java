package PipesCarreraDeCaballos;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class CCaballo extends Thread{
	
	private int id;
	private Tablon tablon;
	private CyclicBarrier inicioConcurrente;
	private int posicion;
	private final int LONGITUDPISTA=100;
	private CountDownLatch finHilos;
	
	public CCaballo(int i, Tablon tablon, CyclicBarrier inicioConcurrente, CountDownLatch finHilos) {

		this.id=i;
		this.tablon=tablon;
		this.inicioConcurrente =inicioConcurrente;
		this.finHilos = finHilos;
		this.start();
	}

	public void run() {
		
		Random aleatorio = new Random();
		int avanza =0;
		posicion =0;
		try {
			//BARRIER!! The threads have to wait until are of them are created
			inicioConcurrente.await();
		} catch (InterruptedException | BrokenBarrierException e) {

			e.printStackTrace();
		}
		
		while(posicion < LONGITUDPISTA) {
			avanza = aleatorio.nextInt(10)+1;
			posicion +=avanza;
			tablon.setPosicion(id, posicion);
			try {
				sleep(500);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
		tablon.setGanador(id);
		//Allows to notify to the programm that all the threads have finished
		finHilos.countDown();
		
		
	}

}
