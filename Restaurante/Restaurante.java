package Restaurante;

import java.io.IOException;
import java.io.PipedWriter;
import java.io.PrintWriter;
import java.time.LocalTime;

public class Restaurante {

	private final static int MAXCOCINEROS =8;
	private final static int MAXMESAS=100;
	private static long inicia=0;
	private static long saleRestaurante = 0;
	private static long totalTiempoTranscurrido;
	
	
	public static void main (String [] args) {
		
		PipedWriter emisor = new PipedWriter();
		PrintWriter flujoS = new PrintWriter(emisor);	
		Metre metre = new Metre(emisor);
		Sincroniza sincro = new Sincroniza(MAXCOCINEROS, MAXMESAS);
		Mesa [] mesas = new Mesa[MAXMESAS];
		
		for (int i = 0; i < mesas.length; i++) {
			
			mesas[i] = new Mesa(i, sincro, emisor,flujoS, metre);
		}
		System.out.println("Iniciando servicio de restaurante");
		LocalTime horaActual = LocalTime.now();
		String hora = horaActual.toString().substring(0,8);
		inicia = System.currentTimeMillis();
		sincro.esperarMesasFinalicen();
		metre.cerrarReceptor();
		
			try {
				emisor.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		saleRestaurante= System.currentTimeMillis();
		totalTiempoTranscurrido += (saleRestaurante -inicia);
		
		System.out.println("Servicio finalizado");
		System.out.println("Programa iniciado a la/s "+ hora + "\nAlumno: Toñi Sanchez Maiquez");
		System.out.println("El servicio ha durado "+ totalTiempoTranscurrido + " milisegundos");
	}
	
	
	
	
}
