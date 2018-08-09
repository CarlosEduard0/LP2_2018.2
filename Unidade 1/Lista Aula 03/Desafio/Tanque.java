public class Tanque {

    private int capacidade;
    private int capacidadeUtilizada;

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public int getCapacidadeUtilizada() {
        return capacidadeUtilizada;
    }

    public void setCapacidadeUtilizada(int capacidadeUtilizada) {
        if(capacidadeUtilizada > this.getCapacidade()) {
            System.out.println("setCapacidadeUtilizada foi chamado com um valor superior a capacidade: " + capacidadeUtilizada);
            return;
        }
        this.capacidadeUtilizada = capacidadeUtilizada;
    }
}
