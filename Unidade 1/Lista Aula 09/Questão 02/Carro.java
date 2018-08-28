import java.time.LocalDate;

public class Carro extends ProdutoDuravel {
    private String modelo;

    public Carro(String nome, double preco, String marca, String descricao, LocalDate dataFrabricacao, String materialPredominante, int durabilidade, String modelo) {
        super(nome, preco, marca, descricao, dataFrabricacao, materialPredominante, durabilidade);
        this.modelo = modelo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
}
