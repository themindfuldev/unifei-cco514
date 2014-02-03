package filosofos;

public class Principal {

	public static void main(String[] args) {
		// Teste de argumentos
		assert args[1].equals("") == false;

		// Inicializa��o das vari�veis
		int total = Integer.parseInt(args[0]);	// Total de fil�sofos
		int limite = Integer.parseInt(args[1]); // Limite de itera��es
		
		// In�cio da mesa
		Mesa mesa = new Mesa(total, limite);
		mesa.iniciar();
	}

}
