package Pipes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PipedReader;

public class Consumidor extends Thread{
	
	private BufferedReader flujoE;
	private boolean fin = false;
	private int id;
	
	public Consumidor (PipedReader receptor, int id) {
		
		flujoE = new BufferedReader(receptor);
		this.id= id;
	}
	

	public void run() {
		while(!fin) {
			try {
				String mensa = flujoE.readLine();
				System.out.println("Recibido por "+ id+ " "+ mensa);
			} catch (IOException e) {

				fin=true;
			}
		}
		System.out.println("Consumidor cerrado");

	}

}
