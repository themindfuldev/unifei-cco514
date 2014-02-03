package filosofos;

public class Filosofo extends Thread {
	// Estado do filósofo
	private Estado estado;
	
	// Número do filósofo
	private int num;

	// Conexão com a mesa
	private Mesa mesa;

	// Limite de iterações
	private int limite;

	// Total de filósofos
	private static byte total;

	static {
		total = 0;
	}

	public Filosofo(Mesa mesa, int limite) {
		this.estado = Estado.PENSANDO;
		this.num = total++;
		this.limite = limite;
		this.mesa = mesa;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public int getNum() {
		return num;
	}

	public void Pensar() {
		System.out.println("Filosofo " + num + " pensando.");
	}

	public void Comer() {
		System.out.println("Filosofo " + num + " comendo.");
	}

	public void run() {
		try {
			// Para o número de filósofos
			for (int i = 0; i < limite; i++) {
				// Pensa
				Pensar();
				
				// Pega os garfos
				mesa.pegaGarfos(this);
				
				// Come
				Comer();
				
				// Devolve os garfos
				mesa.devolveGarfos(this);
			}
			// Vai embora
			System.out.println("Filosofo " + num + " retirou-se da mesa.");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
