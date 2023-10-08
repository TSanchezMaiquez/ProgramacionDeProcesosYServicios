package threads;

public class LanzadorReloj {

	public static void main(String[] args) {

		Reloj hs1 = new Reloj ("TIC");
		Reloj hs2 = new Reloj ("TAC");
		
		
		hs1.start();
		hs2.start();
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		hs1.parar();
		hs2.parar();
		
		while(hs1.isAlive() || hs2.isAlive()) {
			System.out.println("Thinking!!!");
		}
		
		
		try {
			
			//Hasta que no muere el hilo el programa no continua.
			hs1.join();
			hs2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Reloj terminado");
	

	}

}
