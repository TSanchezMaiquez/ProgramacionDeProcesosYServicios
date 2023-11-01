package Misiles;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Sincroniza {
	private int maxmisiles;
	private CountDownLatch misilesListosCD;
	private CountDownLatch lanzaMisilesCD;
	private int aciertos;
	private int fallos;
	private Semaphore miLock = new Semaphore(1);
	private CountDownLatch misilesFinalizados;

	public Sincroniza(int maxmisiles) {
		this.maxmisiles = maxmisiles;
		this.misilesListosCD = new CountDownLatch(maxmisiles);
		this.lanzaMisilesCD = new CountDownLatch(1);
		this.misilesFinalizados = new CountDownLatch(maxmisiles);
	}

	public void esperaMisilesListo() {
		
		try {
			misilesListosCD.await();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		
	}

	public void notificarMisilListo() {
			misilesListosCD.countDown();
		
	}

	public void esperarActivacion() {
		try {
			lanzaMisilesCD.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void realizarActivacion() {
		lanzaMisilesCD.countDown();

		
	}

	public void resetContadores() {
		try {
			miLock.acquire();
				this.aciertos=0;
				this.fallos=0;
			miLock.release();
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		
	}

	public void contarFallo() {
		try {
			miLock.acquire();
				this.fallos++;
			miLock.release();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
	
		
	}

	public void contarAcierto() {
		try {
			miLock.acquire();
				this.aciertos++;
			miLock.release();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		
	}

	public int getAciertos() {
		int aciertos=0;
		try {
			miLock.acquire();
			aciertos = this.aciertos;
			miLock.release();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		
		return aciertos;
	}

	public int getFallos() {
		int fallos=0;
		try {
			miLock.acquire();
			fallos = this.fallos;
			miLock.release();
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		return fallos;
	}
	
	public void esperarMisilesFinalizados () {
		
		try {
			this.misilesFinalizados.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void notificarMisilesFinalizados () {
		this.misilesFinalizados.countDown();
	}

}
