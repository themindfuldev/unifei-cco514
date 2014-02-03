package barbeiro;

public class Cliente {
	private int num;

	private static byte total;

	static {
		total = 0;
	}

	public Cliente() {
		this.num = total++;
		System.out.println("Cliente " + num + " chegou à Barbearia.");
	}

	public int getNum() {
		return num;
	}

	public void cortaCabelo() {
		System.out.println("Cliente " + num + " cortou o cabelo.");
	}

	public void sentar() {
		System.out.println("Cliente " + num + " entrou na fila.");
	}

}
