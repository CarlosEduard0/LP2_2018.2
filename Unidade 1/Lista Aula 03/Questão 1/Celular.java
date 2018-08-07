public class Celular {
    private int saldo;

    public Celular() {
        this.saldo = 1000;
    }

    public Celular(int saldo) {
        this.saldo = saldo;
    }

    public void recarga(int valor) {
        this.saldo += valor;
    }

    public void gastar(int valor) {
        if(valor > this.getSaldo()) {
            System.out.println("gastar foi chamado com um valor superior ao saldo: " + valor);
        } else {
            this.setSaldo(this.getSaldo() - valor);
        }
    }

    public void setSaldo(int saldo) {
        if(saldo < 0) {
            System.out.println("setSaldo foi chamado com um valor negativo: " + saldo);
        } else {
            this.saldo = saldo;
        }
    }

    public int getSaldo() {
        return this.saldo;
    }
}
