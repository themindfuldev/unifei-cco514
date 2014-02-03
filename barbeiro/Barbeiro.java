package barbeiro;

import java.util.*;
import java.util.concurrent.Semaphore;

public class Barbeiro extends Thread {
	private Barbearia barbearia;

	Barbeiro(Barbearia barbearia) {
		this.barbearia = barbearia;
	}

	public void run() {
		try {
			while (true) {
				// Recuperação dos dados compartilhados
				Queue<Cliente> cadeiras = barbearia.getCadeiras();
				Semaphore fila = barbearia.getFila();
				Semaphore acesso = barbearia.getAcesso();
				Cliente cliente2;

				// Soneca do barbeiro
				while (cadeiras.size() == 0) {
					System.out.println("O barbeiro foi dormir.");
					Thread.sleep(1000);
					System.out.println("O barbeiro acordou.");
					// Se não há mais clientela, o barbeiro vai embora
					if (barbearia.getClientela() == true) {
						System.out.println("O barbeiro foi embora.");
						return;
					}
				}
				
				// O primeiro da fila levanta para cortar o cabelo 
				acesso.acquire();
				cliente2 = cadeiras.poll();
				fila.release();
				acesso.release();
				
				// Este cliente corta o cabelo e vai embora
				cliente2.cortaCabelo();
				System.out.println("Cliente " + cliente2.getNum()
						+ " retirou-se da Barbearia.");
				// Este cliente já foi atendido 
				barbearia.setClientela(true, cliente2.getNum());
				cliente2 = null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
