public class Main {
	public static void main(String[] args) {
		MailServer server 	= new MailServer();
		MailClient joao		= new MailClient(server, "João");
		MailClient maria 	= new MailClient(server, "Maria");

		joao.sendMailItem("Maria", "Boas vindas", "Olá Maria");
		maria.printNextMailItem();
	}
}