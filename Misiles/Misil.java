package Misiles;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;
import java.util.Random;

public class Misil extends Thread{

	private Sincroniza sincro;
	private PipedReader receptor;
	private BufferedReader flujoE;
	private int id;
	
	public Misil(int id, Sincroniza sincro, PipedWriter emisor) {
		try {
			this.receptor = new PipedReader(emisor);
			this.flujoE = new BufferedReader(receptor);
			this.sincro =sincro;
			this.id = id;
			this.start();
		} catch (IOException e) {

			e.printStackTrace();
		}
		
	}

	public void run () {
		System.out.println("Misil "+id+ " armado.");
		sincro.notificarMisilListo();
		//Esperamos que el NORAD nos active
		sincro.esperarActivacion();
		System.out.println("Misil "+id+ " esperando doble verificacion.");
		try {
			String comando = flujoE.readLine();
			if(comando.contains("ataca")) {
				System.out.println("Misil "+id+ " lanzado");
				Random aleatorio = new Random();
				Thread.sleep(aleatorio.nextInt(500));
				if(aleatorio.nextInt(2)>0) {
					System.out.println("Misil "+ id+ " acerto");
					sincro.contarAcierto();
				}
				else {
					System.out.println("Misil "+ id+ " fallo");
					sincro.contarFallo();
				}
			}else {
				System.out.println("Misil "+id+ " abortado");
			}
		} catch (IOException | InterruptedException e) {
			
			e.printStackTrace();
		}
		sincro.notificarMisilesFinalizados();
	}
}
