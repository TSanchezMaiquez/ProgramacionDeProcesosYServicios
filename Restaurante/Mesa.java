package Restaurante;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.io.PrintWriter;
import java.util.Random;

public class Mesa extends Thread{

	private int id;
	private Sincroniza sincro;
	
	private Metre metre;
	private PipedWriter emisor;
	private PrintWriter flujoS;
	
	public Mesa(int i, Sincroniza sincro, PipedWriter emisor, PrintWriter flujoS, Metre metre ) {

			this.id=i;
			this.sincro=sincro;
			this.start();
			this.metre = metre;
			this.emisor = emisor;
			this.flujoS = flujoS;
				
	}

	public Mesa() {
		
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
					flujoS.println("Mesa "+id+ " esperando cocinero");
				} catch (InterruptedException e) {
	
					e.printStackTrace();
				}	
			}
		}
		
		
		
		sincro.mesaFinalizada();
		System.out.println("La mesa "+ id + " ha terminado");
	
	}

}
