package threads;

import java.util.Random;

public class Reloj extends Thread{

	private boolean fin = false;
	private String mensaje;

		public Reloj (String mensaje) {
			this.mensaje= mensaje;
		}

	public void run() {
		
		Random aleatorio= new Random();
		
		while(!fin) {
			
			System.out.println(mensaje);
			int espera = aleatorio.nextInt(1000);
			try {
				Thread.sleep(espera);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			
		}
		//Random aleatorio= new Random();
		int espera = aleatorio.nextInt(1000)+500;
		
		try {
			Thread.sleep(espera);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		
		System.out.println("Parado "+mensaje);
	}
	public void parar() {
		fin=true;
	}
}
