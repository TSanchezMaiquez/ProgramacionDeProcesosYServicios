package domain;

import domain.Buffer;

public class Consumidor extends Thread{

	private Buffer buffer;
	private int id;
	public Consumidor (Buffer miBuffer, int id) {
		this.buffer= miBuffer;
		this.id = id;
	}
	
	public void run (){
		char valor;
		for(int i=0; i<10; i++){
			valor=buffer.recoger();
			System.out.println(i+ " Consumidor: "+valor+ " id: "+ id);
			try{
				sleep(100);
			}catch (InterruptedException e) { }
		}
	}

}
