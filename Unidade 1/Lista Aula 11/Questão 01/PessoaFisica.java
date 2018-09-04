public class PessoaFisica extends Cliente {
    private String cpf;

    public PessoaFisica(String nome, String endereco, String telefone, String cpf) {
        super(nome, endereco, telefone);
        this.cpf = cpf;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void imprimirDados() {
        super.imprimirDados();
        System.out.println("Cpf: " + this.getCpf());
    }
}