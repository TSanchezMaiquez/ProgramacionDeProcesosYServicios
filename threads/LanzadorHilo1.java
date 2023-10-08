package threads;

public class LanzadorHilo1 {

	public static void main(String[] args) {

		HiloSimple hs = new HiloSimple ();
		
		Thread t = new Thread(hs);
		t.start();
		System.out.println("Lanzador terminado");
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		System.out.println("Parando hilo");
		hs.terminar();
	}

}

