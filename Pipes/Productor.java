package Pipes;

import java.io.PipedWriter;
import java.io.PrintWriter;

public class Productor extends Thread{

	private PrintWriter flujoS;
	private int id;
	
	
	public Productor (PipedWriter emisor, int id) {
		flujoS = new PrintWriter(emisor);
		this.id = id;
	}
	
	public void run() {
		
		for (int i = 0; i < 10; i++) {
			String mensa = "Hola "+ i + " hilo "+ id;
			flujoS.println(mensa);
			System.out.println("Emisor " + id+ " manda "+ mensa);
			
		}
	}
}
