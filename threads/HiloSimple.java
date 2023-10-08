package threads;

class HiloSimple implements Runnable{

	boolean fin =false;
	
	@Override
	public void run() {
		while(!fin) {
			System.out.println("Hola soy hiloSimple");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			
		}
		System.out.println("Hilo simple terminado");
		
	}

	public void terminar() {
		
		fin=true;
		
	}
	
}
