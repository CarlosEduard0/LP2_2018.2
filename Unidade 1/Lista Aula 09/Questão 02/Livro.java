import java.time.LocalDate;

public class Livro extends ProdutoDuravel {
    public Livro(String nome, double preco, String marca, String descricao, LocalDate dataFrabricacao, String materialPredominante, int durabilidade) {
        super(nome, preco, marca, descricao, dataFrabricacao, materialPredominante, durabilidade);
    }
}
