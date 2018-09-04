import java.util.ArrayList;

public class Database {
    private ArrayList<Cliente> clientes;

    public Database() {
        this.clientes = new ArrayList<Cliente>();
    }

    public void addCliente(Cliente cliente) {
        this.clientes.add(cliente);
    }

    public void removeCliente(Cliente cliente) {
        this.clientes.remove(cliente);
    }

    public void imprimir() {
        int pessoasFisicas = 0;
        int pessoasJuridicas = 0;
        for(Cliente cliente : this.clientes) {
            System.out.println("============================");
            if(cliente instanceof PessoaFisica) {
                System.out.println("Pessoa física");
                pessoasFisicas++;
            } else if(cliente instanceof PessoaJuridica) {
                System.out.println("Pessoa jurídica");
                pessoasJuridicas++;
            }
            cliente.imprimirDados();
            System.out.println( );
        }
        System.out.println("Número de pessoas físicas: " + pessoasFisicas);
        System.out.println("Número de pessoas jurídicas: " + pessoasJuridicas);
    }
}