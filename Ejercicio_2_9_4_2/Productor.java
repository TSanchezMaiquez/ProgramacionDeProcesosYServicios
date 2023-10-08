package Ejercicio_2_9_4_2;



public class Productor extends Thread {
		 private Buffer buffer;
		 private final String letras="abcdefghijklmnopqrstuvxyz";
	 
	 public Productor(Buffer miBuffer) {
		 this.buffer=miBuffer;
	 }
	 public void run() {
		 while(true){
			 int posi = (int)(Math.random()*letras.length());
			 char c=letras.charAt((int)(Math.random()*letras.length()));
			 buffer.poner(c);
			 System.out.println(" Productor: " +c);
			 try {
			 sleep(400);
			 } 
			 catch (InterruptedException e) { }
		 	}
	 }
}