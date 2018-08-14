import java.util.HashMap;

public class ContadorPalavras {

	private String texto;

	public ContadorPalavras(String texto) {
		this.texto = texto;
	}

	private HashMap<String, Integer> contarPalavras() {
		String[] palavras = this.texto.split(" ");
		HashMap<String, Integer> resultado = new HashMap<String, Integer>();
		for(String palavra : palavras) {
			if(resultado.get(palavra) == null) {
				resultado.put(palavra, 1);
			} else {
				resultado.put(palavra, resultado.get(palavra) + 1);
			}
		}
		return resultado;
	}

	public void print() {
		HashMap<String, Integer> palavras = this.contarPalavras();
		palavras.forEach((k, v) -> System.out.println(k + ": " + v));
	}
}