package barbeiro;

public class Principal {

	public static void main(String[] args) {
		// Teste de argumentos
		assert args[2].equals("") == false;

		// Inicializa��o das vari�veis
		// N�mero de clientes
		int clientes = Integer.parseInt(args[0]);
		// N�mero de lugares na fila
		int lugares = Integer.parseInt(args[1]);
		// Tempo de intervalo de chegada entre cada cliente
		int tempo = Integer.parseInt(args[2]);

		// Iniciar barbearia
		Barbearia barbearia = new Barbearia(clientes, lugares, tempo);
		barbearia.iniciar();
	}

}
