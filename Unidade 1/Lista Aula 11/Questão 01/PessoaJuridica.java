public class PessoaJuridica extends Cliente {
    private String cnpj;
    private String nomeFantasia;

    public PessoaJuridica(String nome, String endereco, String telefone, String cnpj, String nomeFantasia) {
        super(nome, endereco, telefone);
        this.cnpj = cnpj;
        this.nomeFantasia = nomeFantasia;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public String getNomeFantasia() {
        return this.nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public void imprimirDados() {
        super.imprimirDados();
        System.out.println("Cnpj: " + this.getCnpj());
        System.out.println("Nome fantasia: " + this.getNomeFantasia());
    }
}