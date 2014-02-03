package filosofos;

import java.util.concurrent.*;

public class Mesa {
	// Array de filósofos
	private Filosofo[] filosofos;
	
	// Semáforos dos garfos
	private Semaphore[] garfos;
	
	// Semáforo mutex para os garfos
	private Semaphore mutex;
	
	// total de filósofos
	private int total;

	Mesa(int total, int limite) {
		this.total = total;
		mutex = new Semaphore(1);
		garfos = new Semaphore[total];		
		filosofos = new Filosofo[total];
		
		// Criação dos garfos e filósofos
		for (int i=0; i<total; i++) {
			garfos[i] = new Semaphore(1);
			filosofos[i] = new Filosofo(this, limite);
		}
		System.out.println(total + " garfos criados.");
		System.out.println(total + " filosofos criados.");		
	}

	void iniciar() {	
		// Início das threads de cada filósofo
		for (Filosofo f: filosofos) {			
			f.start();					
		}
		System.out.println(total + " filosofos sentaram-se à mesa.");
	}

	void pegaGarfos(Filosofo filosofo) 
			throws InterruptedException {
		// Semáforo para pegar garfos		
		mutex.acquire();
		filosofo.setEstado(Estado.COM_FOME);
		// Tentativa de pegar os garfos
		tentativa(filosofo);
		mutex.release();
		
		// Garfos obtidos pelo filósofo
		garfos[filosofo.getNum()].acquire();
	}

	void devolveGarfos(Filosofo filosofo)
			throws InterruptedException {
		// Semáforo para devolver os garfos
		mutex.acquire();
		filosofo.setEstado(Estado.PENSANDO);
		// Tentativa de devolver os garfos
		tentativa(esquerda(filosofo));
		tentativa(direita(filosofo));
		mutex.release();
	}

	// Rotação da mesa
	private Filosofo esquerda(Filosofo filosofo) {
		return filosofos[(filosofo.getNum() + total - 1) % total];
	}

	// Rotação da mesa
	private Filosofo direita(Filosofo filosofo) {
		return filosofos[(filosofo.getNum() + 1) % total];
	}

	private void tentativa(Filosofo filosofo) 
			throws InterruptedException {		
		if (filosofo.getEstado() == Estado.COM_FOME
				&& esquerda(filosofo).getEstado() != Estado.COMENDO
				&& direita(filosofo).getEstado() != Estado.COMENDO) {
			// Se o filósofo pode comer, ele se habilita para comer.
			filosofo.setEstado(Estado.COMENDO);
			// Libera o seu garfo
			garfos[filosofo.getNum()].release();
		}
	}
}
