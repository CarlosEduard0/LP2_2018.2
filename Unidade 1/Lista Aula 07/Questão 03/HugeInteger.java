public class HugeInteger {

	private int digitos[];

	public HugeInteger() {
		this.digitos = new int[40];
	}

	public HugeInteger(String valor) {
		this();
		this.converter(valor);
	}

	public void addHugeIntegers(HugeInteger num) {
		int soma = 0;
		int sobra = 0;

		for(int i = 40 -1; i >= 0; i--) {
			soma = this.getDigito(i) + num.getDigito(i) + sobra;
			if(soma < 10) {
				sobra = 0;
			} else {
				soma -= 10;
				sobra = 1;
			}
			this.setDigito(i, soma);
		}
	}

	public void print() {
		for(int i = 0; i < 9; i++) {
			System.out.print(this.digitos[i]);
		}
		System.out.println();
	}

	public void setDigito(int index, int valor) {
		this.digitos[index] = valor;
	}

	public int getDigito(int index) {
		return this.digitos[index];
	}

	private void converter(String valor) {
		for(int i = 0; i < valor.length(); i++) {
			this.digitos[i] = Character.getNumericValue(valor.charAt(i));
		}
	}
}