package Ejercicio_2_9_4_2;


public class ProductorConsumidor extends Thread{

	
	public static void main (String args []) {
		
		Buffer miBuffer = new Buffer();
		Productor productor = new Productor(miBuffer);
		ProductorU productorU = new ProductorU(miBuffer);
		Consumidor consumidor = new Consumidor (miBuffer, 1);
		Consumidor consumidor2 = new Consumidor (miBuffer, 2);
		System.out.println("Iniciamos hilos");
		productor.start();
		productorU.start();
		consumidor.start();
		consumidor2.start();
		
		try {
			productorU.join();
			productor.join();
			consumidor.join();
			consumidor2.join();
			
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		System.out.println("Productor y consumidor finalizados");
	}
}
