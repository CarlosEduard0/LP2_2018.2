public class Turma {

	private Aluno aluno1;
	private Aluno aluno2;
	private Aluno aluno3;

	public Turma() {
		this(null, null, null);
	}

	public Turma(Aluno aluno1, Aluno aluno2, Aluno aluno3) {
		this.setAluno1(aluno1);
		this.setAluno2(aluno2);
		this.setAluno3(aluno3);
	}

	public double calcularMedia() {
		double media = aluno1.calcularMedia() + aluno2.calcularMedia() + aluno3.calcularMedia();
		media /= 3.0;
		return media;
	}

	public void setAluno1(Aluno aluno) {
		this.aluno1 = aluno;
	}

	public void setAluno2(Aluno aluno) {
		this.aluno2 = aluno;
	}

	public void setAluno3(Aluno aluno) {
		this.aluno3 = aluno;
	}

	public Aluno getAluno1() {
		return this.aluno1;
	}

	public Aluno getAluno2() {
		return this.aluno2;
	}

	public Aluno getAluno3() {
		return this.aluno3;
	}
}