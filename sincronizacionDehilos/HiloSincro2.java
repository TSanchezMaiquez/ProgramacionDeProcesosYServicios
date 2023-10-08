package sincronizacionDehilos;

public class HiloSincro2 extends Thread{
	boolean fin = false;
	public static int contador =0;
	private int idHilo;
	private Object candado;
	
	
	public HiloSincro2(int idHilo, Object candado) {
		this.idHilo=idHilo;
		this.start();
		this.candado=candado;
	}
	
	
	public void run() {
		
		while (!fin) 
		{
			
			cambiarContador();
			/*String nombre = this.getName();
			System.out.println("Hola soy hilo "+ nombre);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
	
				e.printStackTrace();
			}*/
		}
	}

	//Synchronized permite bloquear los hilos y hasta que no termine el que esta dentro no puede entrar otro
	public void cambiarContador() {
		
		synchronized(this.candado) 
		{
			if(this.idHilo==contador) {
				System.out.println("hola soy "+ this.idHilo);
				contador++;
			
			try {
				Thread.sleep(2000);
				} catch (InterruptedException e) {
	
				e.printStackTrace();
				}
			}else if(contador ==100){
				contador=0;
			}
		}
	}
}