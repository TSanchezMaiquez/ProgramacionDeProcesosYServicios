package Restaurante;

import java.io.PipedWriter;
import java.io.PrintWriter;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Mesa extends Thread{

	private int id;
	private Sincroniza sincro;	
	private Metre metre;
	private PipedWriter emisor;
	private PrintWriter flujoS;
	private Lock miLock = new ReentrantLock();
	
	public Mesa(int i, Sincroniza sincro, PipedWriter emisor, PrintWriter flujoS, Metre metre ) {

			this.id=i;
			this.sincro=sincro;
			this.start();
			this.metre = metre;
			this.emisor = emisor;
			this.flujoS = flujoS;
				
	}

	public void run () {
		Random aleatorio = new Random();
		sincro.barreraMesas();
	
		boolean mesaAtendida = false;
		
		while(!mesaAtendida) {
			if(sincro.solicitarCocinero()) {
				try {
					
					System.out.println("La mesa "+ id + " ha sido atendida");
					Thread.sleep(aleatorio.nextInt(2000));
					mesaAtendida = true;
				} catch (InterruptedException e) {
	
					e.printStackTrace();
				}
				sincro.soltarCocinero();
				
			}else {
				try {
					System.out.println("La mesa "+ id + " aún no ha sido atendida");
					Thread.sleep(aleatorio.nextInt(1000));
					this.imprimirMensaje();
					flujoS.println("Mesa "+id+ " esperando cocinero");
				} catch (InterruptedException e) {
	
					e.printStackTrace();
				}	
			}
		}
	
		sincro.mesaFinalizada();
		System.out.println("La mesa "+ id + " ha terminado");	
	}

	public void imprimirMensaje () {
		miLock.lock();
			System.out.println("Mesa "+id+ " esperando cocinero");
		miLock.unlock();
	}
}
