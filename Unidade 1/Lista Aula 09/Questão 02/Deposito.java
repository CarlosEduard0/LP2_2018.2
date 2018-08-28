import java.util.ArrayList;

public class Deposito {
    private ArrayList<Produto> produtos;

    public Deposito() {
        this.produtos = new ArrayList<>();
    }

    public void adicionarProduto(Produto produto) {
        this.produtos.add(produto);
    }

    public int getQuantidadeProdutos() {
        return this.produtos.size();
    }

    public boolean isVazio() {
        return this.produtos.size() == 0;
    }

    public void produtoMaiorValor() {
        Produto maior = this.produtos.get(0);
        for(Produto produto : produtos) {
            if(produto.getPreco() > maior.getPreco()) {
                maior = produto;
            }
        }

        System.out.println("Produto com maior valor: " + maior.getNome());
    }
}