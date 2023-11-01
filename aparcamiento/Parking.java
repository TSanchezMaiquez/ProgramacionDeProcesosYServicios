package aparcamiento;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Parking {
    private int maxparking;
    private Semaphore estacionados;
    private Semaphore accesoContador;
    //El semaforo puede tener varios bloqueos, Lock solo tiene uno
    private Lock miLock = new ReentrantLock();
    private int contadorEstacionamientos;
    private long tiempoEstacionamiento;
    
    
	public Parking(int maxparking) {
		this.maxparking = maxparking;
		this.estacionados = new Semaphore(maxparking);
		this.accesoContador = new Semaphore(1);
		this.contadorEstacionamientos = 0;
	}

	public int getContadorEstacionamientos() {
		int contador = 0;
		try {
			accesoContador.acquire();
			contador = contadorEstacionamientos;
			accesoContador.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return contador;
	}

	/*public void entrarParking() {
		try {
			estacionados.acquire();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}*/

	public void salirParking() {
		estacionados.release();
		try {
			accesoContador.acquire();
			contadorEstacionamientos++;
			accesoContador.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean esDentroParking() {
		return estacionados.tryAcquire();
	}

	public void addTiempoEstacionado( long tiempoCoche) {

		miLock.lock();
		tiempoEstacionamiento +=tiempoCoche;
		miLock.unlock();
	}

	public long getTiempoEstacionado() {
		long tiempo;
		
		miLock.lock();
			tiempo = tiempoEstacionamiento;
		miLock.unlock();
		return tiempo;
		
	}
	public int getContadorEstacionamiento () {
		return this.contadorEstacionamientos;
	}
}
