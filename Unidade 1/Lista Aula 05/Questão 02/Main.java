public class Main {
	public static void main(String[] args) {
		String texto 				= "eu tenho um barco vermelho e um carro vermelho";
		ContadorPalavras contador 	= new ContadorPalavras(texto);
		contador.print();
	}
}