package barbeiro;

import java.util.concurrent.*;
import java.util.*;

public class Barbearia {
	// Semáforo de armazenamento na fila
	private Semaphore fila;

	// Semáforo de acesso à fila
	private Semaphore acesso;

	// Fila de lugares
	private Queue<Cliente> cadeiras;

	// Número de clientes
	private int clientes;

	// Tempo de intervalo de chegada entre cada cliente
	private int tempo;

	// Estado do expediente
	private boolean clientela[];

	Barbearia(int clientes, int lugares, int tempo) {
		this.clientes = clientes;
		this.tempo = tempo;
		clientela = new boolean[clientes];
		fila = new Semaphore(lugares, true);
		acesso = new Semaphore(1);
		cadeiras = new LinkedList<Cliente>();
		System.out.println(lugares + " cadeiras criadas.");
	}

	void iniciar() {
		try {
			// Início da thread Barbeiro
			System.out.println("Barbeiro começa a atender.");
			new Barbeiro(this).start();

			// Início da thread Clientes
			System.out.println("Clientes começam a chegar.");
			new Clientes(this).start();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public Queue<Cliente> getCadeiras() {
		return cadeiras;
	}

	public Semaphore getFila() {
		return fila;
	}

	public Semaphore getAcesso() {
		return acesso;
	}

	public int getClientes() {
		return clientes;
	}

	public int getTempo() {
		return tempo;
	}

	public boolean getClientela() {
		boolean retorno = true;
		for (boolean c : clientela) {
			retorno &= c;
		}
		return retorno;
	}

	public void setClientela(boolean clientela, int i) {
		this.clientela[i] = clientela;
	}

}
