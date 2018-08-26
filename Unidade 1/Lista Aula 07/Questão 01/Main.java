public class Main {

	public static void main(String[] args) {

		Prova prova1 = new Prova(4.0, 2.5);
		Prova prova2 = new Prova(1.0, 7.0);
		Prova prova3 = new Prova(6.5, 3.5);
		Prova prova4 = new Prova(0.0, 3.0);
		Prova prova5 = new Prova(5.0, 4.0);
		Prova prova6 = new Prova(6.0, 1.5);

		Aluno aluno1 = new Aluno(prova1, prova2);
		Aluno aluno2 = new Aluno(prova3, prova4);
		Aluno aluno3 = new Aluno(prova5, prova6);

		Turma turma = new Turma(aluno1, aluno2, aluno3);

		System.out.println("Média dos alunos: ");
		System.out.println("Aluno 1: " + aluno1.calcularMedia());
		System.out.println("Aluno 2: " + aluno2.calcularMedia());
		System.out.println("Aluno 3: " + aluno3.calcularMedia());

		System.out.println("Média da turma: " + turma.calcularMedia());
	}
}