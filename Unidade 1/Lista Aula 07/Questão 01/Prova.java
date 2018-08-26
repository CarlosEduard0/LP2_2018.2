public class Prova {

	private double notaParte1;
	private double notaParte2;

	public Prova() {
		this(0, 0);
	}

	public Prova(double notaParte1, double notaParte2) {
		this.setNotaParte1(notaParte1);
		this.setNotaParte2(notaParte2);
	}

	public double calcularNotaTotal() {
		if(notaParte1 + notaParte2 <= 10) {
			return notaParte1 + notaParte2;
		}
		return -1;
	}
	
	public void setNotaParte1(double valor) {
		this.notaParte1 = valor;
	}

	public void setNotaParte2(double valor) {
		this.notaParte2 = valor;
	}

	public double getNotaParte1() {
		return this.notaParte1;
	}

	public double getNotaParte2() {
		return this.notaParte2;
	}
}