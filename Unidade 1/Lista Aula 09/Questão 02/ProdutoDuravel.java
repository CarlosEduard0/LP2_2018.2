import java.time.LocalDate;

public class ProdutoDuravel extends Produto {
    private String materialPredominante;
    private int durabilidade;

    ProdutoDuravel(String nome, double preco, String marca, String descricao, LocalDate dataFrabricacao, String materialPredominante, int durabilidade) {
        super(nome, preco, marca, descricao, dataFrabricacao);
        this.materialPredominante = materialPredominante;
        this.durabilidade = durabilidade;
    }

    public String getMaterialPredominante() {
        return materialPredominante;
    }

    public void setMaterialPredominante(String materialPredominante) {
        this.materialPredominante = materialPredominante;
    }

    public int getDurabilidade() {
        return durabilidade;
    }

    public void setDurabilidade(int durabilidade) {
        this.durabilidade = durabilidade;
    }
}
