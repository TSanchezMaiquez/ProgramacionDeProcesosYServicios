package Restaurante;

import java.io.PrintWriter;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Sincroniza {

	private CyclicBarrier barrera;
	private Semaphore solicitarCocinero;
	private CountDownLatch mesasFinalizadas;
	private PrintWriter flujoS;
	
	public Sincroniza (int numCocineros, int numMesas) {
		
		this.barrera= new CyclicBarrier(numMesas);
		this.solicitarCocinero = new Semaphore(numCocineros);
		this.mesasFinalizadas = new CountDownLatch(numMesas);
	}

	public void barreraMesas() {

		try {
			barrera.await();
		} catch (InterruptedException | BrokenBarrierException e) {

			e.printStackTrace();
		}
	}
	public boolean solicitarCocinero() {
		
		return solicitarCocinero.tryAcquire();
	}
	
	public void soltarCocinero () {
		solicitarCocinero.release();
	}
	public void esperarMesasFinalicen() {
		try {
			this.mesasFinalizadas.await();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	}
	public void mesaFinalizada() {
		this.mesasFinalizadas.countDown();
	}
}
