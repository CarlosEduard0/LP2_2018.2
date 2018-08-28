import java.time.LocalDate;

public class Refrigerante extends ProdutoNaoDuravel {
    public Refrigerante(String nome, double preco, String marca, String descricao, LocalDate dataFrabricacao, LocalDate dataValidade, String genero) {
        super(nome, preco, marca, descricao, dataFrabricacao, dataValidade, genero);
    }
}