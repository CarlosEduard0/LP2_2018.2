public class Roda {

    private int tamanho;
    private int pressao;

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public int getPressao() {
        return pressao;
    }

    public void setPressao(int pressao) {
        if(pressao < 0 || pressao > 50) {
            System.out.println("setPressao foi chamado com um valor inferior a 0 ou superior a 50: " + pressao);
            return;
        }
        this.pressao = pressao;
    }
}
