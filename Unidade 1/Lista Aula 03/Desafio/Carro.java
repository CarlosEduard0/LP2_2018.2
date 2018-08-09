public class Carro {

    private Motor motor;
    private Roda[] rodas;
    private Porta[] portas;
    private Tanque tanque;
    private int numMarchas;
    private String cor;
    private float velocidadeAtual;

    public Carro(Motor motor, Roda[] rodas, Porta[] portas, Tanque tanque, int numMarchas, String cor, float velocidadeAtual) {
        this.motor = motor;
        this.rodas = rodas;
        this.portas = portas;
        this.tanque = tanque;
        this.numMarchas = numMarchas;
        this.cor = cor;
        this.velocidadeAtual = velocidadeAtual;
    }

    public void ligar() {
        this.getMotor().setLigado(true);
    }

    public void desligar() {
        this.getMotor().setLigado(false);
    }

    public void acelerar(float velocidade) {
        this.setVelocidadeAtual(this.getVelocidadeAtual() + velocidade);
    }

    public void frear(float velocidade) {
        this.setVelocidadeAtual(this.getVelocidadeAtual() - velocidade);
    }

    public void abastecer(int litros) {
        this.getTanque().setCapacidadeUtilizada(this.getTanque().getCapacidadeUtilizada() + litros);
    }

    public Motor getMotor() {
        return motor;
    }

    public void setMotor(Motor motor) {
        this.motor = motor;
    }

    public Roda[] getRodas() {
        return rodas;
    }

    public void setRodas(Roda[] rodas) {
        this.rodas = rodas;
    }

    public Porta[] getPortas() {
        return portas;
    }

    public void setPortas(Porta[] portas) {
        this.portas = portas;
    }

    public Tanque getTanque() {
        return tanque;
    }

    public void setTanque(Tanque tanque) {
        this.tanque = tanque;
    }

    public int getNumMarchas() {
        return numMarchas;
    }

    public void setNumMarchas(int numMarchas) {
        this.numMarchas = numMarchas;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public float getVelocidadeAtual() {
        return velocidadeAtual;
    }

    public void setVelocidadeAtual(float velocidadeAtual) {
        this.velocidadeAtual = velocidadeAtual;
    }
}
