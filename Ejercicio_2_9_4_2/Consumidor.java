package Ejercicio_2_9_4_2;



public class Consumidor extends Thread{

	private Buffer buffer;
	private int id;
	public Consumidor (Buffer miBuffer, int id) {
		this.buffer= miBuffer;
		this.id = id;
	}
	
	public void run (){
		char valor;
		while(true){
			valor=buffer.recoger();
			System.out.println(" Consumidor: "+valor+ " id: "+ id);
			try{
				sleep(1000);
			}catch (InterruptedException e) { }
		}
	}

}
