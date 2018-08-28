public class ProfessorHorista extends Professor {

    private int totalHoras;
    private String salarioHoras;

    public ProfessorHorista(String nome, String matricula, int idade, int totalHoras, String salarioHoras) {
        super(nome, matricula, idade);
        this.totalHoras = totalHoras;
        this.salarioHoras = salarioHoras;
    }

    public double salario() {
        return totalHoras * Double.parseDouble(this.getSalarioHoras());
    }

    public void setTotalHoras(int totalHoras) {
        this.totalHoras = totalHoras;
    }

    public int getTotalHoras() {
        return this.totalHoras;
    }

    public void setSalarioHoras(String salarioHoras) {
        this.salarioHoras = salarioHoras;
    }

    public String getSalarioHoras() {
        return this.salarioHoras;
    }
}