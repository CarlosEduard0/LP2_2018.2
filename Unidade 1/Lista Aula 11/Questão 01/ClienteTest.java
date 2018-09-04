public class ClienteTest {

    private Database clientes;

    public void setUp() {
        this.clientes = new Database();
        this.clientes.addCliente(new PessoaFisica("Nome", "Endereço", "Telefone", "CPF"));
        this.clientes.addCliente(new PessoaJuridica("Nome", "Endereço", "Telefone", "CNPJ", "Nome fantasia"));
    }

    public void test() {
        this.setUp();
        this.clientes.imprimir();
    }

    public static void main(String[] args) {
        ClienteTest clienteTest = new ClienteTest();
        clienteTest.test();
    }
}