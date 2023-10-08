package threads;

class HiloSimple2 extends Thread{

	boolean fin =false;
	
	@Override
	public void run() {
		while(!fin) {
			Thread t = Thread.currentThread();
			String nombre = t.getName();
			System.out.println("Hola soy hiloSimple"+nombre);
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
