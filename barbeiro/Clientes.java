package barbeiro;

import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Clientes extends Thread {
	private Barbearia barbearia;

	private int clientes;

	Clientes(Barbearia barbearia) {
		this.barbearia = barbearia;
		this.clientes = barbearia.getClientes();
	}

	public void run() {
		// Recuperação dos dados compartilhados
		Queue<Cliente> cadeiras = barbearia.getCadeiras();
		Semaphore fila = barbearia.getFila();
		Semaphore acesso = barbearia.getAcesso();
		Cliente cliente1;

		try {
			// Para o número de clientes entrado
			for (int i = 0; i < clientes; i++) {
				// Novo cliente chega na barbearia
				cliente1 = new Cliente();
				
				// Ele tenta sentar na fila
				acesso.acquire();
				if (fila.tryAcquire()) { // Consegue lugar e entra na fila
					cadeiras.offer(cliente1);
					cliente1.sentar();
				} else { // Não consegue e vai embora.
					System.out.println("Cliente " + cliente1.getNum()
							+ " retirou-se da Barbearia.");
					barbearia.setClientela(true, cliente1.getNum());
					cliente1 = null;
				}
				acesso.release();
				
				// Tempo de espera entre chegada de clientes
				Thread.sleep(barbearia.getTempo());
			}
			System.out.println("Acabaram os clientes.");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
