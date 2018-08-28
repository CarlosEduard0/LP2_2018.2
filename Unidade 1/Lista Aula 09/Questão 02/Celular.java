import java.time.LocalDate;

public class Celular extends ProdutoDuravel {
    private String modelo;

    public Celular(String nome, double preco, String marca, String descricao, LocalDate dataFrabricacao, String materialPredominante, int durabilidade, String modelo) {
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
