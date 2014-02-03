package filosofos;

public class Filosofo extends Thread {
	// Estado do fil�sofo
	private Estado estado;
	
	// N�mero do fil�sofo
	private int num;

	// Conex�o com a mesa
	private Mesa mesa;

	// Limite de itera��es
	private int limite;

	// Total de fil�sofos
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
			// Para o n�mero de fil�sofos
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
