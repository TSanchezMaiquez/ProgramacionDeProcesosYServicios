package Misiles;

import java.io.PipedWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Norad {
	
	private static final int MAXMISILES = 100;

	public static void main(String[] args) {
		
		Sincroniza sincro = new Sincroniza(MAXMISILES);
		Misil [] misiles = new Misil [MAXMISILES];
		PipedWriter emisor [] = new PipedWriter [MAXMISILES];
		PrintWriter flujoS [] = new PrintWriter [MAXMISILES];
		
		for (int i = 0; i < misiles.length; i++) {
			emisor[i]= new PipedWriter();
			flujoS[i] = new PrintWriter(emisor[i]);
			misiles[i]= new Misil(i,sincro, emisor[i]);
		}
		sincro.esperaMisilesListo();
		System.out.println("Todos los misiles preparados");
		Scanner sc = new Scanner (System.in);
		boolean fin = false;
		while(!fin) {
			System.out.println("Orden > ");
			String comando = sc.nextLine();
			if(comando.contains("atacar")) {
				fin = true;
				sincro.resetContadores();
				sincro.realizarActivacion();
				dobleVerificacion(flujoS);
			}
		}
		sincro.esperarMisilesFinalizados();
		System.out.println("Numero de acierto: "+ sincro.getAciertos());
		System.out.println("Numero de fallos: "+ sincro.getFallos());
		System.out.println("Total de misiles lanzados: "+ MAXMISILES);
		System.out.println("Porcentaje de exito: "+sincro.getAciertos()*100/MAXMISILES);
	}

	private static void dobleVerificacion(PrintWriter[] flujoS) {

		for (int i = 0; i < MAXMISILES; i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			flujoS[i].println("ataca");
			
		}
	}

}
