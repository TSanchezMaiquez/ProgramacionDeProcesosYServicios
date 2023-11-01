package PipesCarreraDeCaballos;

import java.io.PipedWriter;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class CHipodromo {
	final static int num_participantes = 4;
	
	public static void main(String[] args) {
		//boolean corriendo = true;
		//Prevents that threads from initialing the programm until all of them are created
		CyclicBarrier inicioConcurrente = new CyclicBarrier(num_participantes);
		//Prevents that the programm continue until all the threads communicate that they have finished
		//finHilos is a counter. When a thread finishes this notifies to CountDownLatch and finHilos subtracts one 
		CountDownLatch finHilos = new CountDownLatch(num_participantes+1);
				
		Tablon tablon = new Tablon(num_participantes,finHilos);
		CCaballo[] caballo = new CCaballo[num_participantes];
		
		for (int i = 0; i < caballo.length; i++) {
			//Each of the caballo points to the object tablon
			caballo[i] = new CCaballo(i,tablon,inicioConcurrente,finHilos);
		}
	
		try {
			//The programm cant continue until all the threads comunicate that they have finished
			finHilos.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		/*
		while (corriendo) {
			corriendo = false;
			for (int i = 0; i < caballo.length; i++) {
				if (caballo[i].isAlive()) {
					corriendo = true;}
			}
		}
		*/
		System.out.println("Carrera terminada");
		System.out.println("El ganador es "+tablon.getGanador());
	}

}
