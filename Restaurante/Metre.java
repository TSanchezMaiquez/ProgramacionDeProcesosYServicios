package Restaurante;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

public class Metre extends Thread{

	private PipedReader receptor;
	private BufferedReader flujoE;
	
	public Metre(PipedWriter emisor) {
		try {
			this.receptor = new PipedReader(emisor);
			this.flujoE = new BufferedReader(receptor);
			this.start();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	public void run() {
        try {
            String mensaje;
            while ((mensaje = flujoE.readLine()) != null) {
              
                System.out.println("Recibido: " + mensaje);
             
            }
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }
	public void cerrarReceptor () {
		try {
			receptor.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
