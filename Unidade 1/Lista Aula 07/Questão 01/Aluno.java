public class Aluno {

	private Prova prova1;
	private Prova prova2;

	public Aluno() {
		this(null, null);
	}

	public Aluno(Prova prova1, Prova prova2) {
		this.setProva1(prova1);
		this.setProva2(prova2);
	}

	public double calcularMedia() {
		double media = this.getProva1().calcularNotaTotal() + this.getProva2().calcularNotaTotal();
		media /= 2.0;
		return media;
	}

	public void setProva1(Prova prova) {
		this.prova1 = prova;
	}

	public void setProva2(Prova prova) {
		this.prova2 = prova;
	}

	public Prova getProva1() {
		return this.prova1;
	}

	public Prova getProva2() {
		return this.prova2;
	}
}