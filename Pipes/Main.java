package Pipes;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

public class Main {

	public static void main(String[] args) {

		
		try {
			
			//Creación de tubería
			PipedWriter emisor = new PipedWriter();
			//Enlazamos el receptor con el emisor
			PipedReader receptor = new PipedReader(emisor);
			
			//Creamos productor y le pasamor el emisor
			Productor pHilo = new Productor(emisor, 1);
			Productor pHilo2 = new Productor(emisor, 2);
			Consumidor cHilo = new Consumidor (receptor,1);
			Consumidor cHilo2 = new Consumidor (receptor, 2);
			
			pHilo.start();
			pHilo2.start();
			cHilo.start();
			cHilo2.start();
		} catch (IOException e) {
			

			e.printStackTrace();
		}

	}

}
