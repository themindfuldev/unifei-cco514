package filosofos;

public class Principal {

	public static void main(String[] args) {
		// Teste de argumentos
		assert args[1].equals("") == false;

		// Inicialização das variáveis
		int total = Integer.parseInt(args[0]);	// Total de filósofos
		int limite = Integer.parseInt(args[1]); // Limite de iterações
		
		// Início da mesa
		Mesa mesa = new Mesa(total, limite);
		mesa.iniciar();
	}

}
