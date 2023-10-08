package Ejercicio_2_9_4_2;

import java.util.LinkedList;
import java.util.Queue;

public class Buffer {
	
	private char contenido;
	private boolean disponible;
	private Queue<Character> cola;
	private int contador;
	private final int max=10;
	private final int min=0;
	
	public Buffer () {
		this.contenido = ' ';
		cola = new LinkedList<>();
		contador=0;
	}
	
	public synchronized char recoger () {
		while(contador<max) {
			try {
				wait();
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
		
		contador--;
		notifyAll();
		return cola.poll();
	}

	public synchronized void poner (char c) {
		
		while(contador==max) {
			try {
				wait();
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
		}
			cola.offer(c);
			contador++;
			notifyAll();
	}
	
	
}

